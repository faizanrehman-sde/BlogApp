package com.asus.blogapplication.payloads;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDto { 
	
	
    private Long id;
	
	@Column(name="Post_title",nullable = false,length=200)
	@NotBlank
	@Size(min=5,max=200, message = "Title of the post must not be empty!!")
	private String title;
	
	@Column(name="Post_content",nullable = false,length=5000)
	@NotBlank
	@Size(min=10, max =5000, message="Content must not be empty and must contain atleast 10 words")
	private String content;
	
	@Column(nullable = false ,length=250)
	@NotBlank
	@Size(max=250)
	private String imageUrl;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	

	private LocalDateTime updatedAt;
	
	
	
	private UserDto userdto;
	private CategoryDto categorydto;
	//private List<CommentDto> commentdto;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UserDto getUserdto() {
		return userdto;
	}
	public void setUserdto(UserDto userdto) {
		this.userdto = userdto;
	}
	public CategoryDto getCategorydto() {
		return categorydto;
	}
	public void setCategorydto(CategoryDto categorydto) {
		this.categorydto = categorydto;
	}
	
	
//	public List<CommentDto> getCommentdto() {
//		return commentdto;
//	}
//	public void setCommentdto(List<CommentDto> commentdto) {
//		this.commentdto = commentdto;
//	}
	
	
	
	
	
	
	
	
	
	
//	public CommentResponse getCommentResponse() {
//		return commentResponse;
//	}
//	public void setCommentResponse(CommentResponse commentResponse) {
//		this.commentResponse = commentResponse;
//	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostDto(Long id,
			@NotBlank @Size(min = 5, max = 200, message = "Title of the post must not be empty!!") String title,
			@NotBlank @Size(min = 10, max = 5000, message = "Content must not be empty and must contain atleast 10 words") String content,
			@NotBlank @Size(max = 250) String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt,
			UserDto userdto, CategoryDto categorydto) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userdto = userdto;
		this.categorydto = categorydto;
		//this.commentdto = commentdto;
		
	}

	
	

}
