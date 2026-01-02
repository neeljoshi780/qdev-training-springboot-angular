package com.customer.crud.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.crud.app.dto.request.CustomerRequestDto;
import com.customer.crud.app.dto.response.CustomerResponseDto;
import com.customer.crud.app.dto.response.PageResponseDto;
import com.customer.crud.app.service.CustomerService;

import com.customer.crud.app.constant.ApiPathConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for Customer resources.
 *
 * Provides APIs to create, retrieve (with pagination),
 * update, and delete customer records. Uses DTOs to
 * decouple API contracts from persistence models.
 */
@RestController
@RequestMapping(ApiPathConstant.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {

	// Service dependency for customer-related business operations
	private final CustomerService customerService;

	/**
	 * Creates a new customer.
	 *
	 * @param customerDto Customer request pay-load
	 * @return Created customer details
	 */
	@PostMapping
	public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
		CustomerResponseDto createdCustomer = customerService.createCustomer(customerRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	}

	/**
	 * Retrieves customers using pagination.
	 *
	 * @param page Zero-based page index
	 * @param size Number of records per page
	 * @return Paginated customer list with metadata
	 */
	@GetMapping
	public ResponseEntity<PageResponseDto<CustomerResponseDto>> getCustomers(
		@RequestParam(defaultValue = "0") Long pageNo,
		@RequestParam(defaultValue = "10") Long pageSize,
		@RequestParam(defaultValue = "id") String sortBy,
		@RequestParam(defaultValue = "asc") String sortDir
	) {
		PageResponseDto<CustomerResponseDto> customers = customerService.getCustomers(pageNo, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(customers);
	}

	/**
	 * Retrieves a single customer by ID.
	 *
	 * @param id Customer ID
	 * @return Customer details
	 */
	@GetMapping(ApiPathConstant.ID)
	public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
		CustomerResponseDto customer = customerService.getCustomer(id);
		return ResponseEntity.ok(customer);
	}

	/**
	 * Updates an existing customer.
	 *
	 * @param id Customer ID
	 * @param customerDto Updated customer data
	 * @return Updated customer details
	 */
	@PutMapping(ApiPathConstant.ID)
	public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto dto) {
		dto.setId(id);
		CustomerResponseDto updatedCustomer = customerService.updateCustomer(dto);
		return ResponseEntity.ok(updatedCustomer);
	}

	/**
	 * Deletes a customer by ID.
	 *
	 * @param id Customer ID
	 */
	@DeleteMapping(ApiPathConstant.ID)
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}

}