package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderstatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {
	@Id
	private Integer status;
	
	@Column(nullable = false)
	private String description;
}
