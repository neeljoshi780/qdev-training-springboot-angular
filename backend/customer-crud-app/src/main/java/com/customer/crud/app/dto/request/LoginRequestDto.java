package com.customer.crud.app.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequestDto
 *
 * DTO used to capture login credentials
 * during user authentication requests.
 */
@Getter
@Setter
public class LoginRequestDto {

	private String username;

	private String password;

}