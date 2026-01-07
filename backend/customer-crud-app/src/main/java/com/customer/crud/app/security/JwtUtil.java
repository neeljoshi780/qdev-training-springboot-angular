package com.customer.crud.app.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

/**
 * JwtUtil
 *
 * Utility component responsible for generating,
 * parsing, and validating JSON Web Tokens (JWT).
 *
 * Uses a secret key and expiration configuration
 * to create secure, signed tokens.
 */
@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpirationMs;

	private SecretKey key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Generates a JWT token for the given username.
	 *
	 * @param username the authenticated user's username
	 * @return signed JWT token
	 */
	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	/**
	 * Extracts the username from the JWT token.
	 *
	 * @param token the JWT token
	 * @return username stored in the token subject
	 */
	public String getUsername(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	/**
	 * Validates the JWT token signature and expiration.
	 *
	 * @param token the JWT token to validate
	 * @return true if the token is valid, false otherwise
	 */
	public boolean validate(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parse(token);
			return true;
		} catch (JwtException | IllegalArgumentException ex) {
			return false;
		}
	}

}