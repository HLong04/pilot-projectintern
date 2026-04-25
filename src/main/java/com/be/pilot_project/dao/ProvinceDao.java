package com.be.pilot_project.dao;

import com.be.pilot_project.dto.ProvinceDTO;

import java.util.List;
import java.util.Optional;

public interface ProvinceDao {
	List<ProvinceDTO> findAll();
	Optional<ProvinceDTO> findByName(String provinceName);
}
