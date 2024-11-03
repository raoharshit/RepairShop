package com.repairshoptest.service.impl;

import java.util.List;
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
	public Customer findById(int custId) {
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			//throw new Exception("Customer not found");
			return null;
		}
		return optCustomer.get();
	}
	
//	@Override
//	public Customer findByEmail(String email) {
//		Customer customer = customerRepo.findByEmail(email);
//		if(customer == null) {
//			//throw new Exception("Customer not found");
//			return null;
//		}
//		return customer;
//	}
	
	@Override
	public Customer authenticateCustomer(String userName, String password) {
		Customer customer = customerRepo.findByEmail(userName);
		if(customer == null) {
			// throw new exception("User not found");
			return null;
		}
		if(passwordEncoder.matches(password, customer.getHashedPassword())) {
			return customer;
		}
		//throw new Exception("Invalid User");
		return null;
	}
	
	

//	@Override
//	public List<Customer> findAll() {
//		// TODO Auto-generated method stub
//		List<Customer> list = customerRepo.findAll();
//		return list;
//	}
	
	
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
		}
			Customer customer = customerRequestDTO.getCustomer();
			String password = PasswordGenerator.generatePassword();
			//send password code
			String hashedPassword = passwordEncoder.encode(password);
			customer.setHashedPassword(hashedPassword);
			customer.setCreatedBy(clerk);
			Customer saved = customerRepo.save(customer);
			return saved;
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
	public PasswordChangeResponse updatePassword(int custId,PasswordChangeRequest passwordChangeRequest) {
		// TODO Auto-generated method stub


		Optional<Customer> optCustomer = customerRepo.findById(custId);
		if(optCustomer.isEmpty()) {
			//throw new Exception("Customer not found");
			return null;
		}
			Customer customer = optCustomer.get();
			if(passwordEncoder.matches(passwordChangeRequest.getOldPassword(),customer.getHashedPassword())) {
				String hashedPasssword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
				customer.setHashedPassword(hashedPasssword);
				customerRepo.save(customer);
				PasswordChangeResponse passwordChangeResponse = new PasswordChangeResponse("Password changed successfully");
				return passwordChangeResponse;
			}
			// throw new Exception("Old password is incorrect:);
			return null;
			
	  
	}

	
}
