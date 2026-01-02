package com.customer.crud.app.dto.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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

	private String name;

	private String address1;

	private String address2;

	private String address;

	private LocalDate dateOfBirth;

	private String mobile;

	private Byte age;

	private Byte gender;

	private String email;

}