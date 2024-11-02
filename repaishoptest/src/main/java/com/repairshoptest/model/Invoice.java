package com.repairshoptest.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne // Can be modified to ManyToOne later
	private RepairService repairService;
//	@OneToMany // Can be modified to ManyToMany later
//	private List<RequestForApproval> approvedRequests;
	private Double totalAmount;
	private int otp; // 4 digits
	@Column(columnDefinition = "boolean default false")
	private Boolean isDelivered;
	private LocalDateTime deliveredAt;
	@ManyToOne
	private Clerk deliveredBy;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Invoice(int id, RepairService repairService, Double totalAmount, int otp) {
		super();
		this.id = id;
		this.repairService = repairService;
		this.totalAmount = totalAmount;
		this.otp = otp;
	}

	public Invoice() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RepairService getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Boolean getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(Boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public LocalDateTime getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(LocalDateTime deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public Clerk getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveredBy(Clerk deliveredBy) {
		this.deliveredBy = deliveredBy;
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
		return "Invoice [id=" + id + ", repairService=" + repairService + ", totalAmount=" + totalAmount + ", otp="
				+ otp + ", isDelivered=" + isDelivered + ", deliveredAt=" + deliveredAt + ", deliveredBy=" + deliveredBy
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	

}
