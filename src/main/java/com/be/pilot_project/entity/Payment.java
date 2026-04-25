package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentID;
	
	@ManyToOne
	@JoinColumn(name = "OrderID", nullable = false)
	private Order order;
	
	private String paymentMethod;
	private String transactionNo;
	private java.math.BigDecimal amount;
	private java.time.LocalDateTime paymentTime = java.time.LocalDateTime.now();
	private String paymentStatus;
}