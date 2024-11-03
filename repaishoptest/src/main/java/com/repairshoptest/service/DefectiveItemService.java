package com.repairshoptest.service;

import com.repairshoptest.dto.DefectiveItemDTO;
import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.DefectiveItem;

public interface DefectiveItemService {
	
	DefectiveItem add(Clerk clerk, Customer customer, RepairServiceRequestDTO dto);

}
