package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Integer orderId;
	private LocalDateTime orderTime;
	private String deliveryProvince;
	private String deliveryAddress;
	private LocalDateTime acceptTime;
	private LocalDateTime shippedTime;
	private LocalDateTime finishedTime;
	private BigDecimal discountAmount = BigDecimal.ZERO;
	private Integer customerId;
	private Integer branchId;
	private Integer statusId;
	private Integer voucherId;
	private CustomerDTO customer;
	private OrderStatusDTO orderStatus;
	private BranchDTO branch;
	private List<OrderDetailDTO> orderDetails;
}