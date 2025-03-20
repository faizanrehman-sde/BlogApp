package com.asus.blogapplication.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.asus.blogapplication.payloads.ApiResponse;
import com.asus.blogapplication.payloads.PostDto;
import com.asus.blogapplication.payloads.PostResponse;
import com.asus.blogapplication.services.PostService;
import appConstant.AppConstants;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	/* Create A new Post*/
	@PostMapping("/user/{userId}/category/{categoryId}") 
	public ResponseEntity<PostDto> createNewPosts (@Valid @RequestBody PostDto postdto, @PathVariable Long userId,
			                                       @PathVariable Long categoryId )
	{
		PostDto p= this.postservice.createPost(postdto, userId, categoryId);
		return new ResponseEntity<PostDto>(p, HttpStatus.CREATED);
	}
	
	
	/*Update the Existing Post*/
	@PutMapping("/update/postId/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto, @PathVariable Long postId) {
		return new ResponseEntity<PostDto>(this.postservice.updatePost(postdto, postId), HttpStatus.OK);
	}
	
	
	/*Fetch All Posts From the Specific Category  */
	@GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostsByCategory(  @PathVariable Long categoryId,
    		                                                 @RequestParam(defaultValue = AppConstants.Page_Number,required = false) Integer pageNumber,
    		                                                 @RequestParam(defaultValue = AppConstants.Page_Size,required = false) Integer pageSize,
    		                                                 @RequestParam(defaultValue = AppConstants.Sort_By,required = false) String sortBy,
    		                                                 @RequestParam(defaultValue = AppConstants.Sort_Direction,required = false) String sortDirection
                                                           )
	{
		return new ResponseEntity<PostResponse>(this.postservice.getPostsBycategory(categoryId,pageNumber,pageSize,sortBy,sortDirection), HttpStatus.OK);
	}
	
	
	/*Fetch All Posts By User*/
	@GetMapping("/user/{userId}")
	public ResponseEntity<PostResponse> getAllPostsByUser(  @PathVariable Long userId,
			                                                @RequestParam(defaultValue = "0",required = false) Integer pageNumber,
			                                                @RequestParam(defaultValue = "5",required = false) Integer pageSize,
			                                                @RequestParam(defaultValue = AppConstants.Sort_By,required = false) String sortBy,
   		                                                    @RequestParam(defaultValue = AppConstants.Sort_Direction,required = false) String sortDirection
   		                                                  )
	{
		return new ResponseEntity<PostResponse>(this.postservice.getAllPostsByUser(userId,pageNumber,pageSize,sortBy ,sortDirection), HttpStatus.OK);
	}
	
	
	/* Fetch Post By PostId*/
	@GetMapping("postId/{postId}")
	public ResponseEntity<PostDto> getPostsByPostId(@PathVariable Long postId){
		this.postservice.getPostById(postId);
		return new ResponseEntity<PostDto>(this.postservice.getPostById(postId), HttpStatus.OK);
	}
	
	
	/* Fetch All Posts */
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPosts( @RequestParam(defaultValue = AppConstants.Page_Number,required = false) Integer pageNumber,
	                                                 @RequestParam(defaultValue = AppConstants.Page_Size,required = false) Integer pageSize,
	                                                 @RequestParam(defaultValue = AppConstants.Sort_By,required = false) String sortBy,
			                                         @RequestParam(defaultValue = AppConstants.Sort_Direction,required = false) String sortDirection
			                                       ) 
	{
		return new ResponseEntity<PostResponse>(this.postservice.getAllPost(pageNumber,pageSize,sortBy,sortDirection), HttpStatus.OK);
	}
	
	
	/* Delete Post By PostId*/
	@DeleteMapping("postId/{postId}")
	public ResponseEntity<ApiResponse> deletePostBy(@PathVariable Long postId){
		this.postservice.deletePostById(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
	}
	 
	
	/*  Fetch Posts Based On Title */
	@GetMapping("/by-title/{title}")
	public ResponseEntity<List<PostDto>> searchPostByName(@PathVariable String title){
		return new ResponseEntity<List<PostDto>>(this.postservice.searchPost(title), HttpStatus.OK);
	}
	
}
