package com.project.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.CategoryDTO;
import com.project.dto.CategoryResponse;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Category;
import com.project.repository.CategoryRepository;
import com.project.service.CategoryService;
import com.project.util.Validation;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Validation validation;

	@Override
	public Boolean saveCategory(CategoryDTO categoryDTO) {
		validation.categoryValidation(categoryDTO);
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category saveCategory = categoryRepository.save(category);
		
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id")); 
		List<CategoryResponse> categoryResponse = categories.stream().map(c -> modelMapper.map(c, CategoryResponse.class)).toList();
		
		return categoryResponse;
	}

	@Override
	public CategoryResponse getCategoryById(Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id [" + categoryId +"]"));
		CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public Boolean deleteCategory(Integer categoryId) throws ResourceNotFoundException {
		categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id [" + categoryId + "]"));
		
		categoryRepository.deleteById(categoryId);
		return true;
	}

}
