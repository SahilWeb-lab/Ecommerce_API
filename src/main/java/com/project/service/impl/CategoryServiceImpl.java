package com.project.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.CategoryDTO;
import com.project.dto.CategoryResponse;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
import com.project.exception.ExistDataException;
import com.project.exception.ResourceNotFoundException;
>>>>>>> Stashed changes
import com.project.model.Category;
import com.project.repository.CategoryRepository;
import com.project.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Boolean saveCategory(CategoryDTO categoryDTO) {
<<<<<<< Updated upstream
=======
		validation.categoryValidation(categoryDTO);
		
		Boolean status = categoryRepository.existsByTitle(categoryDTO.getTitle());
		
		if(status) 
			throw new ExistDataException("Category with title [" + categoryDTO.getTitle() +"] already exists!");
		
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
	public CategoryResponse getCategoryById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public Boolean deleteCategory(Integer categoryId) {
		categoryRepository.deleteById(categoryId);
		return true;
	}

}
