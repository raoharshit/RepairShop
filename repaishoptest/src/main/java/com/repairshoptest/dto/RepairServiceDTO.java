package com.repairshoptest.dto;

import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.model.RepairService;

public class RepairServiceDTO {
	
	private double baseCharge;
	private DefectiveItemDTO defectiveItem;
	private int custId;
	private int repairId;
	
	public RepairServiceDTO() {
		super();
	}

	public RepairServiceDTO(double baseCharge, DefectiveItemDTO defectiveItem, int custId, int repairId) {
		super();
		this.baseCharge = baseCharge;
		this.defectiveItem = defectiveItem;
		this.custId = custId;
		this.repairId = repairId;
	}

	public double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public DefectiveItemDTO getDefectiveItemDTO() {
		return defectiveItem;
	}

	public void setDefectiveItemDTO(DefectiveItemDTO defectiveItem) {
		this.defectiveItem = defectiveItem;
	}

	@Override
	public String toString() {
		return "RepairServiceDTO [baseCharge=" + baseCharge + ", defectiveItemDTO=" + defectiveItem + ", custId="
				+ custId + ", repairId=" + repairId + "]";
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
	
	
	public RepairService getRepairService() {
		RepairService repairService = new RepairService();
		repairService.setBaseCharge(this.baseCharge);
	    return repairService;
	}

}
