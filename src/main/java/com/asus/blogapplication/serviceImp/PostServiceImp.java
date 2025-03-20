package com.asus.blogapplication.serviceImp;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.asus.blogapplication.Entities.Category;
import com.asus.blogapplication.Entities.Comment;
import com.asus.blogapplication.Entities.Post;
import com.asus.blogapplication.Entities.User;
import com.asus.blogapplication.exceptions.ResourceNotFoundException;
import com.asus.blogapplication.payloads.CategoryDto;
import com.asus.blogapplication.payloads.CommentDto;
import com.asus.blogapplication.payloads.CommentResponse;
import com.asus.blogapplication.payloads.PostDto;
import com.asus.blogapplication.payloads.PostResponse;
import com.asus.blogapplication.payloads.UserDto;
import com.asus.blogapplication.repositories.CategoryRepository;
import com.asus.blogapplication.repositories.CommentRepository;
import com.asus.blogapplication.repositories.PostRepository;
import com.asus.blogapplication.repositories.UserRepository;
import com.asus.blogapplication.services.PostService;


@Service
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private PostResponse postResponse;
	@Autowired
	private CommentRepository comRepo;
    @Autowired
	private CommentResponse commentResponse;
	
    @Override
	public PostDto createPost(PostDto postdto, Long userid, Long categoryid) {
		User user =this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userid));
		Category category =this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryid));
	   	Post post= this.convertToPost(postdto);
	   	post.setTitle(postdto.getTitle());
	   	post.setContent(postdto.getContent());
	   	post.setImageUrl(postdto.getImageUrl());
	   	post.setUser(user);
	   	post.setCategory(category);
	   	this.postRepo.save(post);
	   	PostDto response= this.convertToPostDto(post);
	   	return response;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Long PostId) {
	     Post post=this.postRepo.findById(PostId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", PostId));
	     post.setTitle(postdto.getTitle());
	     post.setContent(postdto.getContent());
	     post.setImageUrl(postdto.getImageUrl());
	     this.postRepo.save(post);
		 return this.convertToPostDto(post);
	}

	@Override
	public PostDto getPostById(Long id) {
	 Post post= this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", id));
	 return this.convertToPostDto(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
	    Sort sort=null;
		if(sortDirection.equalsIgnoreCase("asc")) {
	    	sort= Sort.by(sortBy).ascending();
	    }else {
	    	 sort=Sort.by(sortBy).descending();
	     }
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
	     Page<Post> postPages = this.postRepo.findAll(p);
         List<Post> post= postPages.getContent();
	     List<PostDto> postdto= post.stream().map(e->this.convertToPostDto(e)).toList();
	     return this.convertToPostResponse(postdto, postPages);
	}

	@Override
	public void deletePostById(Long PostId) {
	    Post post= this.postRepo.findById(PostId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", PostId));
	    this.postRepo.delete(post);
		
	}
	
	/**
	 * Retrieves all posts associated with a specific category.
	 * <p>
	 * This method first fetches the category entity based on the provided category ID.
	 * If the category exists, it retrieves all posts linked to that category from the database.
	 * The fetched posts are then converted into a list of DTOs before being returned.
	 **/
	
	@Override
	public PostResponse getPostsBycategory(Long categoryid, Integer pageNumber, Integer pageSize ,String sortBy, String sortDirection) {
		 //Sort sort=null;
		Sort sort= sortDirection.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p= PageRequest.of(pageNumber, pageSize, sort);
		Category category=this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryid));
		Page<Post> postPages= this.postRepo.findBycategory(category, p);
		List<Post> post = postPages.getContent();
		List<PostDto> postdto= post.stream().map(e->this.convertToPostDto(e)).collect(Collectors.toList());
		
		return this.convertToPostResponse(postdto, postPages);
	}

	/**
	 * Retrieves all posts associated with a specific user.
	 * <p>
	 * This method first fetches the user entity based on the provided user ID.
	 * If the user exists, it retrieves all posts linked to that user from the database.
	 * The fetched posts are then converted into a list of DTOs before being returned.
	 **/
	
	@Override
	public PostResponse getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		 User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		 Sort sort= sortDirection.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		 Pageable p= PageRequest.of(pageNumber, pageSize, sort);
		 Page<Post> postPages = this.postRepo.findByUser(user,p);
		 List<Post> post= postPages.getContent();

		 List<PostDto> postdto= post.stream().map(e->this.convertToPostDto(e)).collect(Collectors.toList());
		 List<CommentDto> commentDtos= post.stream().flatMap(e->e.getComment().stream()).map(e->this.converToCommentDto(e)).toList();
		 Long count = this.comRepo.countCommentsByUserId(userId);
		 CommentResponse commentResponse= this.convertToCommentResponse(commentDtos, count);
         
           
          
          return this.convertToPostResponse(postdto, postPages);
	}

	@Override
	public List<PostDto> searchPost(String title) {
		List<Post> post = this.postRepo.findByTitle(title).orElseThrow(()-> new ResourceNotFoundException("Post", "title", title));;
		return post.stream().map(e->this.convertToPostDto(e)).toList();
		
	}

  
   
   private Post convertToPost(PostDto postdto) {
	  return this.modelmapper.map(postdto, Post.class);
   }
   
   private PostDto convertToPostDto(Post post) {
	   PostDto postdto= this.modelmapper.map(post, PostDto.class);
	   postdto.setUserdto(this.modelmapper.map(post.getUser(), UserDto.class));
	   postdto.setCategorydto(this.modelmapper.map(post.getCategory(), CategoryDto.class));
	   //postdto.setCommentdto(post.getComment().stream().map(comment->this.converToCommentDto(comment)).toList());
	           
	 
	  return postdto;
   }  
   
   private PostResponse convertToPostResponse(List<PostDto> p, Page<Post> q) {
	    this.postResponse.setContent(p);
	    this.postResponse.setCommentResponse(commentResponse);
	     this.postResponse.setPageNumber(q.getNumber());
	     this.postResponse.setPageSize(q.getSize());
	     this.postResponse.setTotalPages(q.getTotalPages());
	     this.postResponse.setTotalElements(q.getTotalElements());
	     this.postResponse.setLastPage(q.isLast());
	     return postResponse;
	     }
   
   
   private CommentDto converToCommentDto (Comment comment) {
		 CommentDto commentdto=this.modelmapper.map(comment, CommentDto.class);
		  commentdto.setUserdto(this.modelmapper.map(comment.getUser(), UserDto.class));
		 
	return commentdto;
   }
   
   
   
   
   private CommentResponse convertToCommentResponse (List<CommentDto> commentdto, Long count) {
		 commentResponse.setComment(commentdto);
		 commentResponse.setTotalComment(count);
		 return commentResponse;
   }	 
	 
}
     