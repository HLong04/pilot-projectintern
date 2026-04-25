package com.be.pilot_project.dao;

import com.be.pilot_project.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentDao {
	List<PaymentDTO> findAllWithOrderInfo();
	List<PaymentDTO> findByOrderIdWithOrderInfo(Integer orderId);
	Optional<PaymentDTO> findByIdWithOrderInfo(Integer paymentId);
}
