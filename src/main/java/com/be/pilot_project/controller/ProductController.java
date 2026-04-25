package com.be.pilot_project.controller;

import com.be.pilot_project.dto.ProductDTO;
import com.be.pilot_project.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/products")
@AllArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping
	public List<ProductDTO> getPublicProducts(@RequestParam(required = false) Boolean selling) {
		if (Boolean.TRUE.equals(selling)) {
			return productService.findSellingProducts();
		}
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ProductDTO getPublicProductDetail(@PathVariable Integer id) {
		return productService.getById(id);
	}
	
	@PostMapping()
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
		return productService.create(dto);
	}
	
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO dto) {
		return productService.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.delete(id);
	}
}
