package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.ShipperDao;
import com.be.pilot_project.dto.ShipperDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ShipperDaoImpl implements ShipperDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ShipperDTO> findAll() {
		String sql = "SELECT ShipperID, ShipperName, Phone FROM shippers ORDER BY ShipperID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) ->
				new ShipperDTO(
						rs.getInt("ShipperID"),
						rs.getString("ShipperName"),
						rs.getString("Phone")
				)
		);
	}
	
	@Override
	public Optional<ShipperDTO> findById(Integer shipperId) {
		String sql = "SELECT ShipperID, ShipperName, Phone FROM shippers WHERE ShipperID = :shipperId";
		List<ShipperDTO> rows = jdbcTemplate.query(
				sql,
				new MapSqlParameterSource("shipperId", shipperId),
				(rs, rowNum) -> new ShipperDTO(
						rs.getInt("ShipperID"),
						rs.getString("ShipperName"),
						rs.getString("Phone")
				)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
}
