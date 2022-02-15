package com.raj.product.service.utils;

import java.util.Optional;

import com.raj.product.service.exception.InvalidInputRequestException;

public class Validations {
	
	public static  void isNullOrEmpty(String value, String message){
		Optional.ofNullable(value).orElseThrow(()-> new InvalidInputRequestException(message));
	}

}
