package com.raj.product.service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raj.product.service.model.Product;
@Repository
@Transactional
public interface ProductsRepo extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM PRODUCT_DETAILS WHERE CATEGORY_ID=?1", nativeQuery=true)
	List<Product> getProductsByCategoryId(String categoryId);
	
	@Modifying
	@Query(value = "DELETE FROM PRODUCT_DETAILS WHERE CATEGORY_ID=?1", nativeQuery=true)
	void deleteProductsByCategoryId(String categoryId);
	
}
