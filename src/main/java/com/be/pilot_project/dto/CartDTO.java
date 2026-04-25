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
public class CartDTO {
	private Integer cartId;
	private Integer customerId;
	private CustomerDTO customer;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private BigDecimal totalAmount;
	private List<CartItemDTO> items;
}