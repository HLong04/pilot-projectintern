package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ProductStockDTO;
import com.be.pilot_project.entity.ProductStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductStockMapper {
	@Mapping(source = "product.productID", target = "productId")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "branch.branchID", target = "branchId")
	@Mapping(source = "branch.branchName", target = "branchName")
	ProductStockDTO toDto(ProductStock entity);
	
	@Mapping(source = "productId", target = "product.productID")
	@Mapping(source = "branchId", target = "branch.branchID")
	@Mapping(source = "productId", target = "id.productID")
	@Mapping(source = "branchId", target = "id.branchID")
	ProductStock toEntity(ProductStockDTO dto);
}