package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("DEFECTIVE")
public class DefectiveItem extends Item {
	
	@JsonIgnore
	@ManyToOne
	private Customer customer;

	public DefectiveItem() {
		super();
	}

	public DefectiveItem(int id, String productCode, String title, String description, String category, User createdBy, Customer customer) {
		super(id, productCode, title, description, category, createdBy);
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "DefectiveItem [customer=" + customer + ", getId()=" + getId() + ", getProductCode()=" + getProductCode()
				+ ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getCategory()="
				+ getCategory() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getCreatedBy()=" + getCreatedBy() + "]";
	}
	
	

}
