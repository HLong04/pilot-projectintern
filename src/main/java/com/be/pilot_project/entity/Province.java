package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provinces")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Province {
	@Id
	@Column(name = "ProvinceName")
	private String provinceName;
}