package com.customer.crud.app.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.customer.crud.app.dto.request.CustomerRequestDto;
import com.customer.crud.app.dto.response.CustomerResponseDto;
import com.customer.crud.app.dto.response.PageResponseDto;
import com.customer.crud.app.model.CustomerModel;

/**
 * Customer service contract.
 *
 * Defines business operations for managing Customer data.
 * Implementations are responsible for enforcing business rules
 * and coordinating persistence and validation logic.
 */
public interface CustomerService {

	/**
	 * Creates a new customer record.
	 */
	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

	/**
	 * Retrieves single customer list.
	 */
	CustomerResponseDto getCustomer(Long id);

	/**
	 * Retrieves paginated customer list.
	 */
	PageResponseDto<CustomerResponseDto> getCustomers(Long page, Long size);

	/**
	 * Updates an existing customer record.
	 */
	CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto);

	/**
	 * Deletes a customer by ID.
	 */
	void deleteCustomer(Long id);

}