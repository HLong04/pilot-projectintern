package com.be.pilot_project.service.interfaces;

import com.be.pilot_project.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	List<ProductDTO> findAll();
	List<ProductDTO> findSellingProducts();
	ProductDTO getById(Integer id);
	ProductDTO create(ProductDTO dto);
	ProductDTO update(Integer id, ProductDTO dto);
	void delete(Integer id);
}
