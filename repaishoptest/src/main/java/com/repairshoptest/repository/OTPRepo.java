package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.OTP;

@Repository
public interface OTPRepo extends JpaRepository<OTP, Integer>{
	
	@Query("SELECT o FROM OTP o WHERE o.userId = :id ORDER BY o.createdAt DESC")
	List<OTP> findTopByUserIdOrderByCreatedAtDesc(@Param("id") int id);
	
	@Query("SELECT o FROM OTP o WHERE o.invoiceId = :id ORDER BY o.createdAt DESC")
	List<OTP> findTopByInvoiceIdOrderByCreatedAtDesc(@Param("id") int id);
}
