package com.raj.product.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.exception.SqlOperationsException;
import com.raj.product.service.model.Product;
import com.raj.product.service.repository.ProductsRepo;

@Service
public class ProductService {

	@Autowired
	ProductsRepo productsRepo;
	
	public List<Product> getAllProducts()
	{
		List<Product> products = (List<Product>) productsRepo.findAll();
		return products;
	}
	
	public List<Product> getProductsByPagination(int pageNumbr, int totalCount)
	{
		Pageable paging = PageRequest.of(pageNumbr, totalCount);
		Page<Product> pageResult = productsRepo.findAll(paging);
		return pageResult.toList();
	}
	
	public List<Product> getProductsByPaginationSorted(int pageNumber, int totalPageSize, String field)
	{
		Pageable paging = PageRequest.of(pageNumber, totalPageSize, Sort.by(Sort.Direction.ASC , field));
		Page<Product> pageResult = productsRepo.findAll(paging);
		return pageResult.toList();
	}
	
	public List<Product> getSortedProducts(String field)
	{
		List<Product> products = (List<Product>) productsRepo.findAll(Sort.by(Sort.Direction.ASC,field));
		return products;
	}
	
	public Optional<Product> getProductById(String productId)
	{
		Optional<Product> product= productsRepo.findById(productId);
		return product;
	}
	
	
	
	public void updateProduct(Product productetails)
	{
		Optional<Product> optionalProduct= productsRepo.findById(productetails.getProductId());
		optionalProduct.orElseThrow(()-> new  ResourceNotFoundException("Product Not Found to Update with id: "+productetails.getProductId()));
		productsRepo.save(productetails);
	}
	
	public List<Product> getProductsByCategoryId(String categoryId)
	{
		List<Product> categoryProducts = productsRepo.getProductsByCategoryId(categoryId);
		return categoryProducts;	
	}
	
	public List<Product> getProductsByCategoryName(String categoryName)
	{
		List<Product> categoryProducts = productsRepo.getProductsByCategoryName(categoryName);
		return categoryProducts;	
	}
	
	public Product saveNewProduct(Product newProduct)
	{	
		Product savedProduct = productsRepo.save(newProduct);
		return savedProduct;
	}
	
	public Iterable<Product> saveMultipleProduct(List<Product> newProduct)
	{	
		Iterable<Product> savedProduct = productsRepo.saveAll(newProduct);
		return savedProduct;
	}
	
	public void deleteProduct(String productId)
	{
		try {
		productsRepo.deleteById(productId);
		}
		catch(Exception ex)
		{
			throw new ResourceNotFoundException("There is No Product To delete with id: "+productId);
		}
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
/*
	public List<Product> searchProducts(String query) {
		return productsRepo.searchProducts(query);
	}
*/
	

}
