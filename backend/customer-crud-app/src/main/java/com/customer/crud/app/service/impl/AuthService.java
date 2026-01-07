package com.customer.crud.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customer.crud.app.exception.ResourceNotFoundException;
import com.customer.crud.app.model.UserModel;
import com.customer.crud.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * AuthService
 *
 * Spring Security service responsible for loading
 * user details from the database during authentication.
 */
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService{

	private final UserRepository userRepository;

	/**
	 * Loads user details by username for authentication.
	 *
	 * This method is invoked by Spring Security
	 * only during the login process.
	 *
	 * @param username the username to authenticate
	 * @return UserDetails containing credentials and authorities
	 * @throws ResourceNotFoundException if the user is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserModel user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

		return org.springframework.security.core.userdetails.User
			.withUsername(user.getUsername())
			.password(user.getPassword())
			.authorities(user.getRole())
			.build();
	}

}