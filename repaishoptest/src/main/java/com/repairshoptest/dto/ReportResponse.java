package com.repairshoptest.dto;

import com.repairshoptest.model.NewItem;

public class ReportResponse {

	private Long customers;

	private Long servicesCreated;

	private Long closedServices;

	private Double serviceCharges;

	private Double repairCharges;

	private Double totalCharges;

	private NewItem mostRequestedItem;

	public ReportResponse() {
		super();
	}

	public ReportResponse(Long customers, Long servicesCreated, Long closedServices, Double serviceCharges,
			Double repairCharges, Double totalCharges, NewItem mostRequestedItem) {
		super();
		this.customers = customers;
		this.servicesCreated = servicesCreated;
		this.closedServices = closedServices;
		this.serviceCharges = serviceCharges;
		this.repairCharges = repairCharges;
		this.totalCharges = totalCharges;
		this.mostRequestedItem = mostRequestedItem;
	}

	public Long getCustomers() {
		return customers;
	}

	public void setCustomers(Long customers) {
		this.customers = customers;
	}

	public Long getServicesCreated() {
		return servicesCreated;
	}

	public void setServicesCreated(Long servicesCreated) {
		this.servicesCreated = servicesCreated;
	}

	public Long getClosedServices() {
		return closedServices;
	}

	public void setClosedServices(Long closedServices) {
		this.closedServices = closedServices;
	}

	public Double getServiceCharges() {
		return serviceCharges;
	}

	public void setServiceCharges(Double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

	public Double getRepairCharges() {
		return repairCharges;
	}

	public void setRepairCharges(Double repairCharges) {
		this.repairCharges = repairCharges;
	}

	public Double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public NewItem getMostRequestedItem() {
		return mostRequestedItem;
	}

	public void setMostRequestedItem(NewItem mostRequestedItem) {
		this.mostRequestedItem = mostRequestedItem;
	}

}
