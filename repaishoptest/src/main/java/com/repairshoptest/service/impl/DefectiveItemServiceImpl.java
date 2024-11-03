package com.repairshoptest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.repository.DefectiveItemRepo;
import com.repairshoptest.service.DefectiveItemService;

@Service
public class DefectiveItemServiceImpl implements DefectiveItemService{
	
	@Autowired
	private DefectiveItemRepo defectiveItemRepo;

	@Override
	@Transactional
	public DefectiveItem add(Clerk clerk, Customer customer, RepairServiceRequestDTO dto) throws ResourceNotFoundException{
		if(clerk == null) {
			throw new ResourceNotFoundException("Clerk not found");
		}else if(customer == null) {
			throw new ResourceNotFoundException("Customer not found");
		}else {
			DefectiveItem defectiveItem = new DefectiveItem();
			defectiveItem.setProductCode(dto.getProductCode());
			defectiveItem.setTitle(dto.getProductTitle());
			defectiveItem.setCategory(dto.getProductCategory());
			defectiveItem.setProductCode(dto.getProductCode());
			defectiveItem.setCreatedBy(clerk);
			defectiveItem.setCustomer(customer);
			defectiveItemRepo.save(defectiveItem);
			return defectiveItem;
		}
		
	}

}
