package com.customer.crud.app.enums;

public enum Gender {

	MALE((byte) 0),
	FEMALE((byte) 1),
	OTHER((byte) 2);

	private final byte value;

	Gender(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public static Byte fromString(String input) {
		if (input == null) return null;

		return switch (input.toLowerCase()) {
			case "male", "m" -> MALE.value;
			case "female", "f" -> FEMALE.value;
			case "other", "o" -> OTHER.value;
			default -> null;
		};
	}

	// Byte → Enum (response)
	public static Gender fromValue(Byte value) {
		if (value == null) return null;

		for (Gender gender : values()) {
			if (gender.value == value) {
				return gender;
			}
		}
		throw new IllegalArgumentException("Invalid gender value: " + value);
	}

}