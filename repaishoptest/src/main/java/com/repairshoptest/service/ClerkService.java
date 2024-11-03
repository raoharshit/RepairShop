package com.repairshoptest.service;

import com.repairshop.exception.DuplicateUserException;
import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.model.Clerk;

public interface ClerkService {
	
	Clerk findById(int clerkId) throws ResourceNotFoundException;
	
	Clerk add(ClerkRequestDTO clerkRequestDTO) throws DuplicateUserException;
	
//	Clerk findByEmail(String email);
	
	Clerk authenticateClerk(String userName, String password) throws InvalidCredentialsException;
	
	Clerk update(int clerkId, ClerkRequestDTO clerkRequestDTO) throws ResourceNotFoundException;
	
//	boolean remove(int clerkId);
	
	PasswordChangeResponse updatePassword(int clerkId,PasswordChangeRequest passwordChangeRequest) throws ResourceNotFoundException,InvalidCredentialsException;

}
