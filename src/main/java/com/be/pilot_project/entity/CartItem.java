package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "cartitems")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartItem {
	
	@EmbeddedId
	private CartItemId id;
	
	@ManyToOne
	@MapsId("cartID")
	@JoinColumn(name = "CartID")
	private Cart cart;
	
	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "ProductID")
	private Product product;
	
	@Column(nullable = false)
	private Integer quantity = 1;
	
	@Embeddable
	@Data @NoArgsConstructor @AllArgsConstructor
	public static class CartItemId implements Serializable {
		private Integer cartID;
		private Integer productID;
	}
}