package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairService;

public interface RepairServiceService {
	
	List<RepairService> findAll();
	
	RepairService findById(int id);
	
	List<RepairService> findByCustomer(Customer customer);
	
	List<RepairService> findByClerk(Clerk clerk);
	
	RepairService add(int clerkId, RepairServiceRequestDTO repairServiceRequestDTO);
	
	Page<RepairService> getServicesForRole(String role, int userId, Boolean onlyMine, String search, int page, int size);
	
	boolean closeService(int id);
	
	
}
