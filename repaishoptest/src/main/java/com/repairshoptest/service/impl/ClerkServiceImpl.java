package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshop.exception.DuplicateUserException;
import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.repository.ClerkRepo;
import com.repairshoptest.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService{
	
	@Autowired
	private ClerkRepo clerkRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Clerk findById(int clerkId) throws ResourceNotFoundException{
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			throw new ResourceNotFoundException("Clerk not found");
//			return null;
		}
		return optClerk.get();
	}
	
//	@Override
//	public Clerk findByEmail(String email) {
//		Clerk clerk = clerkRepo.findByEmail(email);
//		if(clerk == null) {
//			//throw new Exception("Clerk not found");
//			return null;
//		}
//		return clerk;
//	}
	
	@Override
	public Clerk authenticateClerk(String userName, String password) throws InvalidCredentialsException{
		System.out.println(userName + " " + password);
		Clerk clerk = clerkRepo.findByEmail(userName);
		if(clerk == null) {
			 throw new InvalidCredentialsException("Incorrect username or password");
		}
		if(passwordEncoder.matches(password, clerk.getHashedPassword())) {
			return clerk;
		}
		throw new InvalidCredentialsException("Incorrect username or password");
	}

	@Override
	@Transactional
	public Clerk add(ClerkRequestDTO clerkRequestDTO) throws DuplicateUserException{
		if(clerkRepo.findByEmail(clerkRequestDTO.getEmail()) != null ) {
			 throw new DuplicateUserException("Clerk with same email is already present");
		}
		Clerk clerk = clerkRequestDTO.getClerk();
		String hashedPassword = passwordEncoder.encode(clerkRequestDTO.getPassword());
		clerk.setHashedPassword(hashedPassword);
		Clerk saved = clerkRepo.save(clerk);
		return saved;
	}

	@Override
	@Transactional
	public Clerk update(int clerkId, ClerkRequestDTO clerkRequestDTO) throws ResourceNotFoundException{
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			throw new ResourceNotFoundException("Clerk not found");
		}
		Clerk clerk = optClerk.get();
		clerk.setName(clerkRequestDTO.getName());
		clerk.setPhone(clerkRequestDTO.getPhone());
		clerk.setAddress(clerkRequestDTO.getAddress());
		Clerk saved = clerkRepo.save(clerk);
		
		return saved;
		
	}

	@Override
	@Transactional
	public PasswordChangeResponse updatePassword(int clerkId, PasswordChangeRequest passwordChangeRequest) throws ResourceNotFoundException,InvalidCredentialsException{
		//Hashing algorithm code

		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);

		if(optClerk.isEmpty()) {
			 throw new ResourceNotFoundException("Clerk not found");
		}
		Clerk clerk = optClerk.get();
		if(passwordEncoder.matches(passwordChangeRequest.getOldPassword(),clerk.getHashedPassword())) {
			String hashedPasssword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
			clerk.setHashedPassword(hashedPasssword);
			clerkRepo.save(clerk);
			PasswordChangeResponse passwordChangeResponse = new PasswordChangeResponse("Password changed successfully");
			return passwordChangeResponse;
		}
		 throw new InvalidCredentialsException("Old password is incorrect");
	}

//	@Override
//	public boolean remove(int clerkId) {
//		// TODO Auto-generated method stub
//		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
//		if(optClerk.isEmpty()) {
//			//throws new Exception("Repairperson with " + id + " is not present");
//			return false;
//		}
//		clerkRepo.delete(optClerk.get());
//		return true;
//	}

	

}
