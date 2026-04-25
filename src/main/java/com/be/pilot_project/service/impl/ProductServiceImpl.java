package com.be.pilot_project.service.impl;

import com.be.pilot_project.dao.ProductDao;
import com.be.pilot_project.dto.ProductDTO;
import com.be.pilot_project.entity.Category;
import com.be.pilot_project.entity.Product;
import com.be.pilot_project.entity.Supplier;
import com.be.pilot_project.mapper.ProductMapper;
import com.be.pilot_project.repository.CategoryRepository;
import com.be.pilot_project.repository.ProductRepository;
import com.be.pilot_project.repository.SupplierRepository;
import com.be.pilot_project.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final SupplierRepository supplierRepository;
	private final ProductMapper productMapper;
	private final ProductDao productDao;
	
	@Override
	public List<ProductDTO> findAll() {
		return productDao.findAllWithRelations();
	}
	
	@Override
	public List<ProductDTO> findSellingProducts() {
		return productDao.findSellingWithRelations();
	}
	
	@Override
	public ProductDTO getById(Integer id) {
		return productDao.findByIdWithRelations(id)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product id: " + id));
	}
	
	@Override
	public ProductDTO create(ProductDTO dto) {
		Product product = productMapper.toEntity(dto);
		attachRelations(product, dto);
		return productMapper.toDto(productRepository.save(product));
	}
	
	@Override
	public ProductDTO update(Integer id, ProductDTO dto) {
		Product existing = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product id: " + id));
		
		existing.setProductName(dto.getProductName());
		existing.setProductDescription(dto.getProductDescription());
		existing.setUnit(dto.getUnit());
		existing.setPrice(dto.getPrice());
		existing.setPhoto(dto.getPhoto());
		existing.setIsSelling(dto.getIsSelling());
		
		attachRelations(existing, dto);
		return productMapper.toDto(productRepository.save(existing));
	}
	
	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}
	
	private void attachRelations(Product product, ProductDTO dto) {
		if (dto.getCategoryId() != null) {
			Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category id: " + dto.getCategoryId()));
			product.setCategory(category);
		}
		
		if (dto.getSupplierId() != null) {
			Supplier supplier = supplierRepository.findById(dto.getSupplierId())
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy supplier id: " + dto.getSupplierId()));
			product.setSupplier(supplier);
		}
	}
}
