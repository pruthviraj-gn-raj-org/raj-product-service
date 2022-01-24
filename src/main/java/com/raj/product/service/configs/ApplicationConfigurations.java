package com.raj.product.service.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SuppressWarnings("deprecation")
@Configuration
@EnableSwagger2WebMvc
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
	
	
	//Swagger Configurations
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

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
