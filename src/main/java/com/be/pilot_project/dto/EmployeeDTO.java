package com.be.pilot_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	private Integer employeeId;
	private String fullName;
	private java.time.LocalDate birthDate;
	private String address;
	private String phone;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String photo;
	private Boolean isWorking = true;
	private String roleNames;
}
