package com.repairshoptest.service;

import org.springframework.data.domain.Page;

import com.repairshop.exception.DuplicateUserException;
import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.model.RepairPerson;

public interface RepairPersonService {
	
	RepairPerson findById(int id) throws ResourceNotFoundException;
	
//	RepairPerson findByEmail(String email);
	
	public RepairPerson authenticateRepairPerson(String userName, String password) throws InvalidCredentialsException;
	
//	List<RepairPerson> findBySpecialty(String specialty);
	
//	List<RepairPerson> findAll();
	
	Page<RepairPerson> findBySearch(String search, int page, int limit, String specialty);
	
	RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO) throws DuplicateUserException;
	
	RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO) throws ResourceNotFoundException;
	
//	boolean remove(int repairPersonId);
	
	PasswordChangeResponse updatePassword(int repairPersonId, PasswordChangeRequest passwordChangeRequest) throws ResourceNotFoundException,InvalidCredentialsException;

}
