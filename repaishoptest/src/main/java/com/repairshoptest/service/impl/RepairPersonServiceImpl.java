package com.repairshoptest.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.exception.DuplicateUserException;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.repository.RepairPersonRepo;
import com.repairshoptest.service.RepairPersonService;

@Service
public class RepairPersonServiceImpl implements RepairPersonService{
	
	@Autowired
	private RepairPersonRepo repairPersonRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public RepairPerson findById(int id){
		Optional<RepairPerson> optRepairPerson = repairPersonRepo.findById(id);
		if(optRepairPerson.isEmpty()) {
			throw new ResourceNotFoundException("Repairperson not found");
		}
		return optRepairPerson.get();
	}
	
	
	@Override
	public RepairPerson authenticateRepairPerson(String userName, String password){
		RepairPerson repairPerson = repairPersonRepo.findByEmail(userName);
		if(repairPerson == null) {
			 throw new InvalidCredentialsException("Incorrect username or password");
		}
		if(passwordEncoder.matches(password, repairPerson.getHashedPassword())) {
			return repairPerson;
		}
		throw new InvalidCredentialsException("Incorrect username or password");
	}
	
	
	@Override
	public Page<RepairPerson> findBySearch(String search, int page, int limit, String specialty) {
		if(limit == -1) {
			page = 0;
			limit = Integer.MAX_VALUE;
		}
		Pageable pageable = PageRequest.of(page, limit);
		return repairPersonRepo.findBySearchAndCategory(search, specialty, pageable);
	}
	
	
	

	@Override
	@Transactional
	public RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO){
		// TODO Auto-generated method stub
		if(repairPersonRepo.findByEmail(repairPersonRequestDTO.getEmail()) != null ) {
			 throw new DuplicateUserException("Customer with same email is already present");
		}
		
		RepairPerson repairPerson = repairPersonRequestDTO.getRepairPerson();
		String hashedPassword = passwordEncoder.encode(repairPersonRequestDTO.getPassword());
		repairPerson.setHashedPassword(hashedPassword);
		RepairPerson saved = repairPersonRepo.save(repairPerson);
		return saved;
	}

	@Override
	@Transactional
	public RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO){
		// TODO Auto-generated method stub
		Optional<RepairPerson> optRepairPerson = repairPersonRepo.findById(repairPersonId);
		
		if(optRepairPerson.isEmpty()) {
			throw new ResourceNotFoundException("RepairPerson not found");
		}
		RepairPerson repairPerson = optRepairPerson.get();
		repairPerson.setName(repairPersonRequestDTO.getName());
		repairPerson.setPhone(repairPersonRequestDTO.getPhone());
		repairPerson.setAddress(repairPersonRequestDTO.getAddress());
		RepairPerson updated = repairPersonRepo.save(repairPerson);
		
		return updated;
	}

	@Override
	@Transactional
	public PasswordChangeResponse updatePassword(int id, PasswordChangeRequest passwordChangeRequest){
		Optional<RepairPerson> optRepairPerson = (repairPersonRepo.findById(id));
		
		if(optRepairPerson.isEmpty()) {
			throw new ResourceNotFoundException("RepairPerson not found");
		}
		RepairPerson repairPerson = optRepairPerson.get();
		if(passwordEncoder.matches(passwordChangeRequest.getOldPassword(),repairPerson.getHashedPassword())) {
			String hashedPasssword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
			repairPerson.setHashedPassword(hashedPasssword);
			repairPersonRepo.save(repairPerson);
			PasswordChangeResponse passwordChangeResponse = new PasswordChangeResponse("Password changed successfully");
			return passwordChangeResponse;
		}
		 throw new InvalidCredentialsException("Old password is incorrect");
		
		
	}
	

	

}
