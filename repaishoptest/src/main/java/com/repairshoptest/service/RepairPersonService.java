package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.model.RepairPerson;

public interface RepairPersonService {
	
	RepairPerson findById(int id);
	
	RepairPerson findByEmail(String email);
	
	public RepairPerson authenticateRepairPerson(String userName, String password);
	
	List<RepairPerson> findBySpecialty(String specialty);
	
	List<RepairPerson> findAll();
	
	Page<RepairPerson> findBySearch(String search, int page, int limit, String specialty);
	
	RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO);
	
	RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO);
	
	boolean remove(int repairPersonId);
	
	PasswordChangeResponse updatePassword(int repairPersonId, PasswordChangeRequest passwordChangeRequest);

}
