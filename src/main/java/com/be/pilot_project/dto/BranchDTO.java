package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
	private Integer branchId;
	private String branchName;
	private String province;
	private String address;
	private String phone;
	private Boolean isActive = true;
}