package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.AdditionalItemRFARequestDTO;
import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.NewItem;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.repository.RFARepo;
import com.repairshoptest.service.NewItemService;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ServiceStatusService;

@Service
public class RFAServiceImpl implements RFAService{
	
	@Autowired
	RFARepo rfaRepo;
	
	@Autowired
	ServiceStatusService serviceStatusService;
	
	@Autowired
	RepairServiceService repairServiceService;
	
	@Autowired
	NewItemService newItemService;
	


	@Override
	public Page<AdditionalItemRFA> findRFAForRole(String role, int userId, String search, int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit);

	    switch (role) {
	        case "Customer":
	            return rfaRepo.findByCustomerAndDefectiveItemTitle(userId, search, pageable);
	        case "RepairPerson":
	            return rfaRepo.findByRepairPersonAndDefectiveItemTitle(userId, search, pageable);
	        default:
	            throw new IllegalArgumentException("Invalid role");
	    }
	}
	
	@Override
	public AdditionalItemRFA findById(int id) {
		Optional<AdditionalItemRFA> optRFA = rfaRepo.findById(id);
		if(optRFA.isEmpty()) {
			//throw new Exception("No rfa found");
			return null;
		}
		
		return optRFA.get();
	}

	@Override
	public List<AdditionalItemRFA> findByRepairServiceId(int id) {
		List<AdditionalItemRFA> list = rfaRepo.findByRepairService(id);
		return list;
	}
	
	@Override
	@Transactional
	public AdditionalItemRFA createRFA(int serviceId, AdditionalItemRFARequestDTO dto) {
		RepairService repairService = repairServiceService.findById(serviceId);
		NewItem newItem = newItemService.findById(dto.getNewItemId());
		if(repairService == null) {
			//throw new Exception("RepairService not found");
			return null;
		}
		if(newItem == null) {
			//throw new Exception("New Item not found");
			return null;
		}
		repairService.setLatestStatus("Waiting for Approval");
		AdditionalItemRFA rfa = dto.getRFA();
		rfa.setApprovalStatus("Requested");
		rfa.setRepairService(repairService);
		rfa.setNewItem(newItem);
		
		AdditionalItemRFA save = rfaRepo.save(rfa);
		
		if(save != null) {
			ServiceStatus status = serviceStatusService.createStatus(save.getRepairService());
			if(status != null) {
				return save;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public boolean updateRFA(int id, String response) {
		Optional<AdditionalItemRFA> optRFA = rfaRepo.findById(id);
		if(optRFA.isEmpty()) {
			//throw new Exception("Request not found");
			return false;
		}
		AdditionalItemRFA rfa = optRFA.get();
		if(response != null && !response.isEmpty()) {
			rfa.setApprovalStatus(response);
			rfa.getRepairService().setLatestStatus("Request " + response);
			AdditionalItemRFA save = rfaRepo.save(rfa);
			if(save != null) {
				ServiceStatus status = serviceStatusService.createStatus(save.getRepairService());
				if(status != null) {
					return true;
				}
			}
			
		}
		return false;
	}

}
