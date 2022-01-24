package com.raj.product.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PRODUCT_DETAILS")
public class Product{
	
	
	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int productId;
	
	@Column(name = "PRODUCT_CODE")
	@Size(min = 2, max=10, message="{productCode.size}")
	@NotBlank(message = "{productCode.notBlank}")
	String productCode;
	
	@Column(name = "SUPPLIER_PRODUCT_CODE")
	String supplierProductCode;
	
	@Pattern(regexp="^([0-9]*)$" , message="{productMRP.pattern}")
	@Column(name = "PRODUCT_MSRP")
	String productMRP;
	
	@Column(name = "PRODUCT_NAME")
	String productName;
	
	@Column(name = "PRODUCT_BRAND")
	String productBrand;
	
	@Column(name = "PRODUCT_PRICE")
	String productPrice;
	
	@Column(name = "PRESCRIPTION_REQUIRED")
    boolean prescription;
	
	@Column(name = "PRODUCT_AVAILABLE")
    boolean isProductAvailable;
	
	@Column(name = "IS_IN_DISCOUNT")
    boolean isProductInDiscount;
	
	@DecimalMax(value = "99", message ="discount percentage should be less than 99" )
	@DecimalMin(value = "0", message ="Discount percentage more than or equal to 0" )
	@Column(name = "DISCOUNT_PERCENTAGE")
	float discountPercentage;
	
	@Column(name = "PRODUCT_STATUS")
	boolean productStatus;
	 
	@Column(name = "TOTAL_PRODUCT_VIEWS")
	String totalProductViews;
	
	@Column(name = "TOTAL_PURCHASES")
	String totalProductPurchases;
	
	@Column(name = "REMAINING_PRODUCT_STOCK")
	String remainingProducts;
	
	@Column(name = "MINIMUM_ORDER")
	String minimumOrder;
	
	@Column(name = "MAXIMUM_ORDER")
	String maximumOrder;
	
	@Column(name = "IMAGE_URL")
	String imageUrl;
	
	@Column(name = "IS_PRODUCT_ENABLED")
	boolean productEnabled;
	
	@Column(name = "IS_IN_SPECIAL_OFFER")
	boolean IsInSpecialOffer;
	
	@Column(name = "SPECIAL_DISCOUNT_PERCENTAGE")
	float specialDiscountPercentage;
	
	@Column(name = "SPECIAL_OFFER_PRICE")
	String specialOfferPrice;
	
	@Column(name = "SPECIAL_OFFER_MINIMUM_ORDER")
	String specialOfferMinOrder;
	
	@Column(name = "SPECIAL_OFFER_MAXIMUM_ORDER")
	String specialOfferMaxOrder;
	
	@Column(name="PRODUCT_NOTE")
	String productNote;
	
	@Column(name="PRODUCT_SHORT_DESC")
	String productShortDesc;
	
	@Column(name="PRODUCT_MEDIUM_DESC")
	String productMediumDesc;
	
	@Column(name="PRODUCT_LONG_DESC")
	String productLongDesc;
	
	@Column(name="PRODUCT_RATING")												
	float productRating;
	
	@Column(name="PRODUCT_CREATED_DATE", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Kolkata" )
	@CreatedDate
	Date productCreatedOn;
	
	@Column(name="PRODUCT_LAST_UPDATED_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern="dd/MM/yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	@LastModifiedDate
	Date productUpdatedOn;
	   
	@Column(name="PRODUCT_UPDATED_MAKER_ID")
	String updatedMakerId;
	
	@Column(name="PRODUCT_UPDATED_AUTHOR_ID")
	String updatedAuthorId;
	
	@Column(name="PRODUCT_CREATED_MAKER_ID")
	String createdMakerId;
	
	@Column(name="PRODUCT_CREATED_AUTHOR_ID")
	String createdAuthorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	@JsonIgnore
	private Category category;

	
}
