package com.repairshoptest.service;

import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.model.Clerk;

public interface ClerkService {
	
	Clerk findById(int clerkId);
	
	Clerk add(ClerkRequestDTO clerkRequestDTO);
	
//	Clerk findByEmail(String email);
	
	Clerk authenticateClerk(String userName, String password);
	
	Clerk update(int clerkId, ClerkRequestDTO clerkRequestDTO);
	
//	boolean remove(int clerkId);
	
	PasswordChangeResponse updatePassword(int clerkId,PasswordChangeRequest passwordChangeRequest);

}
