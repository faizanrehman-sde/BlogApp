package com.asus.blogapplication.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asus.blogapplication.Entities.Category;
import com.asus.blogapplication.exceptions.ResourceNotFoundException;
import com.asus.blogapplication.payloads.CategoryDto;
import com.asus.blogapplication.repositories.CategoryRepository;
import com.asus.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
     
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public void createCategory(CategoryDto categorydto) {
		Category newcategory =this.convertToCategory(categorydto);
		 this.categoryRepo.save(newcategory);
	}

	@Override
	public CategoryDto getcategoryById(Long id) {
		Category fetchedCategory=this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", id));
		return this.convertToCategoryDto(fetchedCategory);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> category = this.categoryRepo.findAll();
	    return category.stream().map(e ->this.convertToCategoryDto(e)).collect(Collectors.toList());
	}

	@Override
	public void updateCategory(CategoryDto categorydto, Long id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Id",id));
		category.setTitle(categorydto.getTitle());
		category.setDescription(category.getDescription());
		this.categoryRepo.save(category);
		
	}

	@Override
	public void deleteCategory(Long id) {
		Category category= this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id",id));
		this.categoryRepo.delete(category);
		
	}  
	
	private Category convertToCategory (CategoryDto categorydto) {
		Category category = this.modelMapper.map(categorydto, Category.class);
		return category;
	}
	
	private CategoryDto convertToCategoryDto(Category category) {
		CategoryDto categorydto = this.modelMapper.map(category, CategoryDto.class);
		return categorydto;
	}
	
	
	

}
