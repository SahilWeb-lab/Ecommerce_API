package com.project.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.project.dto.CategoryDTO;
import com.project.exception.ValidationException;

@Component
public class Validation {

//	Create a method to validate category:
	public void categoryValidation(CategoryDTO categoryDTO) {
		Map<String, Object> errors = new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(categoryDTO)) {
			throw new IllegalArgumentException("Category object/JSON shouldn't be null or empty!");
		} else {
//			Category title validation:
			if(ObjectUtils.isEmpty(categoryDTO.getTitle())) {
				errors.put("title", "Title feild should not be null!");
			}
			
			if(ObjectUtils.isEmpty(categoryDTO.getStatus())) {
				errors.put("status", "Status feild should not be null!");
			}
			
			if(!errors.isEmpty()) {
				throw new ValidationException(errors);
			}
		}
	}
	
}
