package com.asus.blogapplication.services;

import java.util.List;

import com.asus.blogapplication.payloads.CategoryDto;

public interface CategoryService { 
	
	void createCategory (CategoryDto category);
	CategoryDto getcategoryById(Long id);
	List<CategoryDto> getAllCategories();
	void updateCategory(CategoryDto categorry ,Long id);
	void deleteCategory (Long id);
	
	

}
