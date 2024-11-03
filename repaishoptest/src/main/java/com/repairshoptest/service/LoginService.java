package com.repairshoptest.service;

import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;

public interface LoginService {
	
	LoginResponse authenticateUser(LoginRequest loginRequest) throws ResourceNotFoundException,InvalidCredentialsException;

}
