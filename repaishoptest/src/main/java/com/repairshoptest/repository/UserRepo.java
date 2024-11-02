package com.repairshoptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	

}
