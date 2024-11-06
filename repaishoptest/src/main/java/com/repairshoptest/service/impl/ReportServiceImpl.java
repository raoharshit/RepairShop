package com.repairshoptest.service.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.ReportResponse;
import com.repairshoptest.model.NewItem;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.InvoiceService;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RepairServiceService repairServiceService;
	
	@Autowired
	private RFAService rfaService;
	
	@Override
	public ReportResponse getMonthlyReport( int year, int month) {
		Long customers = customerService.getCustomersCount(year, month);
		Long countServicesCreated = repairServiceService.countServicesCreated(year, month);
		Long countClosedServices = repairServiceService.countClosedServices(year, month);
		Double totalServiceCharges = repairServiceService.totalServiceCharges(year, month);
		Double repairCharges = rfaService.findAdditionalCharges(year, month);
		Double totalCharges = 0.0;
		if(repairCharges != null) {
			totalCharges += repairCharges;
		}
		if(totalServiceCharges != null) {
			totalCharges += totalServiceCharges;
		}
		NewItem newItem = rfaService.findMostRequestedItem(year, month);
		
		ReportResponse response = new ReportResponse(customers,countServicesCreated,countClosedServices,totalServiceCharges,repairCharges,totalCharges,newItem);
		return response;
	}

}
