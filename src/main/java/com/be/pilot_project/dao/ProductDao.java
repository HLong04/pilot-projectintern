package com.be.pilot_project.dao;

import com.be.pilot_project.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
	List<ProductDTO> findAllWithRelations();
	List<ProductDTO> findSellingWithRelations();
	Optional<ProductDTO> findByIdWithRelations(Integer productId);
}
