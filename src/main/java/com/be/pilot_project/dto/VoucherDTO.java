package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {
	private Integer voucherId;
	private String voucherCode;
	private java.math.BigDecimal discountPercent;
	private java.math.BigDecimal discountAmount;
	private java.time.LocalDateTime startDate;
	private java.time.LocalDateTime endDate;
	private Integer usageLimit;
	private Integer usedCount = 0;
	private Boolean isActive = true;
}
