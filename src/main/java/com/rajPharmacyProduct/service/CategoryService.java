package com.rajPharmacyProduct.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajPharmacyProduct.Exceptions.ResourceNotFoundException;
import com.rajPharmacyProduct.model.Category;
import com.rajPharmacyProduct.model.Product;
import com.rajPharmacyProduct.repository.CategoryRepo;

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
