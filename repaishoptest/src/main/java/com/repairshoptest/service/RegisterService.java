package com.repairshoptest.service;

import com.repairshoptest.dto.RegisterRequest;
import com.repairshoptest.dto.RegisterResponse;

public interface RegisterService {
	
	RegisterResponse registerUser(RegisterRequest resgisterRequest);

}
