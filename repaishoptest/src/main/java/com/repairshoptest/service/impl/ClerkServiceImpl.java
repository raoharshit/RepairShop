package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.exception.DuplicateUserException;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
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
	public Clerk findById(int clerkId){
		Optional<Clerk> optClerk = clerkRepo.findById(clerkId);
		if(optClerk.isEmpty()) {
			throw new ResourceNotFoundException("Clerk not found");
		}
		return optClerk.get();
	}
	
	
	@Override
	public Clerk authenticateClerk(String userName, String password){
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
	public Clerk add(ClerkRequestDTO clerkRequestDTO){
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
	public Clerk update(int clerkId, ClerkRequestDTO clerkRequestDTO){
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
	public PasswordChangeResponse updatePassword(int clerkId, PasswordChangeRequest passwordChangeRequest){
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


	

}
