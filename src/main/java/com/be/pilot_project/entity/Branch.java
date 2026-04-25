package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "branches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer branchID;
	
	@Column(nullable = false)
	private String branchName;
	
	private String province;
	
	@Column(nullable = false)
	private String address;
	
	private String phone;
	
	@Column(nullable = false)
	private Boolean isActive = true;
}
