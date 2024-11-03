package com.repairshoptest.service;

import org.springframework.data.domain.Page;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.model.RepairService;

public interface RepairServiceService {
	
//	List<RepairService> findAll();
	
	RepairService findById(int id) throws ResourceNotFoundException;
	
//	List<RepairService> findByCustomer(Customer customer);
	
//	List<RepairService> findByClerk(Clerk clerk);
	
	RepairService add(int clerkId, RepairServiceRequestDTO repairServiceRequestDTO) throws Exception;
	
	Page<RepairService> getServicesForRole(String role, int userId, Boolean onlyMine, String search, int page, int size);
	
	boolean closeService(int id) throws Exception;
	
	
}
