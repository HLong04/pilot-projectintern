package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ProductPhotoDTO;
import com.be.pilot_project.entity.ProductPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPhotoMapper {
	@Mapping(source = "product.productID", target = "productId")
	ProductPhotoDTO toDto(ProductPhoto entity);
	
	@Mapping(source = "productId", target = "product.productID")
	ProductPhoto toEntity(ProductPhotoDTO dto);
}
