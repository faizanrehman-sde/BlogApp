package com.asus.blogapplication.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asus.blogapplication.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler { 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException x){
		String message=x.getMessage();
		ApiResponse apiresponse= new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> Handler(MethodArgumentNotValidException ex){
		Map<String,String> errors= new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
           errors.put(error.getField(), error.getDefaultMessage());
       }
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}

}
