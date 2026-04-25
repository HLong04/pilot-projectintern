package com.be.pilot_project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDTO {
	private Integer productId;
	private Integer branchId;
	private Integer stockQuantity;
	private String productName;
	private String branchName;
}