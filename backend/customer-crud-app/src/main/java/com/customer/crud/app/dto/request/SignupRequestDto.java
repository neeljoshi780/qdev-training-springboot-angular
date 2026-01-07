package com.customer.crud.app.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * SignupRequestDto
 *
 * DTO used to capture user registration
 * details during signup requests.
 */
@Getter
@Setter
public class SignupRequestDto {

	private String username;

	private String password;

}