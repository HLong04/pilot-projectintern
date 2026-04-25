package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.BranchDTO;
import com.be.pilot_project.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface BranchMapper extends BaseMapper<Branch, BranchDTO> {
}
