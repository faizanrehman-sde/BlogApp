package com.asus.blogapplication.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	
   private Long id;
	
	@Column(unique=true, length=100)
    @NotBlank
    @Size(min=3, max=100, message="Title must be between 3 and 100 characters!!")
	private String title;
	
	@Column(length=500)
	@NotBlank
	@Size(min=5,max=500, message="Description must be between 5 and 500 character")
	private String description;

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

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Long id,
			@NotBlank @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters!!") String title,
			@NotBlank @Size(min = 5, max = 500, message = "Description must be between 5 and 500 character") String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}
   
	
}
