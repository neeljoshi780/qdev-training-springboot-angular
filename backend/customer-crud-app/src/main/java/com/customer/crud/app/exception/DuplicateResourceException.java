package com.customer.crud.app.exception;

/**
 * DuplicateResourceException
 *
 * Thrown when attempting to create or update a resource
 * that violates uniqueness constraints.
 *
 * Example:
 * - Duplicate email
 * - Duplicate mobile number
 *
 * Maps to HTTP 400 (Bad Request).
 */
public class DuplicateResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new DuplicateResourceException with a custom message.
	 *
	 * @param message detailed error message
	 */
	public DuplicateResourceException(String message) {
		super(message);
	}

}