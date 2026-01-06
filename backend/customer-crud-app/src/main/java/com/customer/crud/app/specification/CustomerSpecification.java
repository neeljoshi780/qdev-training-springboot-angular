package com.customer.crud.app.specification;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.data.jpa.domain.Specification;

import com.customer.crud.app.enums.Gender;
import com.customer.crud.app.model.CustomerModel;

public class CustomerSpecification {

	public static Specification<CustomerModel> globalSearch(String search) {

		return (root, query, cb) -> {

			if (search == null || search.isBlank()) {
				return cb.conjunction(); // no filter
			}

			String like = "%" + search.toLowerCase() + "%";

			Long id = parseLong(search);
			LocalDate dob = parseDate(search);
			Byte gender = Gender.fromString(search);

			return cb.or(
				cb.like(cb.lower(root.get("firstName")), like),
				cb.like(cb.lower(root.get("lastName")), like),
				cb.like(cb.lower(root.get("email")), like),
				cb.like(cb.lower(root.get("mobile")), like),
				cb.like(cb.lower(root.get("address1")), like),
				cb.like(cb.lower(root.get("address2")), like),
				id == null ? cb.disjunction() : cb.equal(root.get("id"), id),
				dob == null ? cb.disjunction() : cb.equal(root.get("dateOfBirth"), dob),
				gender == null ? cb.disjunction() : cb.equal(root.get("gender"), gender)
			);
		};
	}

	private static Long parseLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return null;
		}
	}

	private static LocalDate parseDate(String value) {
		try {
			return LocalDate.parse(value); // expects yyyy-MM-dd
		} catch (DateTimeParseException e) {
			return null;
		}
	}

}