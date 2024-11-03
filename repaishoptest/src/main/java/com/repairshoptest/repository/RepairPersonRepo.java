package com.repairshoptest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.repairshoptest.model.RepairPerson;

@Repository
public interface RepairPersonRepo extends JpaRepository<RepairPerson, Integer> {
	
	List<RepairPerson> findBySpecialty(String specialty);
	
    RepairPerson findByEmail(String email);
	
	RepairPerson findByPhone(String phone);
	
	@Query("SELECT rp FROM RepairPerson rp " +
		       "WHERE (" +
		       "LOWER(rp.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		       "LOWER(rp.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
		       "LOWER(rp.phone) LIKE LOWER(CONCAT('%', :search, '%'))) " +
		       "AND (:specialty = '' OR rp.specialty = :specialty)")
		Page<RepairPerson> findBySearchAndCategory(
		        @Param("search") String search, 
		        @Param("specialty") String specialty, 
		        Pageable pageable);

}
