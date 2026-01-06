package com.customer.crud.app.model;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CustomerModel
 *
 * Entity class representing the "customers" table in the database.
 *
 * This class maps customer-related data to persistent storage
 * and is managed by JPA/Hibernate.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@DynamicUpdate
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "mobile", nullable = false, length = 20, unique = true)
	private String mobile;

	@Column(name = "address1", nullable = false, length = 255)
	private String address1;

	@Column(name = "address2", length = 255)
	private String address2;

	@Column(name = "age", columnDefinition = "TINYINT UNSIGNED")
	private Byte age;

	@Column(name = "gender", columnDefinition = "TINYINT UNSIGNED")
	private Byte gender;

	@Column(name = "email", nullable = false, length = 255, unique = true)
	private String email;

}