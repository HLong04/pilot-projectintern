package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.ProvinceDao;
import com.be.pilot_project.dto.ProvinceDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProvinceDaoImpl implements ProvinceDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ProvinceDTO> findAll() {
		String sql = "SELECT ProvinceName FROM provinces ORDER BY ProvinceName";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) ->
			new ProvinceDTO(rs.getString("ProvinceName"))
		);
	}
	
	@Override
	public Optional<ProvinceDTO> findByName(String provinceName) {
		String sql = "SELECT ProvinceName FROM provinces WHERE ProvinceName = :provinceName";
		List<ProvinceDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("provinceName", provinceName),
			(rs, rowNum) -> new ProvinceDTO(rs.getString("ProvinceName"))
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
}
