package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.NewItem;

@Repository
public interface NewItemRepo extends JpaRepository<NewItem, Integer>{
	
	List<NewItem> findByCategory(String category);
	
	List<NewItem> findAll();
	
}
