package com.asus.blogapplication.services;

import java.util.List;

import com.asus.blogapplication.payloads.CommentDto;
import com.asus.blogapplication.payloads.CommentResponse;

public interface CommentService {
 
	    CommentDto createComment(CommentDto content, Long postId, Long userId);
	    void deleteComment(Long commentId);
	    CommentResponse getAllCommentsByUserId (Long UserId);
	    Long totalComments (Long userId);
}
