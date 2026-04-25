package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.CategoryDTO;
import com.be.pilot_project.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {
}
