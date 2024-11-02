package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.repository.RepairPersonRepo;
import com.repairshoptest.service.RepairPersonService;

@Service
public class RepairPersonServiceImpl implements RepairPersonService{
	
	@Autowired
	RepairPersonRepo repairPersonRepo;
	
	@Override
	public RepairPerson findById(int id) {
		Optional<RepairPerson> optRepairPerson = repairPersonRepo.findById(id);
		if(optRepairPerson.isEmpty()) {
			//throws new Exception("Repairperson with " + id + " is not present");
//			return null;
		}
		return optRepairPerson.get();
	}
	
	@Override
	public RepairPerson findByEmail(String email) {
		RepairPerson repairPerson = repairPersonRepo.findByEmail(email);
		if(repairPerson == null) {
			//throw new Exception("RepairPeron not found");
			return null;
		}
		
		return repairPerson;
	}
	
	@Override
	public RepairPerson authenticateUser(String userName, String password) {
		RepairPerson repairPerson = repairPersonRepo.findByEmail(userName);
		if(repairPerson.getHashedPassword().equals(password)) {
			return repairPerson;
		}
		//throw new Exception("Invalid User");
		return null;
	}

	@Override
	public List<RepairPerson> findBySpecialty(String specialty) {
		List<RepairPerson> list = repairPersonRepo.findBySpecialty(specialty);
		return list;
	}

	@Override
	public RepairPerson add(RepairPersonRequestDTO repairPersonRequestDTO) {
		// TODO Auto-generated method stub
		if(repairPersonRepo.findByEmail(repairPersonRequestDTO.getEmail()) != null ) {
			// throw new Exception(Customer with same email is already present)
			return null;
		}
		
		RepairPerson repairPerson = repairPersonRequestDTO.getRepairPerson();
		
		//pasword hasing code
		//String hashedPassword = hasPassword(repairPerson.getHashedPassword());
		//repairPerson.setHashedPassword(hashedPassword);
		RepairPerson saved = repairPersonRepo.save(repairPerson);
		
		return saved;
	}

	@Override
	public RepairPerson update(int repairPersonId, RepairPersonRequestDTO repairPersonRequestDTO) {
		// TODO Auto-generated method stub
		Optional<RepairPerson> optRepairPerson = repairPersonRepo.findById(repairPersonId);
		
		if(optRepairPerson.isEmpty()) {
			//throw new Exception("RepairPerson not found");
			return null;
		}
		RepairPerson repairPerson = optRepairPerson.get();
		repairPerson.setName(repairPersonRequestDTO.getName());
		repairPerson.setPhone(repairPersonRequestDTO.getPhone());
		repairPerson.setAddress(repairPersonRequestDTO.getAddress());
		RepairPerson updated = repairPersonRepo.save(repairPerson);
		
		return updated;
	}

	@Override
	public boolean updatePassword(int id, String password) {
		Optional<RepairPerson> optRepairPerson = (repairPersonRepo.findById(id));
		
		if(optRepairPerson.isEmpty()) {
			//throw new Exception("RepairPerson not found");
			return false;
		}
		RepairPerson repairPerson = optRepairPerson.get();
		//password hashing code
		//String hashedPassword = hashPassword(password);
		//repairPerson.setHashedPassword(hashedPassword);
		repairPersonRepo.save(repairPerson);
		return true;
		
		
	}

	@Override
	public List<RepairPerson> findAll() {
		// TODO Auto-generated method stub
		List<RepairPerson> list = repairPersonRepo.findAll();
		return list;
	}

	@Override
	public boolean remove(int repairPersonId) {
		// TODO Auto-generated method stub
		Optional<RepairPerson> optRepairPerson = repairPersonRepo.findById(repairPersonId);
		if(optRepairPerson.isEmpty()) {
			//throws new Exception("Repairperson with " + id + " is not present");
//			return false;
		}
		repairPersonRepo.delete(optRepairPerson.get());
		return true;
	}

	

}
