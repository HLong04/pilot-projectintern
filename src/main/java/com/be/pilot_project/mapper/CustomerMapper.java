package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.CustomerDTO;
import com.be.pilot_project.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	@Mapping(source = "province.provinceName", target = "provinceName")
	CustomerDTO toDto(Customer customer);
	
	@Mapping(source = "provinceName", target = "province.provinceName")
	@Mapping(target = "password", ignore = true)
	Customer toEntity(CustomerDTO customerDTO);
}
