package com.repairshoptest.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.User;
import com.repairshoptest.repository.UserRepo;
import com.repairshoptest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User findById(int userId){
		Optional<User> optUser = userRepo.findById(userId);
		if(optUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found");
		}
		
		return optUser.get();
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepo.findByEmail(email);
		if(user == null) {
			throw new ResourceNotFoundException("User not found");
		}
		
		return user;
	}
	
	@Override
	@Transactional
	public User add(User user) {
		User save = userRepo.save(user);
		if(save == null) {
			throw new ResourceNotFoundException("User not found");
		}
		return user;
	}

}
