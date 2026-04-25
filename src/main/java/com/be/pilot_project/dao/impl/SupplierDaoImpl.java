package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.SupplierDao;
import com.be.pilot_project.dto.SupplierDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SupplierDaoImpl implements SupplierDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<SupplierDTO> findAllWithProvince() {
		String sql = """
				SELECT s.SupplierID, s.SupplierName, s.ContactName, s.Province, s.Address, s.Phone, s.email
				FROM suppliers s
				LEFT JOIN provinces p ON p.ProvinceName = s.Province
				ORDER BY s.SupplierID DESC
				""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapSupplier(rs));
	}
	
	@Override
	public Optional<SupplierDTO> findByIdWithProvince(Integer supplierId) {
		String sql = """
				SELECT s.SupplierID, s.SupplierName, s.ContactName, s.Province, s.Address, s.Phone, s.email
				FROM suppliers s
				LEFT JOIN provinces p ON p.ProvinceName = s.Province
				WHERE s.SupplierID = :supplierId
				""";
		List<SupplierDTO> rows = jdbcTemplate.query(
				sql,
				new MapSqlParameterSource("supplierId", supplierId),
				(rs, rowNum) -> mapSupplier(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private SupplierDTO mapSupplier(ResultSet rs) throws SQLException {
		SupplierDTO dto = new SupplierDTO();
		dto.setSupplierId(rs.getInt("SupplierID"));
		dto.setSupplierName(rs.getString("SupplierName"));
		dto.setContactName(rs.getString("ContactName"));
		dto.setProvince(rs.getString("Province"));
		dto.setAddress(rs.getString("Address"));
		dto.setPhone(rs.getString("Phone"));
		dto.setEmail(rs.getString("email"));
		return dto;
	}
}
