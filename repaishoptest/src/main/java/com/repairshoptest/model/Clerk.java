package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLERK")
public class Clerk extends User {

	public Clerk() {
		super();
	}

	public Clerk(int id, String name, String email, String phone, String address, String hashedPassword) {
		super(id, name, email, phone, address, hashedPassword);
	}
}
