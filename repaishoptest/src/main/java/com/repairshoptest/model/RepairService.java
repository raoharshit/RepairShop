package com.repairshoptest.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class RepairService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;
	private double baseCharge;
	private String latestStatus;
	private String description;

	@OneToOne
	private DefectiveItem defectiveItem;
	@ManyToOne
	private Customer customer;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	@ManyToOne
	private Clerk createdBy;

	@ManyToOne
	private RepairPerson assignedTo;

	private boolean isRepaired = false;

	public RepairService() {
		super();
	}

	public RepairService(int id, String code, double baseCharge, String description, DefectiveItem defectiveItem,
			Customer customer, Clerk createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.baseCharge = baseCharge;
		this.description = description;
		this.defectiveItem = defectiveItem;
		this.customer = customer;
		this.createdBy = createdBy;
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

	public String getLatestStatus() {
		return latestStatus;
	}

	public void setLatestStatus(String latestStatus) {
		this.latestStatus = latestStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DefectiveItem getDefectiveItem() {
		return defectiveItem;
	}

	public void setDefectiveItem(DefectiveItem defectiveItem) {
		this.defectiveItem = defectiveItem;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public Clerk getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Clerk createdBy) {
		this.createdBy = createdBy;
	}

	public RepairPerson getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(RepairPerson assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isRepaired() {
		return isRepaired;
	}

	public void setRepaired(boolean isRepaired) {
		this.isRepaired = isRepaired;
	}

	@Override
	public String toString() {
		return "RepairService [id=" + id + ", code=" + code + ", baseCharge=" + baseCharge + ", latestStatus="
				+ latestStatus + ", description=" + description + ", defectiveItem=" + defectiveItem + ", customer="
				+ customer + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy
				+ ", assignedTo=" + assignedTo + ", isRepaired=" + isRepaired + "]";
	}

}
