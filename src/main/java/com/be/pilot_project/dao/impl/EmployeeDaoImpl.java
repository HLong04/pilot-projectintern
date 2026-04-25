package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.EmployeeDao;
import com.be.pilot_project.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<EmployeeDTO> findAll() {
		String sql = baseSql() + " ORDER BY EmployeeID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapEmployee(rs));
	}
	
	@Override
	public List<EmployeeDTO> findWorking() {
		String sql = baseSql() + " WHERE IsWorking = 1 ORDER BY EmployeeID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapEmployee(rs));
	}
	
	@Override
	public Optional<EmployeeDTO> findById(Integer employeeId) {
		String sql = baseSql() + " WHERE EmployeeID = :employeeId";
		List<EmployeeDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("employeeId", employeeId),
			(rs, rowNum) -> mapEmployee(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	@Override
	public Optional<EmployeeDTO> findByEmail(String email) {
		String sql = baseSql() + " WHERE Email = :email";
		List<EmployeeDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("email", email),
			(rs, rowNum) -> mapEmployee(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private String baseSql() {
		return """
			SELECT EmployeeID, FullName, BirthDate, Address, Phone, Email, Photo, IsWorking, RoleNames
			FROM employees
			""";
	}
	
	private EmployeeDTO mapEmployee(ResultSet rs) throws SQLException {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(rs.getInt("EmployeeID"));
		dto.setFullName(rs.getString("FullName"));
		Date birthDate = rs.getDate("BirthDate");
		dto.setBirthDate(birthDate == null ? null : birthDate.toLocalDate());
		dto.setAddress(rs.getString("Address"));
		dto.setPhone(rs.getString("Phone"));
		dto.setEmail(rs.getString("Email"));
		dto.setPhoto(rs.getString("Photo"));
		dto.setIsWorking(rs.getObject("IsWorking") != null && rs.getBoolean("IsWorking"));
		dto.setRoleNames(rs.getString("RoleNames"));
		return dto;
	}
}
