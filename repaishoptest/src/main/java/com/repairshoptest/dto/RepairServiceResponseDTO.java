package com.repairshoptest.dto;

import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.model.RepairService;

public class RepairServiceResponseDTO {
	
	private int id;
	private String code;
	private double baseCharge;
	private DefectiveItem defItem;
	private String status;
	private String description;
	private String custName;
	private String repairName;
	public RepairServiceResponseDTO() {
		super();
	}

	public RepairServiceResponseDTO(int id, String code,double baseCharge,DefectiveItem defItem, String status,String description, String custName,
			String repairName) {
		super();
		this.id = id;
		this.code = code;
		this.baseCharge = baseCharge;
		this.defItem = defItem;
		this.status = status;
		this.description = description;
		this.custName = custName;
		this.repairName = repairName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	public double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public DefectiveItem getDefItem() {
		return defItem;
	}

	public void setDefItem(DefectiveItem defItem) {
		this.defItem = defItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	
	
	
	@Override
	public String toString() {
		return "RepairServiceResponseDTO [id=" + id + ", code=" + code + ", baseCharge=" + baseCharge + ", defItem="
				+ defItem + ", status=" + status + ", description=" + description + ", custName=" + custName
				+ ", repairName=" + repairName + "]";
	}

	public static RepairServiceResponseDTO fromEntity(RepairService repairService) {
		RepairServiceResponseDTO repairServiceResponseDTO = new RepairServiceResponseDTO();
		repairServiceResponseDTO.setId(repairService.getId());
		repairServiceResponseDTO.setCode(repairService.getCode());
		repairServiceResponseDTO.setBaseCharge(repairService.getBaseCharge());
		repairServiceResponseDTO.setDescription(repairService.getDescription());
		repairServiceResponseDTO.setDefItem(repairService.getDefectiveItem());
		repairServiceResponseDTO.setStatus(repairService.getLatestStatus());
		repairServiceResponseDTO.setCustName(repairService.getCustomer().getName());
		repairServiceResponseDTO.setRepairName(repairService.getAssignedTo().getName());
		return repairServiceResponseDTO;
	}

}
