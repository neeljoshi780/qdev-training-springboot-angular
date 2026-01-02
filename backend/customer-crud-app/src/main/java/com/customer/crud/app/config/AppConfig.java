package com.customer.crud.app.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration class.
 *
 * Defines shared infrastructure beans used across the application,
 * including object mapping configuration.
 */
@Configuration
public class AppConfig {

	/**
	 * Configures and exposes a {@link org.modelmapper.ModelMapper} bean
	 * with strict property matching and null value skipping enabled.
	 *
	 * @return configured {@link org.modelmapper.ModelMapper} instance
	 */
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);	// ignore null DTO fields.
		return modelMapper;
	}

}