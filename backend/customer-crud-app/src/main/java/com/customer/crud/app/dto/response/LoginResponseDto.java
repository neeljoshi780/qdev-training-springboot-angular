package com.customer.crud.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * LoginResponseDto
 *
 * DTO returned after successful authentication.
 * Contains generated JWT token.
 */
@Getter
@AllArgsConstructor
public class LoginResponseDto {

	private String token;

}