package com.raj.product.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.exception.SqlOperationsException;
import com.raj.product.service.model.Product;
import com.raj.product.service.repository.ProductsRepo;

@Service
public class ProductService {

	@Autowired
	ProductsRepo productsRepo;
	public List<Product> AllProducts()
	{
		List<Product> products=productsRepo.findAll();
		if(products.isEmpty())
		{
			throw new ResourceNotFoundException("No Products");
		}
		return products;
	}
	
	public Product saveNewProduct(Product newProduct)
	{	
		Product savedProduct = productsRepo.save(newProduct);
		if(savedProduct.getProductId()==0)
		{
		throw new SqlOperationsException("Something went Wrong. Failed to Insert new Product");
		}
		return savedProduct;
	}
	
	public void deleteProduct(String productId)
	{
		try {
		productsRepo.deleteById(Integer.parseInt(productId));
		}
		catch(Exception ex)
		{
			throw new ResourceNotFoundException("There is No Product To delete with id: "+productId);
		}
	}
	
	public Optional<Product> getProductById(String productId)
	{
		Optional<Product> product= productsRepo.findById(Integer.parseInt(productId));
		if(!product.isPresent())
		{
			throw new ResourceNotFoundException("Product Not Found for the id: "+productId);
		}
		
			return product;
	}
	
	public void updateProduct(Product product,String productId)
	{
		Optional<Product> getProduct= productsRepo.findById(Integer.parseInt(productId));
		if(!getProduct.isPresent())
		{
			throw new ResourceNotFoundException("Product Not Found to Update with id: "+productId);
		}
		product.setProductId(getProduct.get().getProductId());
		productsRepo.save(product);
	}
	
	public List<Product> getProductsByCategory(String categoryId)
	{
		List<Product> categoryProducts=productsRepo.getProductsByCategoryId(categoryId);
		if(categoryProducts.isEmpty())
		{
			throw new ResourceNotFoundException("No products found for category "+categoryId);
		}
		return categoryProducts;	
	}
	
	public void deleteProductsByCategory(String categoryId)
	{
		try {
		productsRepo.deleteProductsByCategoryId(categoryId);
		}
		catch(Exception ex)
		{
			throw new ResourceNotFoundException("No products found to delete for category "+categoryId);
		}
	}

	

}
