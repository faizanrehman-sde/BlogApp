package com.asus.blogapplication.services;
import java.util.List;

import com.asus.blogapplication.payloads.UserDto;

public interface UserService { 
	
	
	UserDto createUser(UserDto user);
	String updateUser(UserDto user, Long userid );
	UserDto getUserById (Long id);
	List<UserDto> getAllUsers();
	void deleteUser(Long id);

}
