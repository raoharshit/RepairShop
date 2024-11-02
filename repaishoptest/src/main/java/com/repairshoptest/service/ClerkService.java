package com.repairshoptest.service;

import com.repairshoptest.dto.ClerkDTO;
import com.repairshoptest.model.Clerk;

public interface ClerkService {
	
	Clerk findById(int clerkId);
	
	Clerk add(ClerkDTO clerkDTO);
	
	Clerk findByEmail(String email);
	
	public Clerk authenticateUser(String userName, String password);
	
	Clerk update(int clerkId, ClerkDTO clerkDTO);
	
	boolean remove(int clerkId);
	
	boolean updatePassword(int clerkId,String password);

}
