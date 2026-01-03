package com.customer.crud.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.customer.crud.app.model.CustomerModel;

/**
 * Repository interface for Customer entity data access.
 *
 * Encapsulates all database interactions related to Customer records,
 * including CRUD operations, existence checks, and query-based lookups.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>, JpaSpecificationExecutor<CustomerModel> {

	// Checks if a customer exists with the given email
	boolean existsByEmail(String email);

	// Checks if a customer exists with the given mobile number
	boolean existsByMobile(String mobile);

}