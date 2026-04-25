package com.be.pilot_project.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
	private Integer supplierId;
	private String supplierName;
	private String contactName;
	private String province;
	private String address;
	private String phone;
	private String email;
}
