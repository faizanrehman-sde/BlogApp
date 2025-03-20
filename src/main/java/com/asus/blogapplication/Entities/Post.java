package com.asus.blogapplication.Entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post { 
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comment= new ArrayList<>();
	
	
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	

	public Post(Long id,
			@NotBlank @Size(min = 5, max = 200, message = "Title of the post must not be empty!!") String title,
			@NotBlank @Size(min = 10, max = 5000, message = "Content must not be empty and must contain atleast 10 words") String content,
			@NotBlank @Size(max = 250) String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt, User user,
			Category category, List<Comment> comment) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

}
