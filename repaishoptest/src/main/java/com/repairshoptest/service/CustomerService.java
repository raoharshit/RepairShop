package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshop.exception.DuplicateUserException;
import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.model.Customer;

public interface CustomerService {
	
	Customer findById(int custId) throws ResourceNotFoundException;
	
//	List<Customer> findAll();
	
//	List<Customer> findByPattern(String pattern);
	
//	Customer findByEmail(String email);
	
	Customer authenticateCustomer(String userName, String password) throws ResourceNotFoundException;
	
//	List<Customer> findByCreatedBy(User createdBy);
	
    Page<Customer> getCustomers(String search, Boolean onlyMine, int clerkId, int page, int limit);
	
	Customer add(int clerkId, CustomerRequestDTO customerRequestDTO) throws ResourceNotFoundException,DuplicateUserException;
	
	Customer update(int custId, CustomerRequestDTO customerRequestDTO)throws ResourceNotFoundException;
	
//	Customer removeCustomer(int custID);
	
	PasswordChangeResponse updatePassword(int custId,PasswordChangeRequest passwordChangeRequest) throws ResourceNotFoundException,InvalidCredentialsException;
	
}
