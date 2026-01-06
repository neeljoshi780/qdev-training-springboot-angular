package com.customer.crud.app.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CustomerResponseDto
 *
 * This DTO is used to send customer details as a response
 * from the API to the client.
 *
 * It represents a read-only view of customer information
 * and does not contain any business logic.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private LocalDate dateOfBirth;

	private String mobile;

	private Byte age;

	private String gender;

	private String email;

}