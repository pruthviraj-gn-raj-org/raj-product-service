package com.raj.product.service.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfigurations {
	
	// Configurations for messageSource
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.raj.product.service")).build();
	   }
	
	//bean for path and request parameter validations
	 @Bean
	    public MethodValidationPostProcessor methodValidationPostProcessor() {
	         return new MethodValidationPostProcessor();
	    }
	 
	//bean for ribbon client load balancing
// @Bean
	  //  public ServerList<Server> ribbonServerList() {
	    //    // return new ConfigurationBasedServerList();
	     //   StaticServerList<Server> staticServerList = new StaticServerList<>((new Server("localhost", 8101)),
	     //           new Server("localhost", 8102));
	    //    return staticServerList;
	   // }
	//
}
