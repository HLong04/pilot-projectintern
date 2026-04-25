package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartID;
	
	@OneToOne
	@JoinColumn(name = "CustomerID", unique = true, nullable = false)
	private Customer customer;
	
	private LocalDateTime createdTime = LocalDateTime.now();
	
	private LocalDateTime updatedTime;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items;
}