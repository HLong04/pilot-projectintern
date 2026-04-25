package com.be.pilot_project.dao;

import com.be.pilot_project.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
	List<EmployeeDTO> findAll();
	List<EmployeeDTO> findWorking();
	Optional<EmployeeDTO> findById(Integer employeeId);
	Optional<EmployeeDTO> findByEmail(String email);
}
