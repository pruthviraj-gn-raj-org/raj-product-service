package com.raj.product.service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.raj.product.service.model.Product;
@Repository
@Transactional
public interface ProductsRepo extends PagingAndSortingRepository<Product, String> {

	@Query(value = "SELECT * FROM PRODUCT_DETAILS WHERE CATEGORY_ID=?1", nativeQuery=true)
	List<Product> getProductsByCategoryId(String categoryId);
	
	@Query(value = "SELECT * FROM PRODUCT_DETAILS WHERE CATEGORY_NAME=?1", nativeQuery=true)
	List<Product> getProductsByCategoryName(String categoryName);
	
	@Modifying
	@Query(value = "DELETE FROM PRODUCT_DETAILS WHERE CATEGORY_ID=?1", nativeQuery=true)
	void deleteProductsByCategoryId(String categoryId);
	/*
	@Query("SELECT p FROM PRODUCT_DETAILS p WHERE CONCAT(p.productName, ' ') LIKE %?1%")
	List<Product> searchProducts(String productName);
	*/
}
