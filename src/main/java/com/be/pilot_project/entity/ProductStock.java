package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "productstocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {
	
	@EmbeddedId
	private ProductStockId id;
	
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "ProductID")
	private Product product;
	
	@ManyToOne
	@MapsId("branchID")
	@JoinColumn(name = "BranchID")
	private Branch branch;
	
	private Integer stockQuantity = 0;
	
	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProductStockId implements Serializable {
		private Integer productID;
		private Integer branchID;
	}
}