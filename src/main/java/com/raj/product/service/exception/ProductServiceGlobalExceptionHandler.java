package com.raj.product.service.exception;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductServiceGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		ExceptionsModel exceptionsModel = new ExceptionsModel(exception.getMessage(), new Date(),HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),request.getDescription(false),exception.getLocalizedMessage());
		
		return new ResponseEntity<>(exceptionsModel, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionsModel exceptionsModel = new ExceptionsModel("Validation Failed", new Date(),HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),request.getDescription(false),ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(exceptionsModel, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SqlOperationsException.class)
	public ResponseEntity<Object> handleSqlOperationsException(SqlOperationsException exception, WebRequest request) {
		ExceptionsModel exceptionsModel = new ExceptionsModel(exception.getMessage(), new Date(),HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),request.getDescription(false),exception.getLocalizedMessage());
		
		return new ResponseEntity<>(exceptionsModel, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handlePathAndRequestParamsErrors(ConstraintViolationException exception, WebRequest request) {
		ExceptionsModel exceptionsModel = new ExceptionsModel(exception.getMessage(), new Date(),HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),request.getDescription(false),exception.getLocalizedMessage());
		
		return new ResponseEntity<>(exceptionsModel, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidInputRequestException.class)
	public ResponseEntity<Object> handleInvalidInputRequestException(InvalidInputRequestException exception, WebRequest request) {
		ExceptionsModel exceptionsModel = new ExceptionsModel(exception.getMessage(), new Date(),HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),request.getDescription(false),exception.getLocalizedMessage());
	
		return new ResponseEntity<>(exceptionsModel, HttpStatus.BAD_REQUEST);
	}
	
}
