package com.be.pilot_project.dao;

import com.be.pilot_project.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
	List<CategoryDTO> findAll();
	Optional<CategoryDTO> findById(Integer categoryId);
}
