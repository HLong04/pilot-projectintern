package com.be.pilot_project.dao;

import com.be.pilot_project.dto.ProductStockDTO;

import java.util.List;

public interface ProductStockDao {
	List<ProductStockDTO> findAllWithRelations();
	List<ProductStockDTO> findByProductIdWithRelations(Integer productId);
	List<ProductStockDTO> findByBranchIdWithRelations(Integer branchId);
}
