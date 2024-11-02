package com.repairshoptest.dto;

import com.repairshoptest.model.AdditionalItemRFA;

import com.repairshoptest.model.AdditionalItemRFA;

public class AdditionalItemRFARequestDTO {
	
	private String description;
	private double serviceCharge;
	private String label;
	private int newItemId;
	
	
	public AdditionalItemRFARequestDTO() {
		super();
	}


	public AdditionalItemRFARequestDTO(String description, double serviceCharge, String label,
			int newItemId) {
		super();
		this.description = description;
		this.serviceCharge = serviceCharge;
		this.label = label;
		this.newItemId = newItemId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getServiceCharge() {
		return serviceCharge;
	}


	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}



	public int getNewItemId() {
		return newItemId;
	}


	public void setNewItemId(int newItemId) {
		this.newItemId = newItemId;
	}


	@Override
	public String toString() {
		return "AdditionalItemRFARequestDTO [description=" + description + ", serviceCharge=" + serviceCharge
				+ ", label=" + label + ", newItemId=" + newItemId + "]";
	}
	
	
	public AdditionalItemRFA getRFA() {
		AdditionalItemRFA rfa = new AdditionalItemRFA();
		rfa.setDescription(this.description);
		rfa.setServiceCharge(this.serviceCharge);
		rfa.setLabel(this.label);
		rfa.setDescription(this.label);
		
		return rfa;
	}

}
