package com.luv2codeinjava.consume.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class APIRestController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String COUNTRY_URL="https://restcountries.eu/rest/v2/all"; 
	
	@GetMapping("/countries")
	public List<Object> getCoutries() {
		Object[] objs = restTemplate.getForObject(COUNTRY_URL, Object[].class);
		return Arrays.asList(objs);
	}
}
