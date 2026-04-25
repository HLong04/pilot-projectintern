package com.be.pilot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhotoDTO {
	private Long photoId;
	private Integer productId;
	private String photo;
	private String description;
	private Integer displayOrder;
	private Boolean isHidden = false;
}