package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ProductAttributeDTO;
import com.be.pilot_project.entity.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {
	@Mapping(source = "product.productID", target = "productId")
	ProductAttributeDTO toDto(ProductAttribute entity);
	
	@Mapping(source = "productId", target = "product.productID")
	ProductAttribute toEntity(ProductAttributeDTO dto);
}
