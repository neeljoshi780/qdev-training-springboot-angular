package com.customer.crud.app.exception;

/**
 * ResourceNotFoundException
 *
 * Thrown when a requested resource is not found
 * in the system.
 *
 * Example:
 * - Customer not found by ID
 *
 * Maps to HTTP 404 (Not Found).
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 /**
	 * Creates a new ResourceNotFoundException with a custom message.
	 *
	 * @param message detailed error message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}