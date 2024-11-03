package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("REPAIR")
public class RepairPerson extends User {
	
	private String specialty;

	public RepairPerson() {
		super();
	}

	public RepairPerson(int id, String name, String email, String phone, String address, String hashedPassword,  String specialty) {
		super(id, name, email, phone, address, hashedPassword);
		this.specialty=specialty;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "RepairPerson [specialty=" + specialty + ", getAddress()=" + getAddress() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone()
				+ ", getHashedPassword()=" + getHashedPassword() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
	
	
	
	

}
