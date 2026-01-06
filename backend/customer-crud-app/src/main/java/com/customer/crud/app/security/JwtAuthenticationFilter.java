package com.customer.crud.app.security;

import org.springframework.stereotype.Component;

/**
 * JwtAuthenticationFilter
 *
 * Security filter executed once per request.
 *
 * Responsibilities:
 * - Extract JWT from Authorization header
 * - Validate token signature and expiry
 * - Build Authentication object from JWT claims
 * - Populate SecurityContext
 *
 * No database interaction occurs in this filter.
 */
@Component
public class JwtAuthenticationFilter {
}