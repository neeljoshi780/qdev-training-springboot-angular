package com.customer.crud.app.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for customer create and update requests.
 *
 * Encapsulates client-provided customer details and applies
 * validation constraints to ensure request data integrity
 * before processing by the service layer.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {

	private Long id;

	@NotBlank(message = "First name is required")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "First name contains invalid characters")
	@Size(max = 50, message = "First name must not exceed 50 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Last name contains invalid characters")
	@Size(max = 50, message = "Last name must not exceed 50 characters")
	private String lastName;

	@NotNull(message = "Date of Birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[0-9]+$", message = "Mobile number must contain digits only")
	@Pattern(regexp = "^[6-9][0-9]+", message = "Mobile number must start with digits 6–9")
	@Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
	private String mobile;

	@NotBlank(message = "Email is required")
	@Email(message = "Email format is invalid")
	@Size(max = 255, message = "Email must not exceed 255 characters")
	private String email;

	@NotNull(message = "Age is required")
	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age cannot exceed 120")
	private Byte age;

	@NotBlank(message = "Address Line 1 is required")
	@Pattern(regexp = "^[A-Za-z0-9,.\\-/#]+( [A-Za-z0-9,.\\-/#]+)*$", message = "Address Line 1 contains invalid characters")
	@Size(max = 255, message = "Address Line 1 must not exceed 255 characters")
	private String address1;

	@Pattern(regexp = "^$|^[A-Za-z0-9,.\\-/#]+( [A-Za-z0-9,.\\-/#]+)*$", message = "Address Line 2 contains invalid characters")
	@Size(max = 255, message = "Address Line 2 must not exceed 255 characters")
	private String address2;

	@NotNull(message = "Gender is required")
	@Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE or OTHER")
	private String gender;

}