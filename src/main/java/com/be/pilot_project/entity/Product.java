package com.be.pilot_project.entity;

import com.be.pilot_project.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productID;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(columnDefinition = "TEXT")
	private String productDescription;
	
	private String unit;
	private BigDecimal price;
	private String photo;
	private Boolean isSelling;
	
	@ManyToOne
	@JoinColumn(name = "CategoryID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "SupplierID")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductPhoto> photos;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductAttribute> attributes;
}