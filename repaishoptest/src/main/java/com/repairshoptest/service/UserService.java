package com.repairshoptest.service;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.model.User;

public interface UserService {
	
	User findById(int userId) throws ResourceNotFoundException;

}
