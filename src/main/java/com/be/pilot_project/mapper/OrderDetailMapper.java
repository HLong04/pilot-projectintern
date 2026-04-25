package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.OrderDetailDTO;
import com.be.pilot_project.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
	@Mapping(source = "product.productID", target = "productId")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "product.photo", target = "photo")
	OrderDetailDTO toDto(OrderDetail entity);
	
	@Mapping(source = "productId", target = "product.productID")
	OrderDetail toEntity(OrderDetailDTO dto);
}
