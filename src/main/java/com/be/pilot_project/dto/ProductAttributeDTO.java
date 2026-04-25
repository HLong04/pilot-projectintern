package com.be.pilot_project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeDTO {
	private Long attributeId;
	private Integer productId;
	private String attributeName;
	private String attributeValue;
	private Integer displayOrder;
}