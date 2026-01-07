package com.customer.crud.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JwtResponseDto
 *
 * DTO used to send JWT authentication details
 * back to the client after successful login.
 */
@Getter
@AllArgsConstructor
public class JwtResponseDto {

	private String token;

	private String tokenType = "Bearer";

	private String username;

}