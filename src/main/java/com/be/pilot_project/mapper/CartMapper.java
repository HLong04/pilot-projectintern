package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.CartDTO;
import com.be.pilot_project.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
	@Mapping(source = "customer.customerID", target = "customerId")
	CartDTO toDto(Cart entity);
	
	@Mapping(source = "customerId", target = "customer.customerID")
	Cart toEntity(CartDTO dto);
}
