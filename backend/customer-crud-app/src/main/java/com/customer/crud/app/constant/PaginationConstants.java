package com.customer.crud.app.constant;

/**
 * Pagination-related constants used across the application.
 *
 * Defines default values and limits to ensure
 * consistent and safe pagination behavior.
 */
public final class PaginationConstants {

	// Default page index when client does not supply one
	public static final int DEFAULT_PAGE_NO = 0;

	// Default page size when client does not supply one
	public static final int DEFAULT_PAGE_SIZE = 10;

	// Upper bound to prevent excessive memory / DB load
	public static final int MAX_PAGE_SIZE = 100;

	// Prevent instantiation
	private PaginationConstants() {
	}

}