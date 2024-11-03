package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.AdditionalItemRFA;

@Repository
public interface RFARepo extends JpaRepository<AdditionalItemRFA, Integer>{

	@Query("SELECT r FROM AdditionalItemRFA r " +
			"JOIN r.repairService s " +
			"JOIN s.customer c " +
			"WHERE c.id = :customerId " +
			"AND (:search = '' OR LOWER(s.defectiveItem.title) LIKE LOWER(CONCAT('%', :search, '%')))"+
	"AND s.latestStatus != 'Closed'")
Page<AdditionalItemRFA> findByCustomerAndDefectiveItemTitle(@Param("customerId") int custId, 
		@Param("search") String search, 
		Pageable pageable);

@Query("SELECT r FROM AdditionalItemRFA r " +
		"JOIN r.repairService s " +
		"JOIN s.assignedTo rp " +
		"WHERE rp.id = :repairPersonId " +
		"AND (:search = '' OR LOWER(s.defectiveItem.title) LIKE LOWER(CONCAT('%', :search, '%')))")
Page<AdditionalItemRFA> findByRepairPersonAndDefectiveItemTitle(@Param("repairPersonId") int repairId, 
		@Param("search") String search, 
		Pageable pageable);
@Query("SELECT r FROM AdditionalItemRFA r WHERE r.repairService.id = :serviceId")
List<AdditionalItemRFA> findByRepairService(@Param("serviceId") int serviceId);

}
