package com.raj.product.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.model.Category;
import com.raj.product.service.repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	public Optional<Category> getCategoryProducts(int id)
	{
		Optional<Category> categoryProducts= categoryRepo.findById(id);
		if(!categoryProducts.isPresent()) {
			throw new ResourceNotFoundException("Category Not Found");
		}
		else {
			return categoryProducts;
			
		}
	}
	
	public void saveNewCategory(Category newCategory)
	{
		categoryRepo.save(newCategory);
	}
}
