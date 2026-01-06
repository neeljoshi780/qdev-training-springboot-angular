package com.customer.crud.app.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.customer.crud.app.dto.request.CustomerRequestDto;
import com.customer.crud.app.dto.response.CustomerResponseDto;
import com.customer.crud.app.dto.response.PageResponseDto;
import com.customer.crud.app.enums.Gender;
import com.customer.crud.app.exception.DuplicateResourceException;
import com.customer.crud.app.exception.ResourceNotFoundException;
import com.customer.crud.app.model.CustomerModel;
import com.customer.crud.app.repository.CustomerRepository;
import com.customer.crud.app.service.CustomerService;
import com.customer.crud.app.specification.CustomerSpecification;
import com.customer.crud.app.constant.PaginationConstants;
import lombok.RequiredArgsConstructor;

/**
 * Service implementation for Customer-related business operations.
 *
 * Handles customer creation, retrieval, update, deletion,
 * validation, and DTO–entity transformations.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	// Repository dependency for customer data access
	private final CustomerRepository customerRepository;

	// Object mapper for DTO-to-entity conversions
	private final ModelMapper modelMapper;

	/**
	 * Converts Customer entity to response DTO.
	 *
	 * Combines first and last name into a single name field
	 * and merges address lines into a full address.
	 */
	public CustomerResponseDto toDto(CustomerModel customer) {
		if (customer == null) return null;

		CustomerResponseDto dto = modelMapper.map(customer, CustomerResponseDto.class);
		dto.setGender(Gender.fromValue(customer.getGender()).name());
		return dto;
	}

	/**
	 * Converts request DTO to Customer entity.
	 */
	public CustomerModel toEntity(CustomerRequestDto dto) {
		CustomerModel entity = modelMapper.map(dto, CustomerModel.class);

		// Convert gender string → byte
		Byte gender = Gender.fromString(dto.getGender());
		entity.setGender(gender);

		return entity;
	}

	/**
	 * Persists a customer entity and returns mapped response DTO.
	 *
	 * Centralized save method to reuse logic for create and update.
	 */
	private CustomerResponseDto saveCustomer(CustomerModel customer) {
		CustomerModel saved = customerRepository.save(customer);
		return toDto(saved);
	}

	/**
	 * Builds pageable object from page number and page size.
	 *
	 * Applies default values when inputs are null or invalid
	 * and limits page size to a maximum allowed value.
	 */
	private Pageable buildPageable(Long pageNo, Long pageSize, String sortBy, String sortDir) {
		int page = (pageNo == null || pageNo < 0)
			? PaginationConstants.DEFAULT_PAGE_NO
			: pageNo.intValue();

		int size = (pageSize == null || pageSize <= 0)
			? PaginationConstants.DEFAULT_PAGE_SIZE
			: Math.min(pageSize.intValue(), PaginationConstants.MAX_PAGE_SIZE);

		Sort sort = sortDir.equalsIgnoreCase("desc")
			? Sort.by(sortBy).descending()
			: Sort.by(sortBy).ascending();

		return PageRequest.of(page, size, sort);
	}

	/**
	 * Creates a new customer record.
	 */
	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
		if (customerRepository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already registered");
		}

		if (customerRepository.existsByMobile(dto.getMobile())) {
			throw new DuplicateResourceException("Mobile number already registered");
		}

		CustomerModel entity = toEntity(dto);
		return saveCustomer(entity);
	}

	/**
	 * Retrieves a single customer by ID.
	 */
	@Override
	public CustomerResponseDto getCustomer(Long id) {
		CustomerModel customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
		return toDto(customer);
	}

	/**
	 * Retrieves customers using pagination.
	 */
	@Override
	public PageResponseDto<CustomerResponseDto> getCustomers(String search, Long pageNo, Long pageSize, String sortBy, String sortDir) {
		Pageable pageable = buildPageable(pageNo, pageSize, sortBy, sortDir);

		Specification<CustomerModel> spec = CustomerSpecification.globalSearch(search);

		Page<CustomerModel> page = customerRepository.findAll(spec, pageable);

		List<CustomerResponseDto> content = page.getContent()
			.stream()
			.map(this::toDto)
			.toList();

		return new PageResponseDto<>(
			content,
			page.getNumber(),
			page.getSize(),
			page.getTotalElements(),
			page.getTotalPages(),
			page.hasNext(),
			page.hasPrevious()
		);
	}

	/**
	 * Updates an existing customer record.
	 */
	@Override
	public CustomerResponseDto updateCustomer(CustomerRequestDto dto) {
		CustomerModel existing = customerRepository.findById(dto.getId())
			.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + dto.getId()));

		if (!existing.getEmail().equals(dto.getEmail())) {
			if (customerRepository.existsByEmail(dto.getEmail())) {
				throw new DuplicateResourceException("Email already registered with another customer");
			}
		}

		if (!existing.getMobile().equals(dto.getMobile())) {
			if (customerRepository.existsByMobile(dto.getMobile())) {
				throw new DuplicateResourceException("Mobile already registered with another customer");
			}
		}

		modelMapper.map(dto, existing);
		// Convert gender string → byte
		Byte gender = Gender.fromString(dto.getGender());
		existing.setGender(gender);
		return saveCustomer(existing);
	}

	/**
	 * Deletes a customer by ID.
	 */
	@Override
	public void deleteCustomer(Long id) {
		CustomerModel customer = customerRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
		customerRepository.delete(customer);
	}

}
