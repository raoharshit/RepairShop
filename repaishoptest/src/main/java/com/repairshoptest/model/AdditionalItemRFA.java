package com.repairshoptest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ADDITIONAL_ITEM")
public class AdditionalItemRFA extends RequestForApproval {

	@ManyToOne
	private NewItem newItem;

	public AdditionalItemRFA(int id, String description, Double serviceCharge, String label, RepairService repairService,
			NewItem newItem) {
		super(id, description, serviceCharge, label, repairService);
		this.newItem = newItem;
	}

	public AdditionalItemRFA() {
		super();
	}

	public NewItem getNewItem() {
		return newItem;
	}

	public void setNewItem(NewItem newItem) {
		this.newItem = newItem;
	}

	@Override
	public String toString() {
		return "AdditionalItemRFA [newItem=" + newItem + ", getId()=" + getId() + ", getDescription()="
				+ getDescription() + ", getServiceCharge()=" + getServiceCharge() + ", getLabel()=" + getLabel()
				+ ", getApprovalStatus()=" + getApprovalStatus() + ", getStatusUpdatedAt()=" + getStatusUpdatedAt()
				+ ", getInvoice()=" + getInvoice() + ", getRepairService()=" + getRepairService() + ", getCreatedAt()="
				+ getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
	
	

}
