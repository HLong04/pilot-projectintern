package com.be.pilot_project.repository;

import com.be.pilot_project.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, ProductStock.ProductStockId> {
	List<ProductStock> findByProduct_ProductID(Integer productID);
	List<ProductStock> findByBranch_BranchID(Integer branchID);
}
