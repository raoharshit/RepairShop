package com.repairshoptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer>{

	Invoice findByService(int serviceId);
	
}
