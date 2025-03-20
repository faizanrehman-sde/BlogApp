package com.asus.blogapplication.payloads;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommentResponse { 
	
	private Long totalComment;
	
	List<CommentDto> comment;
	
	
	
	public List<CommentDto> getComment() {
		return comment;
	}
	public void setComment(List<CommentDto> comment) {
		this.comment = comment;
	}
	public Long getTotalComment() {
		return totalComment;
	}
	public void setTotalComment(Long totalComment) {
		this.totalComment = totalComment;
	}
	public CommentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public CommentResponse(Long totalComment, List<CommentDto> comment, CommentResponse commentResponse) {
		super();
		this.totalComment = totalComment;
		this.comment = comment;
		this.commentResponse = commentResponse;
	}





	private CommentResponse commentResponse;
	public CommentResponse getCommentResponse() {
	return commentResponse;
  }
   public void setCommentResponse(CommentResponse commentResponse) {
	this.commentResponse = commentResponse;
   }

}
