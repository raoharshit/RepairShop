package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.CustomerResponseDTO;
import com.repairshoptest.model.Customer;

public interface CustomerService {
	
	Customer findById(int custId);
	
	List<Customer> findAll();
	
//	List<Customer> findByPattern(String pattern);
	
	Customer findByEmail(String email);
	
	Customer authenticateUser(String userName, String password);
	
//	List<Customer> findByCreatedBy(User createdBy);
	
	 public Page<Customer> getCustomers(String search, Boolean onlyMine, int clerkId, int page, int limit);
	
	Customer add(int clerkId, CustomerRequestDTO customerRequestDTO);
	
	Customer update(int custId, CustomerRequestDTO customerRequestDTO);
	
//	Customer removeCustomer(int custID);
	
	boolean updatePassword(int custId, String password);
	
}
