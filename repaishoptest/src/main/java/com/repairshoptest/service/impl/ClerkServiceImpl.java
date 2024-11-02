package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.ClerkDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.repository.ClerkRepo;
import com.repairshoptest.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService{
	
	@Autowired
	ClerkRepo clerkRepo;
	
	@Override
	public Clerk findById(int clerkId) {
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			//throws new Exception("Clerk with " + clerkId + " is not present");
			return null;
		}
		return optClerk.get();
	}
	
	@Override
	public Clerk findByEmail(String email) {
		Clerk clerk = clerkRepo.findByEmail(email);
		if(clerk == null) {
			//throw new Exception("Clerk not found");
			return null;
		}
		return clerk;
	}
	
	@Override
	public Clerk authenticateUser(String userName, String password) {
		Clerk clerk = clerkRepo.findByEmail(userName);
		if(clerk.getHashedPassword().equals(password)) {
			return clerk;
		}
		//throw new Exception("Invalid User");
		return null;
	}

	@Override
	@Transactional
	public Clerk add(ClerkDTO clerkDTO) {
		if(clerkRepo.findByEmail(clerkDTO.getEmail()) != null ) {
			// throw new Exception(Customer with same email is already present);
			return null;
		}
		Clerk clerk = clerkDTO.getClerk();
		//password hashing code
		//String hashedPassword = hashPassword(clerk.getHashedPassword();
		//clerk.setHashedPassword(hashedPassword);
		Clerk saved = clerkRepo.save(clerk);
		
		return saved;
	}

	@Override
	@Transactional
	public Clerk update(int clerkId, ClerkDTO clerkDTO) {
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			//throw new Exception("Clerk not found");
			return null;
		}
		Clerk clerk = optClerk.get();
		clerk.setName(clerkDTO.getName());
		clerk.setPhone(clerkDTO.getPhone());
		clerk.setAddress(clerkDTO.getAddress());
		Clerk saved = clerkRepo.save(clerk);
		
		return saved;
		
	}

	@Override
	@Transactional
	public boolean updatePassword(int clerkId, String password) {
		//Hashing algorithm code

		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);

		if(optClerk.isEmpty()) {
			// throw new Exception("Clerk not found");
			return false;
		}
		Clerk clerk = optClerk.get();
		//password hashing code
		//String hashedPassword = hashPassword(password);
		// clerk.setHashedPassword(hashedPassword);
		clerkRepo.save(clerk);
		return true;
	}

	@Override
	public boolean remove(int clerkId) {
		// TODO Auto-generated method stub
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			//throws new Exception("Repairperson with " + id + " is not present");
			return false;
		}
		clerkRepo.delete(optClerk.get());
		return true;
	}

	

}
