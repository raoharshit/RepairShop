package com.repairshoptest.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ServiceStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JsonIgnore
	private RepairService repairService;

	private String description; // Approved for repair, pending request

	@CreationTimestamp
	private LocalDateTime createdAt;

	public ServiceStatus() {
		super();
	}

	public ServiceStatus(int id, RepairService repairService, String description) {
		super();
		this.id = id;
		this.repairService = repairService;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
