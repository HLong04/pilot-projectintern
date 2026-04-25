package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderdetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	
	@EmbeddedId
	private OrderDetailId id;
	
	@ManyToOne
	@MapsId("orderID")
	@JoinColumn(name = "OrderID")
	private Order order;
	
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "ProductID")
	private Product product;
	
	private Integer quantity;
	private java.math.BigDecimal salePrice;
	
	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderDetailId implements java.io.Serializable {
		private Integer orderID;
		private Integer productID;
	}
}
