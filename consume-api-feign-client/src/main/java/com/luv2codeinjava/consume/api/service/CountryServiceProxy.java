package com.luv2codeinjava.consume.api.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "countryService")
public interface CountryServiceProxy extends CountryService {

}
