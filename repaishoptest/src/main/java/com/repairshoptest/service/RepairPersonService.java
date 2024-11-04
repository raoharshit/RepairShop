package com.repairshoptest.service;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.exception.DuplicateUserException;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.RepairPerson;

public interface RepairPersonService {
	
	RepairPerson findById(int id);
	
	public RepairPerson authenticateRepairPerson(String userName, String password);
	
	Page<RepairPerson> findBySearch(String search, int page, int limit, String specialty);
	
	RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO);
	
	RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO);
	
	PasswordChangeResponse updatePassword(int repairPersonId, PasswordChangeRequest passwordChangeRequest);

}
