package com.customer.crud.app.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PageResponseDto
 *
 * A generic DTO used to send paginated response data
 * from the API to the client.
 *
 * @param <T> the type of content in the page
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponseDto<T> {

	// List of records present in the current page.
	private List<T> content;

	// Current page number (zero-based index).
	private int pageNumber;

	// Number of records per page.
	private int pageSize;

	// Total number of records available.
	private long totalElements;

	// Total number of pages available.
	private int totalPages;

	// Indicates whether a next page is available.
	private boolean hasNext;

	// Indicates whether a previous page is available.
	private boolean hasPrevious;

}