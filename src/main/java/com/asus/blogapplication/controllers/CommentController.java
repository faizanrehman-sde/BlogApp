package com.asus.blogapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asus.blogapplication.payloads.ApiResponse;
import com.asus.blogapplication.payloads.CommentDto;
import com.asus.blogapplication.payloads.CommentResponse;
import com.asus.blogapplication.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController { 
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/create/userId/postId/{userId}/{postId}")
	public ResponseEntity<CommentDto> createComment( @RequestBody CommentDto commentdto,
			                                         @PathVariable Long userId,
			                                         @PathVariable Long postId
			                                        )
	
	{
		 return new ResponseEntity<CommentDto>(this.commentService.createComment(commentdto, postId, userId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/commentId/{commentId}") 
		public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully",true), HttpStatus.OK);
			
		}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<CommentResponse> getAllCommentsByPostId (@PathVariable Long userId) {
		return new ResponseEntity<CommentResponse> (this.commentService.getAllCommentsByUserId(userId),HttpStatus.OK);
	}

}
