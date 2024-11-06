package com.repairshoptest.service;

import java.util.List;

import com.repairshoptest.model.NewItem;

public interface NewItemService {
	
	NewItem findById(int id);
	
	List<NewItem> findByCategory(String category);
	
	List<NewItem> findAll();
}
