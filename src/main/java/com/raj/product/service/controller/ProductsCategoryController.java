package com.raj.product.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.product.service.model.Category;
import com.raj.product.service.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/category")
public class ProductsCategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value="/getProducts/{categoryId}/{categoryName}")
	public Optional<Category> getProductsByCategory(@PathVariable int categoryId)
	{
		return categoryService.getCategoryProducts(categoryId);
	}
	
	@PostMapping(value="/addCategory")
	public void addCategory(@RequestBody Category newCategory)
	{
		categoryService.saveNewCategory(newCategory);
	}
}
