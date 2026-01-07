package com.customer.crud.app.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationEntryPoint
 *
 * Handles authentication failures that occur
 * within the Spring Security filter chain.
 *
 * This entry point is invoked when an unauthenticated
 * or invalidly authenticated request attempts to
 * access a secured resource.
 *
 * Responsibilities:
 * - Returns HTTP 401 (Unauthorized)
 * - Sends a standardized JSON error response
 * - Prevents controller execution for unauthorized requests
 *
 * NOTE:
 * This class does NOT handle login (username/password)
 * failures thrown inside controllers.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * Commences an authentication failure response.
	 *
	 * Called by Spring Security when authentication
	 * is required but has failed or is missing.
	 *
	 * @param request the incoming HTTP request
	 * @param response the HTTP response to be sent
	 * @param ex the authentication exception that occurred
	 * @throws IOException if an I/O error occurs while writing the response
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.getWriter().write("""
		{
		  "status": 401,
		  "error": "UNAUTHORIZED",
		  "message": "Invalid username or password",
		  "path": "%s"
		}
		""".formatted(request.getRequestURI()));
	}

}