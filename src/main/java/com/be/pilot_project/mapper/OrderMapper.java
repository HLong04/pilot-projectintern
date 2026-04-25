package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.OrderDTO;
import com.be.pilot_project.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderDetailMapper.class})
public interface OrderMapper {
	@Mapping(source = "customer.customerID", target = "customerId")
	@Mapping(source = "branch.branchID", target = "branchId")
	@Mapping(source = "orderStatus.status", target = "statusId")
	OrderDTO toDto(Order entity);
	
	@Mapping(source = "customerId", target = "customer.customerID")
	@Mapping(source = "branchId", target = "branch.branchID")
	@Mapping(source = "statusId", target = "orderStatus.status")
	Order toEntity(OrderDTO dto);
}
