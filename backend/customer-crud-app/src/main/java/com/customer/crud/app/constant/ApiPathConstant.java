package com.customer.crud.app.constant;

/**
 * Centralized API path constants.
 *
 * Organizes REST end-point paths by resource to ensure
 * consistency and ease of maintenance across controllers.
 */
public final class ApiPathConstant {

	/**
	 * Base path for all REST APIs.
	 * Example: /api
	 */
	public static final String API_BASE = "/api";

	/**
	 * Common path variable for resource identifiers.
	 * Example: /{id}
	 */
	public static final String ID = "/{id}";

	/**
	 * Base path for authentication-related APIs.
	 * Example: /api/auth
	 */
	public static final String AUTH_BASE = API_BASE + "/auth";

	/**
	 * User registration endpoint.
	 * Example: /api/auth/signup
	 */
	public static final String SIGN_UP = "/signup";

	/**
	 * User login endpoint.
	 * Example: /api/auth/signin
	 */
	public static final String SIGN_IN = "/signin";

	/**
	 * Base path for Customer-related APIs.
	 * Example: /api/customer
	 */
	public static final String CUSTOMER_BASE = API_BASE + "/customer";

	// Prevent instantiation
	private ApiPathConstant() {
	}

}