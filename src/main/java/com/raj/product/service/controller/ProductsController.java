package com.raj.product.service.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.model.Product;
import com.raj.product.service.proxy.PaymentServiceProxy;
import com.raj.product.service.service.ProductService;
import com.raj.product.service.utils.CustomMessage;
import static com.raj.product.service.utils.Validations.isNullOrEmpty;

@RestController
@Validated
@RequestMapping("/products")
public class ProductsController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomMessage message;
	
	PaymentServiceProxy paymentProxy;
	
	/*
	String requiredProdId = message.getMessage("product.id.required", "Product ID Required");
	String requiredCategId = message.getMessage("category.id.required", "Category ID Required");
	String requiredCategName = message.getMessage("Category.name.required", "Category Name Required");
	String noProducts = message.getMessage("products.not.found", "No Products Found");
	String pageNoRequired = message.getMessage("page.number.required", "Page Number Required");
	String pageCountRequired = message.getMessage("count.per.page.required", "Total Count per Page Required");
	*/
	String requiredProdId = "Product ID Required";
	String requiredCategId ="Category ID Required";
	String requiredCategName ="Category Name Required";
	String noProducts = "No Products Found";
	String pageNoRequired ="Page Number Required";
	String pageCountRequired = "Total Count per Page Required";
	
	@PutMapping("/update")
	public void updateProduct(@Valid @RequestBody Product productDetails)
	{
		isNullOrEmpty(productDetails.getProductId(), requiredProdId);
		productService.updateProduct(productDetails);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product newProduct)
	{
		Product	savedProduct = productService.saveNewProduct(newProduct);
		WebMvcLinkBuilder link=	linkTo(methodOn(this.getClass()).getProductWithId(savedProduct.getProductId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(link.toUri());
	}
	
	@PostMapping("/addMultiple")
	public ResponseEntity<List<URI>> saveMultipleProduct(@Valid @RequestBody List<Product> newProduct)
	{
		Iterable<Product>	savedProducts = productService.saveMultipleProduct(newProduct);
		List<URI> uriList =  new ArrayList<>();
		
		for(Product product: savedProducts) {
			WebMvcLinkBuilder link=	linkTo(methodOn(this.getClass()).getProductWithId(product.getProductId()));
			URI	location = link.toUri();
			uriList.add(location);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(uriList);
	}
	
	@DeleteMapping("/delete/{productId}")
	public void deleteProduct(@PathVariable String productId)
	{
		isNullOrEmpty(productId, requiredProdId);
		productService.deleteProduct(productId);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<EntityModel<Product>> getProductWithId(@PathVariable String productId)
	{
			isNullOrEmpty(productId, requiredProdId);
			Optional<Product> optionalProduct = productService.getProductById(productId);
			Product product = optionalProduct.orElseThrow(()-> new ResourceNotFoundException("Product Not Found for the id: "+productId));
			EntityModel<Product> resource= EntityModel.of(product);
			WebMvcLinkBuilder link=	linkTo(methodOn(this.getClass()).getProductWithId(product.getProductId()));
			resource.add(link.withRel("selfLink"));
			return ResponseEntity.ok().body(resource);	
	}
	
	@GetMapping(value="/AllProducts")
	public ResponseEntity<List<EntityModel<Product>>> getAllProducts()
	{
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getAllProducts());
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
	}
	
	/*
	@GetMapping(value="/search/{query}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String query)
	{
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.searchProducts(query));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok(products);
	}
	*/
	
	@GetMapping(value="/sortedBy/{field}")
	public ResponseEntity<List<EntityModel<Product>>> getSortedProducts(@PathVariable String field)
	{
		isNullOrEmpty(field,"Field Required To Sort");
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getSortedProducts(field));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
	}
	
	@GetMapping(value="/pagination/{pageNumber}/{count}/sortedBy/{field}")
	public ResponseEntity<List<EntityModel<Product>>> getProductsByPaginationSorted(@PathVariable String pageNumber, @PathVariable String count, @PathVariable String field)
		{
		isNullOrEmpty(field,"Field Required To Sort"); isNullOrEmpty(pageNumber, pageNoRequired); isNullOrEmpty(count, pageCountRequired);
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getProductsByPaginationSorted(
				Integer.parseInt(pageNumber), 
				Integer.parseInt(count), 
				field));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
		}
	
	@GetMapping(value="/pagination/{pageNumber}/{count}")
	public ResponseEntity<List<EntityModel<Product>>> getProductsByPagination(@PathVariable String pageNumber, @PathVariable String count)
	{
		isNullOrEmpty(pageNumber, pageNoRequired); isNullOrEmpty(count, pageCountRequired);
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getProductsByPagination(Integer.parseInt(pageNumber), Integer.parseInt(count)));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
	}
	
	@GetMapping("/byCategoryId/{categoryId}")
	public ResponseEntity<List<EntityModel<Product>>> getProductsByCategoryId(@PathVariable String categoryId)
	{
		isNullOrEmpty(categoryId , requiredCategId);
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getProductsByCategoryId(categoryId));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
	}
	
	@GetMapping("/byCategoryName/{categoryName}")
	public ResponseEntity<List<EntityModel<Product>>> getProductsByCategoryName(@PathVariable String categoryName)
	{
		isNullOrEmpty(categoryName , requiredCategName);
		Optional<List<Product>> optionalProducts = Optional.ofNullable(productService.getProductsByCategoryName(categoryName));
		List<Product> products = optionalProducts.orElseThrow(() ->  new ResourceNotFoundException(noProducts)); 
		return ResponseEntity.ok().body(getSelfLink(products));
	}
	
	@DeleteMapping("delete/byCategory/{categoryId}")
	public void deleteProductsByCategory(@PathVariable String categoryId)
	{
		isNullOrEmpty(categoryId, requiredCategId);
		productService.deleteProductsByCategory(categoryId);
	}
	
	@PutMapping("update/byCategory/{categoryId}")
	public void updateProductsByCategory(@PathVariable String categoryId)
	{
		isNullOrEmpty(categoryId , requiredCategId);
		//productService.updateProductsByCategory(categoryId);
	}
	
	private List<EntityModel<Product>> getSelfLink(List<Product> products) {
		
		return products.stream()
				.map(product -> EntityModel.of(product).add(linkTo(methodOn(this.getClass()).getProductWithId(product.getProductId())).withRel("selfLink")))
				.collect(Collectors.toList());
	}
	
}
