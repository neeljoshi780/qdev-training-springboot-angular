package com.customer.crud.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.crud.app.model.UserModel;

/**
 * Repository for user authentication data.
 *
 * Provides database access for UserModel.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	/**
	 * Fetches a user by username.
	 *
	 * @param username the username to search
	 * @return an Optional containing the user if found
	 */
	Optional<UserModel> findByUsername(String username);

	/**
	 * Checks if a user exists by username.
	 *
	 * @param username the username to check
	 * @return true if the user exists, false otherwise
	 */
	boolean existsByUsername(String username);

}