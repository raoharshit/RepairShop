package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshoptest.dto.RepairServiceDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.DefectiveItem;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.repository.RepairServiceRepo;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.DefectiveItemService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ServiceStatusService;
import com.repairshoptest.utils.ServiceCodeGenerator;

@Service
public class RepairServiceServiceImpl implements RepairServiceService{
	
	@Autowired
	RepairServiceRepo repairServiceRepo;
	
	@Autowired
	ServiceStatusService serviceStatusService;
	
	@Autowired
	DefectiveItemService defectiveItemService;
	
	@Autowired
	ClerkService clerkService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RepairPersonService repairPersonService;
	
	@Override
	public List<RepairService> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepairService findById(int id) {
		// TODO Auto-generated method stub
		Optional<RepairService> optRS = repairServiceRepo.findById(id);
		if(optRS.isEmpty()) {
			//throw new Exception("Service not found");
			return null;
		}
		return optRS.get();
	}

	@Override
	public List<RepairService> findByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepairService> findByClerk(Clerk clerk) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<RepairService> getServicesForRole(String role, int userId, Boolean onlyMine, String search, int page,
			int size) {
		Pageable pageable = PageRequest.of(page, size);

	    switch (role) {
	        case "Clerk":
	            return repairServiceRepo.findServicesForClerk(search,onlyMine,userId, pageable);
	        case "Customer":
	            return repairServiceRepo.findServicesForCustomer(userId, search, pageable);
	        case "RepairPerson":
	            return repairServiceRepo.findServicesForRepairperson(userId, search, pageable);
	        default:
	            throw new IllegalArgumentException("Invalid role");
	    }
	}
	

	@Override
	@Transactional
	public RepairService add(int clerkId, RepairServiceDTO repairServiceDTO) {
		System.out.println(clerkId);
		System.out.println(repairServiceDTO.getCustId());
		System.out.println(repairServiceDTO.getRepairId());
		Clerk clerk = clerkService.findById(clerkId);
		Customer customer = customerService.findById(repairServiceDTO.getCustId());
		RepairPerson repairPerson = repairPersonService.findById(repairServiceDTO.getRepairId());
		if(clerk == null) {
			//throws new Exception("Clerk with " + clerkId + " is not present");
			return null;
		}else if(customer == null) {
			//throws new Exception("Customer not found");
			return null;
		}else if(repairPerson == null) {
			//throws new Exception("RepairPerson not found");
			return null;
		}else {
			RepairService repairService = repairServiceDTO.getRepairService();
			repairService.setCode(ServiceCodeGenerator.generateServiceCode());
			DefectiveItem defectiveItem = defectiveItemService.add(clerk, customer, repairServiceDTO.getDefectiveItemDTO());
			repairService.setDefectiveItem(defectiveItem);
			repairService.setCreatedBy(clerk);
			repairService.setCustomer(customer);
			repairService.setAssignedTo(repairPerson);
			repairService.setLatestStatus("Assigned to repairperson");
			RepairService save = repairServiceRepo.save(repairService);
			ServiceStatus status = serviceStatusService.createStatus(repairService);
			if(status != null) {
				return save;
			}
			return null;
		}
		
	}
	@Override
	public boolean closeService(int id) {
		Optional<RepairService> optRepairService = repairServiceRepo.findById(id);
		if(optRepairService.isEmpty()) {
			//throw new Exception("Service not found");
			return false;
		}
		RepairService repairService = optRepairService.get();
		repairService.setLatestStatus("Closed");
		RepairService save = repairServiceRepo.save(repairService);
		if(save == null) {
			//throw new Exception("Some error occurred");
			return false;
		}
		
		ServiceStatus status = serviceStatusService.createStatus(repairService);
		if(status != null) {
			return true;
		}
		
		return false;
	}

	

}
