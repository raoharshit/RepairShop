package com.repairshoptest.service;

import java.util.List;

import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.model.RepairPerson;

public interface RepairPersonService {
	
	RepairPerson findById(int id);
	
	RepairPerson findByEmail(String email);
	
	public RepairPerson authenticateUser(String userName, String password);
	
	List<RepairPerson> findBySpecialty(String specialty);
	
	List<RepairPerson> findAll();
	
	RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO);
	
	RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO);
	
	boolean remove(int repairPersonId);
	
	boolean updatePassword(int repairPersonId, String password);

}
