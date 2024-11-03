package com.repairshoptest.service;

import java.util.List;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.model.NewItem;

public interface NewItemService {
	
	NewItem findById(int id) throws ResourceNotFoundException;
	List<NewItem> findByCategory(String category) throws ResourceNotFoundException;
	
}
