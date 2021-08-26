package com.luv2codeinjava.consume.api.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

public interface CountryService {

	@GetMapping("/rest/v2/all")
	public List<Object> getCountries();
	
	@GetMapping("/products")
	public List<Object> getProducts();
}
