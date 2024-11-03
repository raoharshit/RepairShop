package com.repairshoptest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.model.NewItem;
import com.repairshoptest.repository.NewItemRepo;
import com.repairshoptest.service.NewItemService;
@Service
public class NewItemServiceImpl implements NewItemService{
	@Autowired
	private NewItemRepo newItemRepo;
	
	@Override
	public List<NewItem> findByCategory(String category) throws ResourceNotFoundException{
		List<NewItem> list = newItemRepo.findByCategory(category);
		if(list == null) {
			throw new ResourceNotFoundException("No newitem found");
		}
		return list;
	}
	
	@Override
	public NewItem findById(int id) throws ResourceNotFoundException{
		Optional<NewItem> optItem = newItemRepo.findById(id);
		if(optItem.isEmpty()) {
			throw new ResourceNotFoundException("New item not found");
		}
		
		return optItem.get();
	}

}
