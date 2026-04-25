package com.be.pilot_project.repository;

import com.be.pilot_project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);
	
	Page<Product> findBySupplierId(Integer supplierId, Pageable pageable);
	
	Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);
	
	Page<Product> findByIsSelling(Boolean isSelling, Pageable pageable);
	
	@Query("SELECT p FROM Product p " +
			"WHERE (:categoryId IS NULL OR p.category.categoryID = :categoryId) " +
			"AND (:keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
			"AND (:isSelling IS NULL OR p.isSelling = :isSelling)")
	Page<Product> findWithFilters(
			@Param("categoryId") Integer categoryId,
			@Param("keyword") String keyword,
			@Param("isSelling") Boolean isSelling,
			Pageable pageable
	);
	
	@Query("SELECT DISTINCT p FROM Product p " +
			"LEFT JOIN FETCH p.photos " +
			"LEFT JOIN FETCH p.attributes " +
			"WHERE p.productID = :id")
	Optional<Product> findByIdWithDetails(@Param("id") Integer id);
}
