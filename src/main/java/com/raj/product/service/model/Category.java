package com.raj.product.service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_DETAILS")
public class Category {

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int categoryId;
	
	@Column(name="CATEGORY_NAME")
	String categoryName;
	
	@Column(name="CATEGORY_DESCRIPTION")
	String categoryaadescription;
	
	@Column(name="CATEGORY_IMAGE_URL")
	String categoryImageUrl;
	
	@Column(name="IS_CATEGORY_ENABLED")
	boolean isCategoryEnabled;
	
	@Column(name="CATEGORY_CREATED_DATE")
	Date categoryCreatedOn;
	
	@Column(name="CATEGORY_LAST_UPDATED_DATE")
	Date categoryUpdatedOn;

	@Column(name="CATEGORY_UPDATED_MAKER_ID")
	String categoryUpdatedMakerId;
	
	@Column(name="CATEGORY_UPDATED_AUTHOR_ID")
	String categoryUpdatedAuthorId;
	
	@Column(name="CATEGORY_CREATED_MAKER_ID")
	String categoryCreatedMakerId;
	
	@Column(name="CATEGORY_CREATED_AUTHOR_ID")
	String categoryCreatedAuthorId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<Product>();

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryaadescription() {
		return categoryaadescription;
	}

	public void setCategoryaadescription(String categoryaadescription) {
		this.categoryaadescription = categoryaadescription;
	}

	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}

	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}

	public boolean isCategoryEnabled() {
		return isCategoryEnabled;
	}

	public void setCategoryEnabled(boolean isCategoryEnabled) {
		this.isCategoryEnabled = isCategoryEnabled;
	}

	public Date getCategoryCreatedOn() {
		return categoryCreatedOn;
	}

	public void setCategoryCreatedOn(Date categoryCreatedOn) {
		this.categoryCreatedOn = categoryCreatedOn;
	}

	public Date getCategoryUpdatedOn() {
		return categoryUpdatedOn;
	}

	public void setCategoryUpdatedOn(Date categoryUpdatedOn) {
		this.categoryUpdatedOn = categoryUpdatedOn;
	}

	public String getCategoryUpdatedMakerId() {
		return categoryUpdatedMakerId;
	}

	public void setCategoryUpdatedMakerId(String categoryUpdatedMakerId) {
		this.categoryUpdatedMakerId = categoryUpdatedMakerId;
	}

	public String getCategoryUpdatedAuthorId() {
		return categoryUpdatedAuthorId;
	}

	public void setCategoryUpdatedAuthorId(String categoryUpdatedAuthorId) {
		this.categoryUpdatedAuthorId = categoryUpdatedAuthorId;
	}

	public String getCategoryCreatedMakerId() {
		return categoryCreatedMakerId;
	}

	public void setCategoryCreatedMakerId(String categoryCreatedMakerId) {
		this.categoryCreatedMakerId = categoryCreatedMakerId;
	}

	public String getCategoryCreatedAuthorId() {
		return categoryCreatedAuthorId;
	}

	public void setCategoryCreatedAuthorId(String categoryCreatedAuthorId) {
		this.categoryCreatedAuthorId = categoryCreatedAuthorId;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
	
	
}
