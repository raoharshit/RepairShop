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

	public RepairPerson(int id, String name, String email, String phone, String address, String hashedPassword,  String speciality) {
		super(id, name, email, phone, address, hashedPassword);
		this.specialty=speciality;
	}

	public String getSpeciality() {
		return specialty;
	}

	public void setSpeciality(String speciality) {
		this.specialty = speciality;
	}

	@Override
	public String toString() {
		return "RepairPerson [specialty=" + specialty + ", getAddress()=" + getAddress() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone()
				+ ", getHashedPassword()=" + getHashedPassword() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
	
	
	
	

}
