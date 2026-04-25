package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.EmployeeDTO;
import com.be.pilot_project.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {
	@Override
	@Mapping(target = "password", ignore = true)
	Employee toEntity(EmployeeDTO dto);
}
