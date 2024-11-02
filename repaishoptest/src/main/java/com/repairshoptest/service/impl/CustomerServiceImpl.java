package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.User;
import com.repairshoptest.repository.CustomerRepo;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	ClerkService clerkService;



	@Override
	public Customer findById(int custId) {
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			//throws new Exception("Customer not found");
			return null;
		}
		return optCustomer.get();
	}
	
	@Override
	public Customer findByEmail(String email) {
		Customer customer = customerRepo.findByEmail(email);
		if(customer == null) {
			//throw new Exception("Customer not found");
			return null;
		}
		return customer;
	}
	
	@Override
	public Customer authenticateUser(String userName, String password) {
		System.out.println(userName);
		System.out.println(password);
		System.out.println("in customer login");
		Customer customer = customerRepo.findByEmail(userName);
		System.out.println(customer.getName());
		if(customer != null && customer.getHashedPassword().equals(password)) {
			return customer;
		}
		//throw new Exception("Invalid User");
		return null;
	}
	
	

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		List<Customer> list = customerRepo.findAll();
		return list;
	}
	
	
//	@Override
//	public List<Customer> findByCreatedBy(User createdBy) {
//		List<Customer> list = customerRepo.findByCreatedBy(createdBy);
//		return list;
//	}
	
//
//	@Override
//	public List<Customer> findByPattern(String pattern) {
//		// TODO Auto-generated method stub
//		List<Customer> list = customerRepo.findByPattern(pattern);
//		return list;
//	}

	
	@Override
	public Page<Customer> getCustomers(String search, Boolean onlyMine, int clerkId, int page, int limit) {
		 Pageable pageable = PageRequest.of(page, limit);

	        return customerRepo.findBySearchAndCreatedBy(search,onlyMine, clerkId, pageable);
	}

	@Override
	@Transactional
	public Customer add(int clerkId,CustomerRequestDTO customerRequestDTO) {
		Clerk clerk = clerkService.findById(clerkId);
		if(customerRepo.findByEmail(customerRequestDTO.getEmail()) != null ) {
			// throw new Exception(Customer with same email is already present);
			return null;
		}else {
			Customer customer = customerRequestDTO.getCustomer();
			//create password code
			//send password code
			//hash password code
//			customer.setHashedPassword(password);
			customer.setCreatedBy(clerk);
			Customer saved = customerRepo.save(customer);
			return saved;
		}
		
		
		
		
	}



	@Override
	@Transactional
	public Customer update(int custId, CustomerRequestDTO customerRequestDTO) {
		
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			
			//throw new Exception("Customer not found");
			return null;
			
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
	public boolean updatePassword(int custId, String password) {
		// TODO Auto-generated method stub


		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			//throw new Exception("Customer not found");
			return false;
		}else {
			Customer customer = optCustomer.get();
			//Hashing algorithm code
			// String hashedPassword = hashPassword(password);
			//customer.setHashedPassword(hashedPassword);
			customerRepo.save(customer);
			return true;


		}
	  
	}

	



	



	





	
	
}
