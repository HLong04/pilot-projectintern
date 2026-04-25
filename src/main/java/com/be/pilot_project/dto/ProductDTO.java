package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Integer productId;
	private String productName;
	private String unit;
	private BigDecimal price;
	private Integer categoryId;
	private Integer supplierId;
	private String photo;
	private String productDescription;
	private Boolean isSelling;
	private CategoryDTO category;
	private SupplierDTO supplier;
	private List<ProductPhotoDTO> photos;
	private List<ProductAttributeDTO> attributes;
}