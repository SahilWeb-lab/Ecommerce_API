package com.project.service;

import java.util.List;

import com.project.dto.CategoryDTO;
import com.project.dto.CategoryResponse;

public interface CategoryService {

//	Create a method to save the category:
	public Boolean saveCategory(CategoryDTO categoryDTO);
	
//	Create a method to get all the categories: ACTIVE & IN ACTIVE BOTH:
	public List<CategoryResponse> getAllCategories();
	
}
