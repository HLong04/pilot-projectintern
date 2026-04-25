package com.be.pilot_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productphotos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long photoID;
	
	@ManyToOne
	@JoinColumn(name = "ProductID", nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private String photo;
	
	private String description;
	private Integer displayOrder;
	private Boolean isHidden = false;
}