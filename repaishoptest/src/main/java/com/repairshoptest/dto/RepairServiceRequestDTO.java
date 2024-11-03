package com.repairshoptest.dto;

import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.model.RepairService;

public class RepairServiceRequestDTO {
	
	private double baseCharge;
	private String productCode;
	private String productTitle;
	private String productCategory;
	private String desrciption;
	private int custId;
	private int repairId;
	
	public RepairServiceRequestDTO() {
		super();
	}

	

	public RepairServiceRequestDTO(double baseCharge, String productCode, String productTitle, String productCategory,
			String desrciption, int custId, int repairId) {
		super();
		this.baseCharge = baseCharge;
		this.productCode = productCode;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.desrciption = desrciption;
		this.custId = custId;
		this.repairId = repairId;
	}



	public double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}

	

	public String getProductCode() {
		return productCode;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public String getProductTitle() {
		return productTitle;
	}



	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}



	public String getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}



	public String getDesrciption() {
		return desrciption;
	}



	public void setDesrciption(String desrciption) {
		this.desrciption = desrciption;
	}
	
	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getRepairId() {
		return repairId;
	}

	public void setRepairId(int repairId) {
		this.repairId = repairId;
	}



	@Override
	public String toString() {
		return "RepairServiceDTO [baseCharge=" + baseCharge + ", productCode=" + productCode + ", productTitle="
				+ productTitle + ", productCategory=" + productCategory + ", desrciption=" + desrciption + ", custId="
				+ custId + ", repairId=" + repairId + "]";
	}

	
	
	
	public RepairService getRepairService() {
		RepairService repairService = new RepairService();
		repairService.setBaseCharge(this.baseCharge);
		repairService.setDescription(this.desrciption);
	    return repairService;
	}

}
