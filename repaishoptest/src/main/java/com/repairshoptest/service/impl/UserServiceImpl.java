package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.model.User;
import com.repairshoptest.repository.UserRepo;
import com.repairshoptest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public User findById(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		if(optUser.isEmpty()) {
			//throw new Exception("User not found");
			return null;
		}
		
		return optUser.get();
	}

}
