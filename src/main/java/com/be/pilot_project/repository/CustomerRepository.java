package com.be.pilot_project.repository;

import com.be.pilot_project.entity.Customer;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	List<Customer> findByIsLocked(boolean isLocked);
	
	@Query("SELECT c FROM Customer c WHERE " +
			"LOWER(c.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Customer> searchCustomers(@Param("keyword") String keyword, Pageable pageable);
}
