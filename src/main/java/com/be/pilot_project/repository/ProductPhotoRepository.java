package com.be.pilot_project.repository;

import com.be.pilot_project.entity.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {
	List<ProductPhoto> findByProduct_ProductID(Integer productID);
}
