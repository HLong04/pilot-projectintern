package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.ShipperDTO;
import com.be.pilot_project.entity.Shipper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipperMapper extends BaseMapper<Shipper, ShipperDTO> {
}
