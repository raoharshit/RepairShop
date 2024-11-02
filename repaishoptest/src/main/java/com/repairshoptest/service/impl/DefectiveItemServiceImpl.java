package com.repairshoptest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.DefectiveItemDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.repository.DefectiveItemRepo;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.DefectiveItemService;

@Service
public class DefectiveItemServiceImpl implements DefectiveItemService{
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ClerkService clerkService;
	
	@Autowired
	DefectiveItemRepo defectiveItemRepo;

	@Override
	@Transactional
	public DefectiveItem add(Clerk clerk, Customer customer, DefectiveItemDTO defectiveItemDTO) {
		if(clerk == null) {
			//throws new Exception("Clerk not found");
			return null;
		}else if(customer == null) {
			//throws new Exception("Customer not found");
			return null;
		}else {
			DefectiveItem defectiveItem = defectiveItemDTO.getDefectiveItem();
			defectiveItem.setCreatedBy(clerk);
			defectiveItem.setCustomer(customer);
			defectiveItemRepo.save(defectiveItem);
			return defectiveItem;
		}
		
	}

}
