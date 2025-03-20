package com.asus.blogapplication.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asus.blogapplication.Entities.User;
import com.asus.blogapplication.exceptions.ResourceNotFoundException;
import com.asus.blogapplication.payloads.UserDto;
import com.asus.blogapplication.repositories.UserRepository;
import com.asus.blogapplication.services.UserService;

@Service
public class UserServiceImp implements UserService {
    
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		User user = this.UserDtoUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.usertodto(savedUser);
		
	}

	@Override
	public String updateUser(UserDto userdto, Long userId) {
		User fetchedUser =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));   
		fetchedUser.setName(userdto.getName());
		fetchedUser.setEmail(userdto.getEmail());
		fetchedUser.setPassword(userdto.getPassword());
		fetchedUser.setAbout(userdto.getAbout());
	     this.userRepo.save(fetchedUser);
	    return "Updated";
	
	}

	@Override
	public UserDto getUserById(Long id) {
		User u =this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
	    return this.usertodto(u);
	}

	@Override
	public List<UserDto> getAllUsers() {
		   List<User> u =this.userRepo.findAll();
		  List<UserDto> userdto1= u.stream().map(e->this.usertodto(e)).collect(Collectors.toList());
	      return userdto1;
	}

	@Override
	public void deleteUser(Long id) {
	   User user=  this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		this.userRepo.delete(user);;
	} 
	
	
	
	private User UserDtoUser(UserDto userdto) {
		User user = this.modelmapper.map(userdto, User.class);
		return user;
	} 
	private UserDto usertodto (User user) {
		UserDto userdto= this.modelmapper.map(user, UserDto.class);
		return userdto;
	}

}
