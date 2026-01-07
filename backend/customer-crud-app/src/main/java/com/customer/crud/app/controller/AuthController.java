package com.customer.crud.app.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.crud.app.constant.ApiPathConstant;
import com.customer.crud.app.dto.request.LoginRequestDto;
import com.customer.crud.app.dto.request.SignupRequestDto;
import com.customer.crud.app.dto.response.JwtResponseDto;
import com.customer.crud.app.exception.AuthenticationFailedException;
import com.customer.crud.app.model.UserModel;
import com.customer.crud.app.security.JwtUtil;
import lombok.AllArgsConstructor;

/**
 * AuthController
 *
 * REST controller responsible for handling
 * user authentication and registration APIs.
 *
 * Provides endpoints for:
 * - User signup (registration)
 * - User signin (JWT-based authentication)
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ApiPathConstant.AUTH_BASE)
@AllArgsConstructor
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;

	@PostMapping(ApiPathConstant.SIGN_UP)
	public UserModel signup(@RequestBody SignupRequestDto dto) {
		return null;
	}

	/**
	 * Authenticates a user and generates a JWT.
	 *
	 * Uses Spring Security AuthenticationManager
	 * to validate credentials and returns a
	 * signed JWT on successful authentication.
	 *
	 * @param dto login request containing username and password
	 * @return JwtResponseDto containing token details
	 */
	@PostMapping(ApiPathConstant.SIGN_IN)
	public JwtResponseDto signin(@RequestBody LoginRequestDto dto) {
		try {
			Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					dto.getUsername(),
					dto.getPassword()
				)
			);

			String token = jwtUtil.generateToken(dto.getUsername());
			return new JwtResponseDto(token, "Bearer", dto.getUsername());

		} catch (BadCredentialsException | InternalAuthenticationServiceException ex) {
			throw new AuthenticationFailedException("Invalid username or password");
		}
	}

}