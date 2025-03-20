package com.asus.blogapplication.serviceImp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asus.blogapplication.Entities.Comment;
import com.asus.blogapplication.Entities.Post;
import com.asus.blogapplication.Entities.User;
import com.asus.blogapplication.exceptions.ResourceNotFoundException;
import com.asus.blogapplication.payloads.CommentDto;
import com.asus.blogapplication.payloads.CommentResponse;
import com.asus.blogapplication.payloads.UserDto;
import com.asus.blogapplication.repositories.CommentRepository;
import com.asus.blogapplication.repositories.PostRepository;
import com.asus.blogapplication.repositories.UserRepository;
import com.asus.blogapplication.services.CommentService;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private CommentResponse commentResponse;
	
	@Override
	public CommentDto createComment(CommentDto commentdto, Long postId, Long userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
	    Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
	    Comment c=this.modelmapper.map(commentdto, Comment.class);
	    c.setContent(commentdto.getContent());
	    c.setPost(post);
	    c.setUser(user);
	    post.getComment().add(c);    
	    user.getComment().add(c);
		 System.out.println("Post ID: " + post.getId());
		 System.out.println("Number of Comments: " + post.getComment().size());
	    Comment comment=this.commentRepo.save(c);
	    return this.converToCommentDto(comment);
	    
	    
	}
	
	@Override
	public CommentResponse getAllCommentsByUserId(Long userId) {
		
		  User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		  List<Comment> comment= this.commentRepo.findByUser(user);
		 Long count= this.commentRepo.countCommentsByUserId(userId);
		  List<CommentDto> commentdto= comment.stream().map(e->this.converToCommentDto(e)).toList();
		  return this.commentResponse(commentdto, count);
		 
	}

	@Override
	public void deleteComment(Long commentId) {
	       Comment comment= this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
	       Post post = comment.getPost();
	       post.getComment().remove(comment);
	       this.postRepo.save(post);
	       
	       User user =comment.getUser();
	       user.getComment().remove(comment);
	       this.userRepo.save(user);
		
	       this.commentRepo.delete(comment);
	}
	 private CommentDto converToCommentDto (Comment comment) {
		 CommentDto commentdto=this.modelmapper.map(comment, CommentDto.class);
		  commentdto.setUserdto(this.modelmapper.map(comment.getUser(), UserDto.class));
		  //commentdto.setTotalComment(this.totalComments(comment.getPost().getId()));
		 return commentdto;
	 }
	 
	 private CommentResponse commentResponse (List<CommentDto> commentdto, Long count) {
		 commentResponse.setComment(commentdto);
		 commentResponse.setTotalComment(count);
		 return commentResponse;
		 
	 }

	@Override
	public Long totalComments(Long userId) {
		return this.commentRepo.countCommentsByUserId(userId);
		
	}

	

}
