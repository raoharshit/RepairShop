package com.repairshoptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repairshoptest.model.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer>{

}
