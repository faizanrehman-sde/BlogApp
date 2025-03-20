package com.asus.blogapplication.payloads;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostResponse {
	
	private List<PostDto> content;
	private CommentResponse commentResponse;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private boolean lastPage;
	public List<PostDto> getContent() {
		return content;
	}
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentResponse getCommentResponse() {
		return commentResponse;
	}
	public void setCommentResponse(CommentResponse commentResponse) {
		this.commentResponse = commentResponse;
	}
	public PostResponse(List<PostDto> content, CommentResponse commentResponse, Integer pageNumber, Integer pageSize,
			Long totalElements, Integer totalPages, boolean lastPage) {
		super();
		this.content = content;
		this.commentResponse = commentResponse;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	

}
