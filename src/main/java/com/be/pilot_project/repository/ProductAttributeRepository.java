package com.be.pilot_project.repository;

import com.be.pilot_project.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
	List<ProductAttribute> findByProduct_ProductID(Integer productID);
}
