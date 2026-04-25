package com.be.pilot_project.repository;

import com.be.pilot_project.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
	List<Branch> findByIsActiveTrue();
}
