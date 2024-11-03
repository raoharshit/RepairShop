package com.repairshoptest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.RepairServiceRequestDTO;
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
	private RepairServiceRepo repairServiceRepo;
	
	@Autowired
	private ServiceStatusService serviceStatusService;
	
	@Autowired
	private DefectiveItemService defectiveItemService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RepairPersonService repairPersonService;
	
//	@Override
//	public List<RepairService> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public RepairService findById(int id) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Optional<RepairService> optRS = repairServiceRepo.findById(id);
		if(optRS.isEmpty()) {
			throw new ResourceNotFoundException("Service not found");
		}
		return optRS.get();
	}

//	@Override
//	public List<RepairService> findByCustomer(Customer customer) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<RepairService> findByClerk(Clerk clerk) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
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
	public RepairService add(int clerkId, RepairServiceRequestDTO repairServiceRequestDTO) throws Exception {
//		System.out.println(clerkId);
//		System.out.println(repairServiceRequestDTO.getCustId());
//		System.out.println(repairServiceRequestDTO.getRepairId());
		Clerk clerk = clerkService.findById(clerkId);
		Customer customer = customerService.findById(repairServiceRequestDTO.getCustId());
		RepairPerson repairPerson = repairPersonService.findById(repairServiceRequestDTO.getRepairId());
		
			RepairService repairService = repairServiceRequestDTO.getRepairService();
			repairService.setCode(ServiceCodeGenerator.generateServiceCode());
			DefectiveItem defectiveItem = defectiveItemService.add(clerk, customer, repairServiceRequestDTO);
			repairService.setDefectiveItem(defectiveItem);
			repairService.setCreatedBy(clerk);
			repairService.setCustomer(customer);
			repairService.setAssignedTo(repairPerson);
			repairService.setLatestStatus("Assigned to repairperson");
			RepairService save = repairServiceRepo.save(repairService);
			if(save != null) {
				ServiceStatus status = serviceStatusService.createStatus(repairService);
				if(status != null) {
					return save;
				}
				throw new Exception("Could not create service status due to some error");
			}
			throw new Exception("Could not create service due to some error");
		
	}
	@Override
	public boolean closeService(int id) throws Exception{
		Optional<RepairService> optRepairService = repairServiceRepo.findById(id);
		if(optRepairService.isEmpty()) {
			throw new ResourceNotFoundException("Service not found");
		}
		RepairService repairService = optRepairService.get();
		repairService.setLatestStatus("Closed");
		RepairService save = repairServiceRepo.save(repairService);
		if(save == null) {
			throw new Exception("Could not close service due to Some error occurred");
		}
		
		ServiceStatus status = serviceStatusService.createStatus(repairService);
		if(status != null) {
			return true;
		}
		throw new Exception("Could not create service status due to some error");
	}

	

}
