package com.be.pilot_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeID;
	
	@Column(nullable = false)
	private String fullName;
	
	private java.time.LocalDate birthDate;
	private String address;
	private String phone;
	
	@Column(nullable = false, unique = true, length = 50)
	@NotBlank(message = "Email không được để trống")
	@Email(message = "Định dạng email không đúng")
	private String email;
	
	private String password;
	private String photo;
	private Boolean isWorking = true;
	
	private String roleNames;
}
