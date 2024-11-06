package com.repairshoptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

	@Query("SELECT i FROM Invoice i WHERE i.repairService.id = :id")
	Invoice findByRepairService(@Param("id") int id);

	@Query("SELECT SUM(i.totalAmount) FROM Invoice i WHERE "
			+ "MONTH(i.createdAt) = :month AND YEAR(i.createdAt) = :year")
	Double findTotalAmountSumForMonthAndYear(@Param("month") int month, @Param("year") int year);

}
