package com.repairshoptest.service;

import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;

public interface LoginService {
	
	LoginResponse authenticateUser(LoginRequest loginRequest);

}
