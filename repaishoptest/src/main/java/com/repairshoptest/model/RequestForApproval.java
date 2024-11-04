package com.repairshoptest.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class RequestForApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;
	private Double serviceCharge;
	private String label; // High priority, additional benefits, etc
	@Column(columnDefinition = "varchar(255) default 'waiting for approval'")
	private String approvalStatus;
	private LocalDateTime statusUpdatedAt;

	@ManyToOne
	private Invoice invoice;

	@ManyToOne
	private RepairService repairService;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public RequestForApproval(int id, String description, Double serviceCharge, String label,
			RepairService repairService) {
		super();
		this.id = id;
		this.description = description;
		this.serviceCharge = serviceCharge;
		this.label = label;
		this.repairService = repairService;
	}

	public RequestForApproval() {
		super();
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public LocalDateTime getStatusUpdatedAt() {
		return statusUpdatedAt;
	}

	public void setStatusUpdatedAt(LocalDateTime statusUpdatedAt) {
		this.statusUpdatedAt = statusUpdatedAt;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public RepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "RequestForApproval [id=" + id + ", description=" + description + ", serviceCharge=" + serviceCharge
				+ ", label=" + label + ", approvalStatus=" + approvalStatus + ", statusUpdatedAt=" + statusUpdatedAt
				+ ", invoice=" + invoice + ", repairService=" + repairService + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
