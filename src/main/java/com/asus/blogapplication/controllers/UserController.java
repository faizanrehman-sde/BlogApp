package com.asus.blogapplication.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asus.blogapplication.payloads.ApiResponse;
import com.asus.blogapplication.payloads.UserDto;
import com.asus.blogapplication.serviceImp.UserServiceImp;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/users")
public class UserController { 
	
	
	@Autowired
	private UserServiceImp userserviceimp;
	
	//Post- Create user
	@PostMapping("/create")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userdto) {
	    UserDto createduserdto=  this.userserviceimp.createUser(userdto);
		return new ResponseEntity<UserDto>(createduserdto,HttpStatus.CREATED);
	}
	
	//Put-Update user
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser (@Valid @RequestBody UserDto userdto, @PathVariable Long id) {
		String updatedUser=this.userserviceimp.updateUser(userdto, id);
		return new ResponseEntity<String>(updatedUser, HttpStatus.OK);
	}
	
	//Delete- delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
		 this.userserviceimp.deleteUser(id);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	//Get- All users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
    	List<UserDto> retrievedUsers=this.userserviceimp.getAllUsers();
    	return new ResponseEntity<List<UserDto>>(retrievedUsers, HttpStatus.OK);
    }
    
    // Get- Single user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Long id){
    	UserDto fetchedUser=this.userserviceimp.getUserById(id);
    	return new ResponseEntity<UserDto>(fetchedUser,HttpStatus.OK);
    }

}
