package com.customer.crud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Customer CRUD Application.
 *
 * This class bootstraps the Spring Boot application and
 * initializes the application context, auto-configuration, and
 * component scanning.
 */
@SpringBootApplication
public class CustomerCrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCrudAppApplication.class, args);
	}

}