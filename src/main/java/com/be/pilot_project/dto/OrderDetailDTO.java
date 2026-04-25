package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
	private Integer productId;
	private Integer quantity;
	private BigDecimal salePrice;
	private String productName;
	private String photo;
	private BigDecimal subTotal;
}