package com.be.pilot_project.dao;

import com.be.pilot_project.dto.ShipperDTO;

import java.util.List;
import java.util.Optional;

public interface ShipperDao {
	List<ShipperDTO> findAll();
	Optional<ShipperDTO> findById(Integer shipperId);
}
