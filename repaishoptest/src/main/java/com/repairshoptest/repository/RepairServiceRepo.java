package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairService;

@Repository
public interface RepairServiceRepo extends JpaRepository<RepairService, Integer> {

	List<RepairService> findByCustomer(Customer customer);

	List<RepairService> findByCreatedBy(Clerk createdBy);

	@Query("SELECT rs FROM RepairService rs " + "JOIN rs.customer c " + "JOIN rs.defectiveItem di "
			+ "WHERE c.id = :customerId " + "AND (rs.code LIKE %:search% " + "OR di.title LIKE %:search%)"
			+ "ORDER BY rs.updatedAt DESC")
	Page<RepairService> findServicesForCustomer(@Param("customerId") int custId, @Param("search") String search,
			Pageable pageable);

	@Query("SELECT rs FROM RepairService rs " + "JOIN rs.customer c " + "JOIN rs.defectiveItem di "
			+ "JOIN rs.assignedTo rp " + "WHERE rp.id = :repairpersonId " + "AND (rs.code LIKE %:search% "
			+ "OR c.name LIKE %:search% " + "OR di.title LIKE %:search%)" + "ORDER BY rs.updatedAt DESC")
	Page<RepairService> findServicesForRepairperson(@Param("repairpersonId") int repairId,
			@Param("search") String search, Pageable pageable);

	@Query("SELECT rs FROM RepairService rs " + "JOIN rs.customer c " + "JOIN rs.defectiveItem di "
			+ "JOIN rs.createdBy cl " + "WHERE (rs.code LIKE %:search% " + "OR c.name LIKE %:search% "
			+ "OR di.title LIKE %:search%) " + "AND (:onlyMine = false OR cl.id = :clerkId)"
			+ "ORDER BY rs.updatedAt DESC")
	Page<RepairService> findServicesForClerk(@Param("search") String search, @Param("onlyMine") Boolean onlyMine,
			@Param("clerkId") int clerkId, Pageable pageable);

	@Query("SELECT COUNT(s) FROM RepairService s WHERE " + "YEAR(s.createdAt) = :year AND MONTH(s.createdAt) = :month")
	Long countByCreatedAtMonthAndYear(@Param("year") int year, @Param("month") int month);

	@Query("SELECT COUNT(s) FROM RepairService s WHERE " + "s.latestStatus = 'Closed' AND "
			+ "YEAR(s.updatedAt) = :year AND MONTH(s.updatedAt) = :month")
	Long countClosedServicesByUpdatedAtMonthAndYear(@Param("year") int year, @Param("month") int month);

	@Query("SELECT SUM(s.baseCharge) FROM RepairService s WHERE " + "s.latestStatus = 'Closed' AND "
			+ "YEAR(s.updatedAt) = :year AND MONTH(s.updatedAt) = :month")
	Double sumServiceChargesByClosedStatusAndDate(@Param("year") int year, @Param("month") int month);

}
