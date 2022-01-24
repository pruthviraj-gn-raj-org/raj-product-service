package com.rajPharmacyProduct.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:bootstrap.properties")
public class PropertiesConfig {
	@Autowired
	Environment env;
	
	public String getValueForKey(String key)
	{
		return env.getProperty(key);
	}
	
}
