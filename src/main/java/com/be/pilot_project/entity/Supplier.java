package com.be.pilot_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supplierID;
	
	@Column(nullable = false)
	private String supplierName;
	
	@Column(nullable = false)
	private String contactName;
	
	private String province;
	private String address;
	private String phone;
	
	@Column(nullable = false, unique = true, length = 50)
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Định dạng email không đúng")
	private String email;
}
