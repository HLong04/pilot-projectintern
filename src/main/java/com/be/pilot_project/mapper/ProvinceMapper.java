package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ProvinceDTO;
import com.be.pilot_project.entity.Province;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinceMapper extends BaseMapper<Province, ProvinceDTO> {
}
