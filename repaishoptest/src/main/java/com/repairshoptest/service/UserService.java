package com.repairshoptest.service;

import com.repairshoptest.model.User;

public interface UserService {
	
	User findById(int userId);
	
	User findByEmail(String email);
	
	User add(User user);

}
