package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NEW")
public class NewItem extends Item {

	private Double price;

	//This is a new item
	public NewItem() {
		super();
	}

	public NewItem(int id, String productCode, String title, String description, String category, User createdBy,
			Double price) {
		super(id, productCode, title, description, category, createdBy);
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "NewItem [price=" + price + ", getId()=" + getId() + ", getProductCode()=" + getProductCode()
				+ ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getCategory()="
				+ getCategory() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getCreatedBy()=" + getCreatedBy() + "]";
	}
	
	
	
}
