package com.be.pilot_project.dao;

import com.be.pilot_project.dto.CartDTO;

import java.util.Optional;

public interface CartDao {
	Optional<CartDTO> findByCustomerId(Integer customerId);
}
