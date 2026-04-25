package com.be.pilot_project.service.interfaces;

import com.be.pilot_project.dto.OrderDTO;

import java.util.List;

public interface OrderService {
	List<OrderDTO> findAll();
	List<OrderDTO> findByCustomerId(Integer customerId);
	OrderDTO getById(Integer id);
	OrderDTO create(OrderDTO dto);
	OrderDTO updateStatus(Integer orderId, Integer status);
	void delete(Integer orderId);
}
