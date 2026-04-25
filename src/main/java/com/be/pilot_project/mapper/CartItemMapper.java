package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.CartItemDTO;
import com.be.pilot_project.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
	
	@Mapping(source = "product.productID", target = "productId")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "product.photo", target = "photo")
	@Mapping(source = "product.price", target = "price")
	CartItemDTO toDto(CartItem entity);
	
	@Mapping(source = "productId", target = "product.productID")
	CartItem toEntity(CartItemDTO dto);
}
