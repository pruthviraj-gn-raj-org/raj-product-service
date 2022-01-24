package com.raj.product.service.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.raj.product.service.model.Simple;

@FeignClient(name="raj-zuul-service")
@RibbonClient(name="raj-payment-service")
public interface PaymentServiceProxy {

	@GetMapping("/raj-payment-service/welcome/{id}/{name}/{gender}")
	public Simple getSimple(@PathVariable("id") String id,@PathVariable("name") String name,@PathVariable("gender") String gender );
}
