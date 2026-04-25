package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.CategoryDao;
import com.be.pilot_project.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryDaoImpl implements CategoryDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<CategoryDTO> findAll() {
		String sql = "SELECT CategoryID, CategoryName, Description FROM categories ORDER BY CategoryID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) ->
			new CategoryDTO(
				rs.getInt("CategoryID"),
				rs.getString("CategoryName"),
				rs.getString("Description")
			)
		);
	}
	
	@Override
	public Optional<CategoryDTO> findById(Integer categoryId) {
		String sql = "SELECT CategoryID, CategoryName, Description FROM categories WHERE CategoryID = :categoryId";
		List<CategoryDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("categoryId", categoryId),
			(rs, rowNum) -> new CategoryDTO(
				rs.getInt("CategoryID"),
				rs.getString("CategoryName"),
				rs.getString("Description")
			)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
}
