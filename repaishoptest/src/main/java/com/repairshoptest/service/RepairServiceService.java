package com.repairshoptest.service;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.RepairService;

public interface RepairServiceService {
	
	RepairService findById(int id);
	
	RepairService add(int clerkId, RepairServiceRequestDTO repairServiceRequestDTO);
	
	Page<RepairService> getServicesForRole(String role, int userId, Boolean onlyMine, String search, int page, int size);
	
	boolean closeService(int id);
	
	
}
