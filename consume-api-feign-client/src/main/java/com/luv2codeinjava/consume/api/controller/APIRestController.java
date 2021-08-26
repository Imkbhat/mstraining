package com.luv2codeinjava.consume.api.controller;

import java.util.List;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2codeinjava.consume.api.service.CountryServiceProxy;

@RestController
@EnableFeignClients(basePackageClasses = CountryServiceProxy.class)
public class APIRestController implements CountryServiceProxy {
	
	private CountryServiceProxy countryServiceProxy;
	
	public APIRestController(CountryServiceProxy countryServiceProxy) {
		this.countryServiceProxy = countryServiceProxy;
	}

	@Override
	@GetMapping("/rest/v2/all")
	public List<Object> getCountries() {
		return countryServiceProxy.getCountries();
	}

	@Override
	@GetMapping("/products")
	public List<Object> getProducts() {
		return countryServiceProxy.getProducts();
	}

}
