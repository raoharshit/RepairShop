package com.repairshoptest.dto;

import com.repairshoptest.model.DefectiveItem;

public class DefectiveItemDTO {
	
	private String productCode;
	private String title;
	private String description;
	private String category;
	
	


	public DefectiveItemDTO() {
		super();
	}
	



	public DefectiveItemDTO(String productCode, String title, String description, String category) {
		super();
		this.productCode = productCode;
		this.title = title;
		this.description = description;
		this.category = category;
	}



	public String getProductCode() {
		return productCode;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "DefectiveItemDTO [productCode=" + productCode + ", title=" + title + ", description=" + description
				+ ", category=" + category + "]";
	}
	
	public DefectiveItem getDefectiveItem() {
		DefectiveItem defectiveItem = new DefectiveItem();
		defectiveItem.setProductCode(this.productCode);
		defectiveItem.setTitle(this.title);
		defectiveItem.setCategory(this.category);
		defectiveItem.setDescription(this.description);
	    return defectiveItem;
	}
	

}
