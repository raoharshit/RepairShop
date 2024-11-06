package com.repairshoptest.enums;

public enum UserRole {
	CUSTOMER("customer"), CLERK("clerk"), REPAIR_PERSON("repairperson");

	private final String role;

	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
