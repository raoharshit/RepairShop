package com.repairshoptest.service;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.enums.UserRole;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.RepairService;

public interface RepairServiceService {
	
	RepairService findById(int id);
	
	RepairService add(int clerkId, RepairServiceRequestDTO repairServiceRequestDTO);
	
	Page<RepairService> getServicesForRole(UserRole role, int userId, Boolean onlyMine, String search, int page, int size);
	
	boolean closeService(int id);
	
	Long countServicesCreated(int year, int month);
	
	Long countClosedServices(int year, int month);
	
	Double totalServiceCharges(int year, int month);
	
	
}
