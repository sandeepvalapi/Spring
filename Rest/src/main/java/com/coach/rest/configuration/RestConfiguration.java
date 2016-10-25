package com.coach.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.coach.rest")
public class RestConfiguration {
	// If you want to register any Beans
	// Its equal to XML bean config

	// @Bean
	// public DataSource dataSource() { }
}