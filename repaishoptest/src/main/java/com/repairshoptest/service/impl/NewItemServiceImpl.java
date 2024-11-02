package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.model.NewItem;
import com.repairshoptest.repository.NewItemRepo;
import com.repairshoptest.service.NewItemService;
@Service
public class NewItemServiceImpl implements NewItemService{
	@Autowired
	NewItemRepo newItemRepo;
	
	@Override
	public List<NewItem> findByCategory(String category) {
		List<NewItem> list = newItemRepo.findByCategory(category);
		return list;
	}
	
	@Override
	public NewItem findById(int id) {
		Optional<NewItem> optItem = newItemRepo.findById(id);
		if(optItem.isEmpty()) {
			//throw new Exception("New item not found");
			return null;
		}
		
		return optItem.get();
	}

}
