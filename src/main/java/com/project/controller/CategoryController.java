package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CategoryDTO;
import com.project.dto.CategoryResponse;
import com.project.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	create a handler to save the category:
	@PostMapping("/save")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		Boolean saveCategory = categoryService.saveCategory(categoryDTO);
		
		if(saveCategory) 
			return new ResponseEntity<>("Category Saved Successfully!", HttpStatus.CREATED);
		
		return new ResponseEntity<>("Failed to save category!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
//	Create a method to get all the categories: ACTIVE & IN ACTIVE BOTH
	@GetMapping("/")
	public ResponseEntity<?> getAllCategories() {
		List<CategoryResponse> categories = categoryService.getAllCategories();
		
		if(CollectionUtils.isEmpty(categories)) 
			return ResponseEntity.noContent().build();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
}
