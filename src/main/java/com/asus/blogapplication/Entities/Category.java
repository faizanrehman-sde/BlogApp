package com.asus.blogapplication.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="categories")
public class Category { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=100)
    @NotBlank
    @Size(min=3, max=100, message="Title must be between 3 and 100 characters!!")
	private String title;
	
	@Column(length=500)
	@NotBlank
	@Size(min=5,max=500, message="Description must be between 5 and 500 character")
	private String description;
    
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> post=new ArrayList<Post>();
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Long id,
			@NotBlank @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters!!") String title,
			@NotBlank @Size(min = 5, max = 500, message = "Description must be between 5 and 500 character") String description,
			List<Post> post) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.post = post;
	}


	
	

}
