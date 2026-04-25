package com.be.pilot_project.dao;

import com.be.pilot_project.dto.BranchDTO;

import java.util.List;
import java.util.Optional;

public interface BranchDao {
	List<BranchDTO> findAllWithProvince();
	List<BranchDTO> findActiveWithProvince();
	Optional<BranchDTO> findByIdWithProvince(Integer branchId);
}
