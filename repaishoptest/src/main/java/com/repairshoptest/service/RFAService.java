package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.AdditionalItemRFARequestDTO;
import com.repairshoptest.model.AdditionalItemRFA;

public interface RFAService {
	
	Page<AdditionalItemRFA> findRFAForRole(String role, int userId, String search, int page, int limit);
	AdditionalItemRFA findById(int id) throws ResourceNotFoundException;
	List<AdditionalItemRFA> findByRepairServiceId(int id);
	AdditionalItemRFA createRFA(int serviceId, AdditionalItemRFARequestDTO dto) throws Exception;
	boolean updateRFA(int id, String response) throws Exception;

}
