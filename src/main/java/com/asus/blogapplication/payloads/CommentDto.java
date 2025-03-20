package com.asus.blogapplication.payloads;

import java.util.List;

public class CommentDto { 
	
	
    private Long id;
	
	private String content;
	

    //private Post post;
	
	private UserDto userdto ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDto getUserdto() {
		return userdto;
	}

	public void setUserdto(UserDto userdto) {
		this.userdto = userdto;
	}

	
	

	
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(Long id, String content, UserDto userdto) {
		super();
		this.id = id;
		this.content = content;
	
		this.userdto = userdto;
	}

	

}     
