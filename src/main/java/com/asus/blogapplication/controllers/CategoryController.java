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
import com.asus.blogapplication.payloads.CategoryDto;
import com.asus.blogapplication.serviceImp.CategoryServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImp categoryServiceImp;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createNewCategory(@Valid @RequestBody CategoryDto categorydto){
		this.categoryServiceImp.createCategory(categorydto);
		return new ResponseEntity<ApiResponse>(new ApiResponse("New category created",true),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategoryDto categorydto, @PathVariable Long id){
		this.categoryServiceImp.updateCategory(categorydto, id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Updated succesfully",true), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return new ResponseEntity<List<CategoryDto>>(this.categoryServiceImp.getAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
		return new ResponseEntity<CategoryDto>(this.categoryServiceImp.getcategoryById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletecategoryByid(@PathVariable Long id){
		this.categoryServiceImp.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully",true), HttpStatus.OK);
	}
	
	

}
