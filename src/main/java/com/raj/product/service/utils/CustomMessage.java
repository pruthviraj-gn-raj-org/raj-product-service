package com.raj.product.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CustomMessage {

	@Autowired
	MessageSource messageSource;
	
	public String getMessage(String key, String defaultMessage) {
		return messageSource.getMessage(key, null, defaultMessage, LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
}
