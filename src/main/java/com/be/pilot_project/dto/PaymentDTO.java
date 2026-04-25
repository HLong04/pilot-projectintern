package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	private Integer paymentId;
	private String paymentMethod;
	private String transactionNo;
	private java.math.BigDecimal amount;
	private java.time.LocalDateTime paymentTime;
	private String paymentStatus;
}