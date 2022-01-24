package com.raj.product.service.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.raj.product.service.configs.PropertiesConfig;
import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.model.Product;
import com.raj.product.service.model.Simple;
import com.raj.product.service.proxy.PaymentServiceProxy;
import com.raj.product.service.service.ProductService;

@RestController
@Validated
@RequestMapping("/products")
public class ProductsController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductService productService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	PropertiesConfig getProperty;
	
	@Autowired
	PaymentServiceProxy paymentProxy;
	
	@GetMapping(value="/welcome/{id}/{name}/{gender}")
	public Simple welcome(@PathVariable String id,@PathVariable String name,@PathVariable String gender )
	{
		System.out.println("inside products service controller");
		logger.info("inside products controller");
		//Map<String,String> uriVariables= new HashMap();	
		//uriVariables.put("id", id);
		//uriVariables.put("name", name);
		//uriVariables.put("gender", gender);
		//ResponseEntity<Simple> response=new RestTemplate().getForEntity("http://localhost:8101/welcome/{id}/{name}/{gender}", Simple.class, uriVariables);
		//return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
		Simple response=paymentProxy.getSimple(id, name, gender);
		return response;
	}
	
	@GetMapping(value="/check")
	@PreAuthorize("hasAuthority('create_profile')")
	public String simple()
	{
		return "role based access";
	}
	
	@RequestMapping(value="/AllProducts")
	public List<Product> getAllProducts()
	{
		List<Product> products=productService.AllProducts();
		if(products==null)
		{
			throw new ResourceNotFoundException("No products found");
		}
		return products;
	}
	
	@PutMapping("/{productId}")
	public void UpdateProduct(@Valid @RequestBody Product product, @PathVariable(required = true) String productId)
	{
		productService.updateProduct(product,productId);
	}
	
	@PostMapping
	public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product newProduct)
	{
		Product	savedProduct=productService.saveNewProduct(newProduct);
		URI	location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/{name}").buildAndExpand(savedProduct.getProductId(),savedProduct.getProductName()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{productId}/{productName}")
	public EntityModel<Optional<Product>> getProduct(
			@PathVariable(required = true) @Size(max = 4, message = "product id lesser than 4") String productId,
			@PathVariable(required = false)  @Size(max = 4, message = "product name lesser than 4") String productName)
	{
			Optional<Product> product = productService.getProductById(productId);
			EntityModel<Optional<Product>> resource= EntityModel.of(product);
			WebMvcLinkBuilder link=	linkTo(methodOn(this.getClass()).getProduct(product.get().getProductId()+"",product.get().getProductName()));
			resource.add(link.withRel("selfLink"));
			return resource;	
	}
	
	@DeleteMapping("{productId}/{productName}")
	public void deleteProduct(@PathVariable(required = true) String productId,  @PathVariable(required = false) String productName)
	{
		productService.deleteProduct(productId);
	}
	
	@GetMapping("/byCategory/{categoryId}/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable(required = true) String categoryId, @PathVariable(required = false) String categoryName)
	{
		return productService.getProductsByCategory(categoryId);
	}
	
	@DeleteMapping("/byCategory/{categoryId}/{categoryName}")
	public void deleteProductsByCategory(@PathVariable(required = true) String categoryId, @PathVariable(required = false) String categoryName)
	{
		productService.deleteProductsByCategory(categoryId);
	}
	
}
