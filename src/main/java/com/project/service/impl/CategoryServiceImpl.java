package com.project.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.dto.CategoryDTO;
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
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category saveCategory = categoryRepository.save(category);
		
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		List<CategoryDTO> categoryDTOs = categories.stream().map(c -> modelMapper.map(c, CategoryDTO.class)).toList();
		
		return categoryDTOs;
	}

}
