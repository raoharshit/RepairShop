package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
	
	@ManyToOne
	private Clerk createdBy;

	public Customer() {
		super();
	}

	public Customer(int id, String name, String email, String phone, String address, String hashedPassword, Clerk createdBy) {
		super(id, name, email, phone, address, hashedPassword);
		this.createdBy = createdBy;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Clerk createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Customer [createdBy=" + createdBy + ", getAddress()=" + getAddress() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone()
				+ ", getHashedPassword()=" + getHashedPassword() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
	
	
	
	

}
