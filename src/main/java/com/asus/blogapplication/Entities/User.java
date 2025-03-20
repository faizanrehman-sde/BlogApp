package com.asus.blogapplication.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	@Size(min=3, max=50, message= "Name must be between 3 and 50 characters")
	private String name;
	
	
	@NotBlank(message="Email is required")
	@Email(message="Email is invalid" )
	@Column(unique = true, nullable= false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String email;
	
	
	@NotBlank(message="Password is required")
	@Size(min=8, max=20, message="password must be between 8 and 20 characters")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Size(max=5000)
	private String about;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> post = new ArrayList<Post>();
    
	@OneToMany
	private List<Comment> comment = new ArrayList<Comment>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	
	

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id,
			@NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name,
			@NotBlank(message = "Email is required") @Email(message = "Email is invalid") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, max = 20, message = "password must be between 8 and 20 characters") String password,
			@Size(max = 5000) String about, List<Post> post, List<Comment> comment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.post = post;
		this.comment = comment;
	}

	

	

	
	
 
	
	
	
}
