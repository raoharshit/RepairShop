package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.repairshoptest.model.RepairService;

public class RepairServiceRequestDTO {

	@NotNull(message = "basecharge cannot be null")
	@NotBlank(message = "basecharge cannot be blank")
	@PositiveOrZero(message = "basecharge should be positive")
	private double baseCharge;

	@NotNull(message = "productcode cannot be null")
	@NotBlank(message = "productcode cannot be blank")
	private String productCode;

	@NotNull(message = "producttitle cannot be null")
	@NotBlank(message = "producttitle cannot be blank")
	private String productTitle;

	@NotNull(message = "productcategory cannot be null")
	@NotBlank(message = "productcategory cannot be blank")
	private String productCategory;

	@NotNull(message = "description cannot be null")
	@NotBlank(message = "description cannot be blank")
	private String description;

	@NotNull(message = "custid cannot be null")
	@NotBlank(message = "custid cannot be blank")
	@Positive(message = "custid should be positive")
	private int custId;

	@NotNull(message = "repairid cannot be null")
	@NotBlank(message = "repairid cannot be blank")
	@Positive(message = "repairid should be positive")
	private int repairId;

	public RepairServiceRequestDTO(
			@NotNull(message = "basecharge cannot be null") @NotBlank(message = "basecharge cannot be blank") @PositiveOrZero(message = "basecharge should be positive") double baseCharge,
			@NotNull(message = "productcode cannot be null") @NotBlank(message = "productcode cannot be blank") String productCode,
			@NotNull(message = "producttitle cannot be null") @NotBlank(message = "producttitle cannot be blank") String productTitle,
			@NotNull(message = "productcategory cannot be null") @NotBlank(message = "productcategory cannot be blank") String productCategory,
			@NotNull(message = "description cannot be null") @NotBlank(message = "description cannot be blank") String description,
			@NotNull(message = "custid cannot be null") @NotBlank(message = "custid cannot be blank") @Positive(message = "custid should be positive") int custId,
			@NotNull(message = "repairid cannot be null") @NotBlank(message = "repairid cannot be blank") @Positive(message = "repairid should be positive") int repairId) {
		super();
		this.baseCharge = baseCharge;
		this.productCode = productCode;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.description = description;
		this.custId = custId;
		this.repairId = repairId;
	}

	public RepairServiceRequestDTO() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		repairService.setDescription(this.description);
		return repairService;
	}

	public DefectiveItemDTO getDefectiveItem() {
		DefectiveItemDTO dto = new DefectiveItemDTO();
		dto.setTitle(this.productTitle);
		dto.setProductCode(this.productCode);
		dto.setCategory(this.productCategory);
		return dto;
	}

}
