package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.OrderStatusDTO;
import com.be.pilot_project.entity.OrderStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper extends BaseMapper<OrderStatus, OrderStatusDTO> {
}
