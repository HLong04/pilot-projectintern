package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.SupplierDTO;
import com.be.pilot_project.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends BaseMapper<Supplier, SupplierDTO>{
}
