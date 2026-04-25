package com.be.pilot_project.dao;

import com.be.pilot_project.dto.SupplierDTO;

import java.util.List;
import java.util.Optional;

public interface SupplierDao {
	List<SupplierDTO> findAllWithProvince();
	Optional<SupplierDTO> findByIdWithProvince(Integer supplierId);
}
