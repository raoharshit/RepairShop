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
public interface RFARepo extends JpaRepository<AdditionalItemRFA, Integer> {

	@Query("SELECT r FROM AdditionalItemRFA r " + "JOIN r.repairService s " + "JOIN s.customer c "
			+ "WHERE c.id = :customerId "
			+ "AND (:search = '' OR LOWER(s.defectiveItem.title) LIKE LOWER(CONCAT('%', :search, '%')))"
			+ "AND s.latestStatus != 'Closed'" + "ORDER BY r.updatedAt DESC")
	Page<AdditionalItemRFA> findByCustomerAndDefectiveItemTitle(@Param("customerId") int custId,
			@Param("search") String search, Pageable pageable);

	@Query("SELECT r FROM AdditionalItemRFA r " + "JOIN r.repairService s " + "JOIN s.assignedTo rp "
			+ "WHERE rp.id = :repairPersonId "
			+ "AND (:search = '' OR LOWER(s.defectiveItem.title) LIKE LOWER(CONCAT('%', :search, '%')))"
			+ "ORDER BY r.updatedAt DESC")
	Page<AdditionalItemRFA> findByRepairPersonAndDefectiveItemTitle(@Param("repairPersonId") int repairId,
			@Param("search") String search, Pageable pageable);

	@Query("SELECT r FROM AdditionalItemRFA r WHERE r.repairService.id = :serviceId")
	List<AdditionalItemRFA> findByRepairService(@Param("serviceId") int serviceId);

	@Query("SELECT SUM(r.serviceCharge + r.newItem.price) FROM AdditionalItemRFA r " + "JOIN r.repairService s "
			+ "WHERE s.latestStatus = 'Closed' AND " + "MONTH(s.updatedAt) = :month AND YEAR(r.updatedAt) = :year AND " + "r.approvalStatus = 'Approved'")
	Double findTotalServiceChargeAndItemPriceForMonthAndYear(@Param("month") int month, @Param("year") int year);

	@Query("SELECT r.newItem, COUNT(r) as requestCount FROM AdditionalItemRFA r " + "JOIN r.repairService s "
			+ "WHERE s.latestStatus = 'Closed' AND " + "MONTH(r.updatedAt) = :month AND YEAR(r.updatedAt) = :year "
			+ "GROUP BY r.newItem " + "ORDER BY requestCount DESC")
	List<Object[]> findMostRequestedItem(@Param("year") int year, @Param("month") int month);

}
