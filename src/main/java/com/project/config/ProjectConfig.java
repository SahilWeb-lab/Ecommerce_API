package com.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

//	Create a model mapper bean:
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
