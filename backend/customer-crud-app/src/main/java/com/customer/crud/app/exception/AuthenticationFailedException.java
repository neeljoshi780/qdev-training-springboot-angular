package com.customer.crud.app.exception;

/**
 * AuthenticationFailedException
 *
 * Thrown when user authentication fails due to
 * invalid credentials such as an incorrect
 * username or password.
 *
 * Typically mapped to HTTP 401 (Unauthorized).
 */
public class AuthenticationFailedException extends RuntimeException {

	/**
	 * Creates a new AuthenticationFailedException
	 * with a custom authentication failure message.
	 *
	 * @param message detailed authentication error message
	 */
	public AuthenticationFailedException(String message) {
		super(message);
	}

}