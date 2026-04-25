package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.BranchDao;
import com.be.pilot_project.dto.BranchDTO;
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
public class BranchDaoImpl implements BranchDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<BranchDTO> findAllWithProvince() {
		String sql = """
			SELECT b.BranchID, b.BranchName, b.Province, b.Address, b.phone, b.IsActive
			FROM branches b
			LEFT JOIN provinces p ON p.ProvinceName = b.Province
			ORDER BY b.BranchID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapBranch(rs));
	}
	
	@Override
	public List<BranchDTO> findActiveWithProvince() {
		String sql = """
			SELECT b.BranchID, b.BranchName, b.Province, b.Address, b.phone, b.IsActive
			FROM branches b
			LEFT JOIN provinces p ON p.ProvinceName = b.Province
			WHERE b.IsActive = 1
			ORDER BY b.BranchID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapBranch(rs));
	}
	
	@Override
	public Optional<BranchDTO> findByIdWithProvince(Integer branchId) {
		String sql = """
			SELECT b.BranchID, b.BranchName, b.Province, b.Address, b.phone, b.IsActive
			FROM branches b
			LEFT JOIN provinces p ON p.ProvinceName = b.Province
			WHERE b.BranchID = :branchId
			""";
		List<BranchDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("branchId", branchId),
			(rs, rowNum) -> mapBranch(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private BranchDTO mapBranch(ResultSet rs) throws SQLException {
		BranchDTO dto = new BranchDTO();
		dto.setBranchId(rs.getInt("BranchID"));
		dto.setBranchName(rs.getString("BranchName"));
		dto.setProvince(rs.getString("Province"));
		dto.setAddress(rs.getString("Address"));
		dto.setPhone(rs.getString("phone"));
		dto.setIsActive(rs.getObject("IsActive") != null && rs.getBoolean("IsActive"));
		return dto;
	}
}
