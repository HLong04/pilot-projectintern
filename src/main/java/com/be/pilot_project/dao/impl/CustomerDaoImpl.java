package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.CustomerDao;
import com.be.pilot_project.dto.CustomerDTO;
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
public class CustomerDaoImpl implements CustomerDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<CustomerDTO> findAllWithProvince() {
		String sql = """
			SELECT c.CustomerID, c.CustomerName, c.ContactName, c.Address, c.Phone, c.Email, c.IsLocked,
			       p.ProvinceName
			FROM customers c
			LEFT JOIN provinces p ON p.ProvinceName = c.Province
			ORDER BY c.CustomerID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapCustomer(rs));
	}
	
	@Override
	public Optional<CustomerDTO> findByIdWithProvince(Integer customerId) {
		String sql = """
			SELECT c.CustomerID, c.CustomerName, c.ContactName, c.Address, c.Phone, c.Email, c.IsLocked,
			       p.ProvinceName
			FROM customers c
			LEFT JOIN provinces p ON p.ProvinceName = c.Province
			WHERE c.CustomerID = :customerId
			""";
		List<CustomerDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("customerId", customerId),
			(rs, rowNum) -> mapCustomer(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private CustomerDTO mapCustomer(ResultSet rs) throws SQLException {
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(rs.getInt("CustomerID"));
		dto.setCustomerName(rs.getString("CustomerName"));
		dto.setContactName(rs.getString("ContactName"));
		dto.setAddress(rs.getString("Address"));
		dto.setPhone(rs.getString("Phone"));
		dto.setEmail(rs.getString("Email"));
		dto.setIsLocked(rs.getObject("IsLocked") != null && rs.getBoolean("IsLocked"));
		dto.setProvinceName(rs.getString("ProvinceName"));
		return dto;
	}
}
