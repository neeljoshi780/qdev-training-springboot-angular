package com.customer.crud.app.security;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.customer.crud.app.service.impl.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JwtAuthenticationFilter
 *
 * Security filter that intercepts every incoming HTTP request
 * and performs JWT-based authentication.
 *
 * - Extracts JWT from the Authorization header
 * - Validates the token
 * - Loads user details and sets authentication
 *   in the Spring Security context
 *
 * This filter is executed once per request and
 * does not create or use HTTP sessions.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final AuthService authService;

	/**
	 * Filters each HTTP request to authenticate users
	 * based on the provided JWT token.
	 *
	 * If a valid token is found, authentication is
	 * set in the SecurityContext for the current request.
	 *
	 * @param request incoming HTTP request
	 * @param response outgoing HTTP response
	 * @param filterChain filter chain for request processing
	 * @throws ServletException in case of servlet errors
	 * @throws IOException in case of I/O errors
	 */
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		// Check for Bearer token in Authorization header
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);

			// Validate the JWT token
			if (jwtUtil.validate(token)) {
				// Extract username from token
				String username = jwtUtil.getUsername(token);

				// Load user details (authorities) for security context
				var userDetails = authService.loadUserByUsername(username);

				// Create authentication token
				var authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

				// Attach request details
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// Set authentication in security context
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		// Continue filter chain
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return HttpMethod.OPTIONS.matches(request.getMethod());
	}

}