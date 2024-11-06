package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.exception.DuplicateUserException;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.repository.CustomerRepo;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.utils.PasswordGenerator;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;



	@Override
	public Customer findById(int custId){
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found");
		}
		return optCustomer.get();
	}
	
	
	@Override
	public Customer authenticateCustomer(String userName, String password){
		Customer customer = customerRepo.findByEmail(userName);
		if(customer == null) {
			 throw new ResourceNotFoundException("Incorrect username or password");
		}
		if(passwordEncoder.matches(password, customer.getHashedPassword())) {
			return customer;
		}
		throw new InvalidCredentialsException("Incorrect username or password");
	}
	
	@Override
	public Page<Customer> getCustomers(String search, Boolean onlyMine, int clerkId, int page, int limit) {
		if(limit == -1) {
			page = 0;
			limit = Integer.MAX_VALUE;
		}
		 Pageable pageable = PageRequest.of(page, limit);

	        return customerRepo.findBySearchAndCreatedBy(search,onlyMine, clerkId, pageable);
	}

	@Override
	@Transactional
	public Customer add(int clerkId,CustomerRequestDTO customerRequestDTO){
		Clerk clerk = clerkService.findById(clerkId);
		if(customerRepo.findByEmail(customerRequestDTO.getEmail()) != null ) {
			 throw new DuplicateUserException("Customer with same email is already present");
		}
			Customer customer = customerRequestDTO.getCustomer();
			String password = PasswordGenerator.generatePassword();
			System.out.println(password);
			//send password code
			String hashedPassword = passwordEncoder.encode(password);
			customer.setHashedPassword(hashedPassword);
			customer.setCreatedBy(clerk);
			Customer saved = customerRepo.save(customer);
			return saved;
	}



	@Override
	@Transactional
	public Customer update(int custId, CustomerRequestDTO customerRequestDTO){
		
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found");
			
		}else {
			Customer customer = optCustomer.get();
			if(customerRequestDTO.getName() != null && !customerRequestDTO.getName().isEmpty()) {
				customer.setName(customerRequestDTO.getName());
			}
			if(customerRequestDTO.getPhone() != null && !customerRequestDTO.getPhone().isEmpty()) {
				customer.setPhone(customerRequestDTO.getPhone());
			}
			if(customerRequestDTO.getAddress() != null && !customerRequestDTO.getAddress().isEmpty()) {
				customer.setAddress(customerRequestDTO.getAddress());
			}
			
			Customer saved = customerRepo.save(customer);
			
			return saved;
		}
		
	}
	
	@Override
	@Transactional
	public PasswordChangeResponse updatePassword(int custId,PasswordChangeRequest passwordChangeRequest){
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found");
		}
			Customer customer = optCustomer.get();
			if(passwordEncoder.matches(passwordChangeRequest.getOldPassword(),customer.getHashedPassword())) {
				String hashedPasssword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
				customer.setHashedPassword(hashedPasssword);
				customerRepo.save(customer);
				PasswordChangeResponse passwordChangeResponse = new PasswordChangeResponse("Password changed successfully");
				return passwordChangeResponse;
			}
			 throw new InvalidCredentialsException("Old password is incorrect");
			
	  
	}


	@Override
	public Long getCustomersCount(int year, int month) {
		return customerRepo.countCustomersByMonthAndYear(year, month);
	}

	
}
