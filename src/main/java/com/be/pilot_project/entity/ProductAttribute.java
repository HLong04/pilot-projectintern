package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productattributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attributeID;
	
	@ManyToOne
	@JoinColumn(name = "ProductID", nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private String attributeName;
	
	@Column(nullable = false)
	private String attributeValue;
	
	private Integer displayOrder;
}