package com.customer.crud.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionHandler
 *
 * Centralized exception handling for all REST controllers.
 * Converts exceptions into standardized error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles validation errors triggered by @Valid.
	 *
	 * Returns field-wise validation messages.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> fieldErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		ErrorResponse response = ErrorResponse.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST.value())
			.error(HttpStatus.BAD_REQUEST.name())
			.message("Validation failed")
			.path(request.getRequestURI())
			.fieldErrors(fieldErrors)
			.build();
		return ResponseEntity.badRequest().body(response);
	}

	/**
	 * Handles duplicate resource errors.
	 */
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateResource(DuplicateResourceException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handles resource not found errors.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * Handles bad request errors.
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handles unexpected server errors.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
		return buildErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/**
	 * Handles authentication failure errors.
	 *
	 * Used for login failures such as
	 * invalid username or password.
	 */
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationFailed(AuthenticationFailedException ex, HttpServletRequest request) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED,request);
	}

	/**
	 * Common method to build error response.
	 */
	private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status, HttpServletRequest request) {
		ErrorResponse response = ErrorResponse.builder()
			.timestamp(LocalDateTime.now())
			.status(status.value())
			.error(status.name())
			.message(message)
			.path(request.getRequestURI())
			.build();
		return ResponseEntity.status(status).body(response);
	}

}