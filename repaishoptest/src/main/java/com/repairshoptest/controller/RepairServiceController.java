package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.service.RepairServiceService;

@RestController
@RequestMapping("/repairservice")
public class RepairServiceController {
	
	@Autowired
	RepairServiceService repairServiceService;
	
	@PostMapping("/addService")
	public RepairService addService(@RequestHeader("clerkId") int clerkId,@RequestBody RepairServiceRequestDTO repairServiceRequestDTO){
		RepairService repairService = repairServiceService.add(clerkId, repairServiceRequestDTO);
		return repairService;
	}

}
