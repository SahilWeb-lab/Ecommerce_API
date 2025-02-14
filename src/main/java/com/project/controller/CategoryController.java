package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CategoryDTO;
import com.project.dto.CategoryResponse;
import com.project.exception.ResourceNotFoundException;
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
	
	
//	Create a method to get the category:
	@GetMapping("/{cid}")
	public ResponseEntity<?> getCategoryById(@PathVariable Integer cid) throws ResourceNotFoundException {
		CategoryResponse categoryResponse = categoryService.getCategoryById(cid);
		
		if(ObjectUtils.isEmpty(categoryResponse))
			return new ResponseEntity<>("No category found with id [" + cid + "]", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
	}
	
//	Create a method to delete the category:
	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer cid) throws ResourceNotFoundException {
		Boolean deleteCategory = categoryService.deleteCategory(cid);
		
		if(deleteCategory)
			return new ResponseEntity<>("Category Deleted Successfully!", HttpStatus.OK);
		
		return new ResponseEntity<>("Failed to delete category!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
