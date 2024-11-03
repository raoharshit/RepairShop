package com.repairshoptest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.repository.ServiceStatusRepo;
import com.repairshoptest.service.ServiceStatusService;

@Service
public class ServiceStatusServiceImpl implements ServiceStatusService{
	
	@Autowired
	ServiceStatusRepo serviceStatusRepo;
	
	@Override
	public List<ServiceStatus> findByRepairServiceId(int repairServiceId) {
		List<ServiceStatus> list = serviceStatusRepo.findByRepairServiceId(repairServiceId);
		return list;
	}

	@Override
	@Transactional
	public ServiceStatus createStatus(RepairService repairService) throws ResourceNotFoundException{
		ServiceStatus serviceStatus = new ServiceStatus();
		if(repairService == null) {
			throw new ResourceNotFoundException("RepairService not found");
		}
		serviceStatus.setDescription(repairService.getLatestStatus());
		serviceStatus.setRepairService(repairService);
		ServiceStatus save = serviceStatusRepo.save(serviceStatus);
		return save;
	}
	
	

}
