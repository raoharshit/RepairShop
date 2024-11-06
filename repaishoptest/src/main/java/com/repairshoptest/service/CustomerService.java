package com.repairshoptest.service;


import org.springframework.data.domain.Page;
import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.model.Customer;

public interface CustomerService {
	
	Customer findById(int custId);
	
	Customer authenticateCustomer(String userName, String password);
	
    Page<Customer> getCustomers(String search, Boolean onlyMine, int clerkId, int page, int limit);
	
	Customer add(int clerkId, CustomerRequestDTO customerRequestDTO);
	
	Customer update(int custId, CustomerRequestDTO customerRequestDTO);
	
	PasswordChangeResponse updatePassword(int custId,PasswordChangeRequest passwordChangeRequest);
	
	Long getCustomersCount(int year, int month);
	
}
