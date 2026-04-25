package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderID;
	
	private LocalDateTime orderTime;
	private String deliveryProvince;
	private String deliveryAddress;
	private LocalDateTime acceptTime;
	private LocalDateTime shippedTime;
	private LocalDateTime finishedTime;
	
	@Column(nullable = false)
	private BigDecimal discountAmount = BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "Status")
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "BranchID")
	private Branch branch;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
}