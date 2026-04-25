package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.VoucherDao;
import com.be.pilot_project.dto.VoucherDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class VoucherDaoImpl implements VoucherDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<VoucherDTO> findAll() {
		String sql = baseSql() + " ORDER BY VoucherID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapVoucher(rs));
	}
	
	@Override
	public List<VoucherDTO> findActive() {
		String sql = baseSql() + " WHERE IsActive = 1 ORDER BY VoucherID DESC";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapVoucher(rs));
	}
	
	@Override
	public Optional<VoucherDTO> findById(Integer voucherId) {
		String sql = baseSql() + " WHERE VoucherID = :voucherId";
		List<VoucherDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("voucherId", voucherId),
			(rs, rowNum) -> mapVoucher(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	@Override
	public Optional<VoucherDTO> findByCode(String voucherCode) {
		String sql = baseSql() + " WHERE VoucherCode = :voucherCode";
		List<VoucherDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("voucherCode", voucherCode),
			(rs, rowNum) -> mapVoucher(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private String baseSql() {
		return """
			SELECT VoucherID, VoucherCode, DiscountPercent, DiscountAmount, StartDate, EndDate,
			       UsageLimit, UsedCount, IsActive
			FROM vouchers
			""";
	}
	
	private VoucherDTO mapVoucher(ResultSet rs) throws SQLException {
		VoucherDTO dto = new VoucherDTO();
		dto.setVoucherId(rs.getInt("VoucherID"));
		dto.setVoucherCode(rs.getString("VoucherCode"));
		dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
		dto.setDiscountAmount(rs.getBigDecimal("DiscountAmount"));
		dto.setStartDate(toLocalDateTime(rs.getTimestamp("StartDate")));
		dto.setEndDate(toLocalDateTime(rs.getTimestamp("EndDate")));
		dto.setUsageLimit((Integer) rs.getObject("UsageLimit"));
		dto.setUsedCount((Integer) rs.getObject("UsedCount"));
		dto.setIsActive(rs.getObject("IsActive") != null && rs.getBoolean("IsActive"));
		return dto;
	}
	
	private java.time.LocalDateTime toLocalDateTime(Timestamp value) {
		return value == null ? null : value.toLocalDateTime();
	}
}
