package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.RepairPerson;

@Repository
public interface RepairPersonRepo extends JpaRepository<RepairPerson, Integer> {
	
	List<RepairPerson> findBySpecialty(String specialty);
	
    RepairPerson findByEmail(String email);
	
	RepairPerson findByPhone(String phone);

}
