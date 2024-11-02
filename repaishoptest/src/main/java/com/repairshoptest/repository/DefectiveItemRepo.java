package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repairshoptest.model.Customer;
import com.repairshoptest.model.DefectiveItem;

public interface DefectiveItemRepo extends JpaRepository<DefectiveItem, Integer>{
	
	List<DefectiveItem> findByCustomer(Customer customer);

}
