package com.customer.crud.app.exception;

/**
 * BadRequestException
 *
 * Thrown when the client sends an invalid request
 * that cannot be processed due to incorrect input,
 * missing data, or invalid parameters.
 *
 * Maps to HTTP 400 (Bad Request).
 */
public class BadRequestException extends RuntimeException {

	/**
	 * Creates a new BadRequestException with a custom message.
	 *
	 * @param message detailed error message
	 */
	public BadRequestException(String message) {
		super(message);
	}

}