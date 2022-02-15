package com.raj.product.service.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:bootstrap.properties")
public class Properties {
	
	@Autowired
	Environment env;
	
	public String getProperty(String key) {
		Optional<String> value = Optional.ofNullable(env.getProperty(key));
		return value.isPresent() ? value.get() : "";
	}
	
	public String getProperty(String key, String defaultMessage) {
		Optional<String> value = Optional.ofNullable(env.getProperty(key));
		return value.isPresent() ? value.get() : defaultMessage;
	}
	
}
