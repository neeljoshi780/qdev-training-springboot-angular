package com.customer.crud.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.customer.crud.app.constant.ApiPathConstant;

import lombok.AllArgsConstructor;

/**
 * SecurityConfig
 *
 * Central Spring Security configuration class.
 *
 * Configures password encoding, authentication manager,
 * JWT-based stateless security, and request authorization rules.
 */
@AllArgsConstructor
@Configuration
public class SecurityConfig {

	/**
	 * Handles unauthenticated or invalid authentication requests before controller execution.
	 */
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	/**
	 * Defines the password encoder used for hashing
	 * and verifying user passwords.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Exposes the AuthenticationManager used
	 * during the login/authentication process.
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/**
	 * Configures the security filter chain.
	 *
	 * - Disables CSRF for REST APIs
	 * - Enforces stateless session management
	 * - Allows public access to authentication endpoints
	 * - Secures all other endpoints using JWT validation
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(
				session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.exceptionHandling(ex ->
				ex.authenticationEntryPoint(authenticationEntryPoint)
			)
			.authorizeHttpRequests(
				auth -> auth
					.requestMatchers(ApiPathConstant.AUTH_BASE+"/**").permitAll()
					.requestMatchers(ApiPathConstant.CUSTOMER_BASE+"/**").hasRole("ADMIN")
					.anyRequest().authenticated()
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}