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
	 */
	Optional<UserModel> findByUsername(String username);

}