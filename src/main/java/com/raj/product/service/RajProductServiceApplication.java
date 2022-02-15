package com.raj.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.raj.product.service.configs.ApplicationConfigurations;

import brave.sampler.Sampler;

@SpringBootApplication
//@EnableResourceServer
@EnableJpaAuditing
@EnableFeignClients("com.rajPharmacyProduct.*")
@RibbonClient(name = "raj-product-service", configuration = ApplicationConfigurations.class)
@EnableDiscoveryClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RajProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RajProductServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}
	
}
