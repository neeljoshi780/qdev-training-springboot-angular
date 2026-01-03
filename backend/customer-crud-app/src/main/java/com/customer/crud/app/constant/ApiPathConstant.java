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
	 * Reused across multiple controllers.
	 * Example: /{id}
	 */
	public static final String ID = "/{id}";

	/**
	 * Base path for Customer-related APIs.
	 * Example: /api/customer
	 */
	public static final String CUSTOMER = API_BASE + "/customer";

	// Prevent instantiation
	private ApiPathConstant() {
	}

}