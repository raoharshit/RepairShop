package com.repairshoptest.service;

import com.repairshoptest.dto.RegisterRequest;
import com.repairshoptest.dto.RegisterResponse;
import com.repairshoptest.exception.DuplicateUserException;

public interface RegisterService {
	
	RegisterResponse registerUser(RegisterRequest resgisterRequest);

}
