package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer voucherID;
	
	@Column(unique = true, nullable = false)
	private String voucherCode;
	
	private java.math.BigDecimal discountPercent;
	private java.math.BigDecimal discountAmount;
	private java.time.LocalDateTime startDate;
	private java.time.LocalDateTime endDate;
	private Integer usageLimit;
	private Integer usedCount = 0;
	private Boolean isActive = true;
}
