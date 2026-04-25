package com.be.pilot_project.dao;

import com.be.pilot_project.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
	List<CustomerDTO> findAllWithProvince();
	Optional<CustomerDTO> findByIdWithProvince(Integer customerId);
}
