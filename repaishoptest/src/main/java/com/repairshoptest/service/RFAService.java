package com.repairshoptest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.repairshoptest.dto.AdditionalItemRFARequestDTO;
import com.repairshoptest.model.AdditionalItemRFA;

public interface RFAService {
	
	Page<AdditionalItemRFA> findRFAForRole(String role, int userId, String search, int page, int limit);
	AdditionalItemRFA findById(int id);
	List<AdditionalItemRFA> findByRepairServiceId(int id);
	AdditionalItemRFA createRFA(int serviceId, AdditionalItemRFARequestDTO dto);
	boolean updateRFA(int id, String response);

}
