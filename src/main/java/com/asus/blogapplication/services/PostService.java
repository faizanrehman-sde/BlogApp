package com.asus.blogapplication.services;

import java.util.List;

import com.asus.blogapplication.payloads.PostDto;
import com.asus.blogapplication.payloads.PostResponse;

public interface PostService { 
	
	PostDto createPost (PostDto postdto, Long userid, Long categoryid);
	PostDto updatePost (PostDto postdto, Long PostId);
	PostDto getPostById (Long id);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	void deletePostById(Long PostId);
	
	//custom finder methods
	PostResponse getPostsBycategory(Long categoryid, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	PostResponse getAllPostsByUser(Long userid, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	//search Posts by title 
	List<PostDto> searchPost(String name);
	
}
