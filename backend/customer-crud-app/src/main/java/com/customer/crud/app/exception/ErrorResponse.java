package com.customer.crud.app.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ErrorResponse
 *
 * Standard API error response structure.
 * Used for validation errors, business errors, and system errors.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	/**
	 * Timestamp when the error occurred
	 */
	private LocalDateTime timestamp;

	/**
	 * HTTP status code
	 */
	private int status;

	/**
	 * HTTP error name (BAD_REQUEST, NOT_FOUND, etc.)
	 */
	private String error;

	/**
	 * Human-readable error message
	 */
	private String message;

	/**
	 * API path where error occurred
	 */
	private String path;

	/**
	 * Field-level validation errors
	 * key = field name, value = validation message
	 */
	private Map<String, String> fieldErrors;

}