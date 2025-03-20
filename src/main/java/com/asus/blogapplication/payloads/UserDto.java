package com.asus.blogapplication.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto { 
	
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

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id,
			@NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name,
			@NotBlank(message = "Email is required") @Email(message = "Email is invalid") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, max = 20, message = "password must be between 8 and 20 characters") String password,
			@Size(max = 5000) String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	
 

}
