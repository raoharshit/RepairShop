package com.repairshoptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;

@Repository
public interface ClerkRepo extends JpaRepository<Clerk, Integer>{
	
    Clerk findByEmail(String email);
	
	Clerk findByPhone(String phone);

}
