package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shippers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shipperID;
	
	@Column(nullable = false)
	private String shipperName;
	
	private String phone;
}
