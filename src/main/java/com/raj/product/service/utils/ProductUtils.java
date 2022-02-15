package com.raj.product.service.utils;

import static com.raj.product.service.utils.Validations.isNullOrEmpty;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.raj.product.service.exception.ResourceNotFoundException;
import com.raj.product.service.model.Product;

public class ProductUtils {

	
}
