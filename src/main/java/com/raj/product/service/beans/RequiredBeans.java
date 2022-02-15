package com.raj.product.service.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.raj.product.service.utils.CustomMessage;
import com.raj.product.service.utils.Validations;

@Configuration
public class RequiredBeans {

	@Bean
	Validations validations() {
		return new Validations();
	}
	
	@Bean
	CustomMessage message() {
		return new CustomMessage();
	}
}
