package com.luv2codeinjava.spring.cloud.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableHystrix
@EnableWebSecurity
public class GatewayConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
				p -> p.path("/countries").filters(
				f -> f.addRequestHeader("Hello", "World").hystrix(config -> config.setName("consume-api-service ")))
				.uri("http://localhost:8082"))
				
				.route(
						p -> p.path("/api").filters(
						f -> f.addRequestHeader("x-rapidapi-key", "d2335777aemsh4d2c4a240215d3fp155d9ajsn3f33c7246a3e")
							  .addRequestHeader("x-rapidapi-host", "geek-jokes.p.rapidapi.com").hystrix(config -> config.setName("jokes-service")))
						.uri("https://geek-jokes.p.rapidapi.com"))
				
				.route(p -> p.host("*.hystrix.com").filters(
					   f -> f.hystrix(config -> config.setName("mycmd")))
						.uri("http://httpbin.org:80"))
				
				.build();
	}

}
