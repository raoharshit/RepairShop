package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.repairshoptest.model.AdditionalItemRFA;

import com.repairshoptest.model.AdditionalItemRFA;

public class AdditionalItemRFARequestDTO {
	
	@NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be blank")
	private String description;
	
	@NotNull(message = "servicecharge cannot be null")
    @NotBlank(message = "servicecharge cannot be blank")
	@PositiveOrZero(message = "servicecharge should be positive")
	private double serviceCharge;
	
	@NotNull(message = "label cannot be null")
    @NotBlank(message = "label cannot be blank")
	private String label;
	
	@NotNull(message = "newitemid cannot be null")
    @NotBlank(message = "newitemid cannot be blank")
	@Positive(message = "newitemid should be positive")
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
