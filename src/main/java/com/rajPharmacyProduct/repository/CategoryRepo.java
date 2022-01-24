package com.rajPharmacyProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajPharmacyProduct.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
