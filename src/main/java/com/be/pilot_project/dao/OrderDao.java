package com.be.pilot_project.dao;

import com.be.pilot_project.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
	List<OrderDTO> findAllWithRelations();
	List<OrderDTO> findByCustomerIdWithRelations(Integer customerId);
	Optional<OrderDTO> findByIdWithRelations(Integer orderId);
}
