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
import com.repairshoptest.exception.ResourceNotFoundException;
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
	private RFARepo rfaRepo;
	
	@Autowired
	private ServiceStatusService serviceStatusService;
	
	@Autowired
	private RepairServiceService repairServiceService;
	
	@Autowired
	private NewItemService newItemService;
	


	@Override
	public Page<AdditionalItemRFA> findRFAForRole(String role, int userId, String search, int page, int limit) {
		if(limit == -1) {
			page = 0;
			limit = Integer.MAX_VALUE;
		}
		Pageable pageable = PageRequest.of(page, limit);

	    switch (role) {
	        case "customer":
	            return rfaRepo.findByCustomerAndDefectiveItemTitle(userId, search, pageable);
	        case "repairperson":
	            return rfaRepo.findByRepairPersonAndDefectiveItemTitle(userId, search, pageable);
	        default:
	            throw new IllegalArgumentException("Invalid role");
	    }
	}
	
	@Override
	public AdditionalItemRFA findById(int id){
		Optional<AdditionalItemRFA> optRFA = rfaRepo.findById(id);
		if(optRFA.isEmpty()) {
			throw new ResourceNotFoundException("No rfa found");
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
	public AdditionalItemRFA createRFA(int serviceId, AdditionalItemRFARequestDTO dto){
		RepairService repairService = repairServiceService.findById(serviceId);
		NewItem newItem = newItemService.findById(dto.getNewItemId());
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
			throw new RuntimeException("Could not create service status due to some error occurred");
		}
		throw new RuntimeException("Could not create additional item request due to some error occurred");
	}

	@Override
	@Transactional
	public boolean updateRFA(int id, String response){
		Optional<AdditionalItemRFA> optRFA = rfaRepo.findById(id);
		if(optRFA.isEmpty()) {
			throw new ResourceNotFoundException("Request not found");
		}
		AdditionalItemRFA rfa = optRFA.get();
			rfa.setApprovalStatus(response);
			rfa.getRepairService().setLatestStatus("Request " + response);
			AdditionalItemRFA save = rfaRepo.save(rfa);
			if(save != null) {
				ServiceStatus status = serviceStatusService.createStatus(save.getRepairService());
				if(status != null) {
					return true;
				}
				throw new RuntimeException("Could not create service status due to some error occurred");
			}
			throw new RuntimeException("Could not update additional item request due to some error occurred");
			
	}

	@Override
	public Double findAdditionalCharges(int year, int month) {
		return rfaRepo.findTotalServiceChargeAndItemPriceForMonthAndYear(month, year);
	}

	@Override
	public NewItem findMostRequestedItem(int year, int month) {
		List<Object[]> mostRequestedItems = rfaRepo.findMostRequestedItem(year, month);
		if (!mostRequestedItems.isEmpty()) {
		    Object[] mostRequestedItem = mostRequestedItems.get(0);
		    NewItem mostRequestedNewItem = (NewItem) mostRequestedItem[0];
		    return mostRequestedNewItem;
		}
		return null;
	}

}
