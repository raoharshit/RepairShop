package com.repairshoptest.service;

import java.util.List;

import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;

public interface ServiceStatusService {
	
	List<ServiceStatus> findByRepairServiceId(int repairServiceId);
	
	ServiceStatus createStatus(RepairService repairService);
	
}
