package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ProductDTO;
import com.be.pilot_project.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductPhotoMapper.class, ProductAttributeMapper.class})
public interface ProductMapper {
	
	@Mapping(source = "category.categoryID", target = "categoryId")
	@Mapping(source = "supplier.supplierID", target = "supplierId")
	ProductDTO toDto(Product entity);
	
	@Mapping(source = "categoryId", target = "category.categoryID")
	@Mapping(source = "supplierId", target = "supplier.supplierID")
	@Mapping(target = "photos", ignore = true)
	Product toEntity(ProductDTO dto);
}
