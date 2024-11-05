package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.ServiceStatus;

@Repository
public interface ServiceStatusRepo extends JpaRepository<ServiceStatus, Integer> {

	@Query("SELECT ss FROM ServiceStatus ss WHERE ss.repairService.id = :serviceId " + "ORDER BY ss.createdAt DESC")
	List<ServiceStatus> findByRepairServiceId(@Param("serviceId") int serviceId);

}
