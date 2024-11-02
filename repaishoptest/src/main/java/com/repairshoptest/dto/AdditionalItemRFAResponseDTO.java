package com.repairshoptest.dto;

import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.NewItem;

public class AdditionalItemRFAResponseDTO {
	
	private int id;
	private String description;
	private Double serviceCharge;
	private String label;
	private String productCode;
	private String defItemTitle;
	private NewItem newItem;
	private String status;
	
	public AdditionalItemRFAResponseDTO() {
		super();
	}

	public AdditionalItemRFAResponseDTO(int id, String description, Double serviceCharge, String label, String productCode,
			String defItemTitle, NewItem newItem, String status) {
		super();
		this.id = id;
		this.description = description;
		this.serviceCharge = serviceCharge;
		this.label = label;
		this.productCode = productCode;
		this.defItemTitle = defItemTitle;
		this.newItem = newItem;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDefItemTitle() {
		return defItemTitle;
	}

	public void setDefItemTitle(String defItemTitle) {
		this.defItemTitle = defItemTitle;
	}

	public NewItem getNewItem() {
		return newItem;
	}

	public void setNewItem(NewItem newItem) {
		this.newItem = newItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdditionalItemRFADTO [id=" + id + ", description=" + description + ", serviceCharge=" + serviceCharge
				+ ", label=" + label + ", productCode=" + productCode + ", defItemTitle=" + defItemTitle + ", newItem="
				+ newItem + ", status=" + status + "]";
	}
	
	public static AdditionalItemRFAResponseDTO fromEntity(AdditionalItemRFA addItemRFA) {
		AdditionalItemRFAResponseDTO additionalItemRFAResponseDTO = new AdditionalItemRFAResponseDTO();
		additionalItemRFAResponseDTO.setId(addItemRFA.getId());
		additionalItemRFAResponseDTO.setDescription(addItemRFA.getDescription());
		additionalItemRFAResponseDTO.setServiceCharge(addItemRFA.getServiceCharge());
		additionalItemRFAResponseDTO.setLabel(addItemRFA.getLabel());
		additionalItemRFAResponseDTO.setDefItemTitle(addItemRFA.getRepairService().getDefectiveItem().getTitle());
		additionalItemRFAResponseDTO.setProductCode(addItemRFA.getRepairService().getDefectiveItem().getProductCode());
		additionalItemRFAResponseDTO.setNewItem(addItemRFA.getNewItem());
		additionalItemRFAResponseDTO.setStatus(addItemRFA.getApprovalStatus());
		return additionalItemRFAResponseDTO;
	}
	

}
