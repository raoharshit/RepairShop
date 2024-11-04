package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.repairshoptest.model.DefectiveItem;

public class DefectiveItemDTO {
	
	@NotNull(message = "productcode cannot be null")
    @NotBlank(message = "productcode cannot be blank")
	private String productCode;
	
	@NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
	private String title;
	
	@NotNull(message = "category cannot be null")
    @NotBlank(message = "category cannot be blank")
	private String category;
	
	


	public DefectiveItemDTO() {
		super();
	}
	



	public DefectiveItemDTO(String productCode, String title, String category) {
		super();
		this.productCode = productCode;
		this.title = title;
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



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "DefectiveItemDTO [productCode=" + productCode + ", title=" + title +", category=" + category + "]";
	}
	
	public DefectiveItem getDefectiveItem() {
		DefectiveItem defectiveItem = new DefectiveItem();
		defectiveItem.setProductCode(this.productCode);
		defectiveItem.setTitle(this.title);
		defectiveItem.setCategory(this.category);
	    return defectiveItem;
	}
	

}
