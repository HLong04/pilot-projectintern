package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.PaymentDao;
import com.be.pilot_project.dto.PaymentDTO;
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
public class PaymentDaoImpl implements PaymentDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<PaymentDTO> findAllWithOrderInfo() {
		String sql = """
			SELECT p.PaymentID, p.PaymentMethod, p.TransactionNo, p.amount, p.PaymentTime, p.PaymentStatus
			FROM payments p
			INNER JOIN orders o ON o.OrderID = p.OrderID
			LEFT JOIN customers c ON c.CustomerID = o.CustomerID
			ORDER BY p.PaymentID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapPayment(rs));
	}
	
	@Override
	public List<PaymentDTO> findByOrderIdWithOrderInfo(Integer orderId) {
		String sql = """
			SELECT p.PaymentID, p.PaymentMethod, p.TransactionNo, p.amount, p.PaymentTime, p.PaymentStatus
			FROM payments p
			INNER JOIN orders o ON o.OrderID = p.OrderID
			LEFT JOIN customers c ON c.CustomerID = o.CustomerID
			WHERE p.OrderID = :orderId
			ORDER BY p.PaymentID DESC
			""";
		return jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("orderId", orderId),
			(rs, rowNum) -> mapPayment(rs)
		);
	}
	
	@Override
	public Optional<PaymentDTO> findByIdWithOrderInfo(Integer paymentId) {
		String sql = """
			SELECT p.PaymentID, p.PaymentMethod, p.TransactionNo, p.amount, p.PaymentTime, p.PaymentStatus
			FROM payments p
			INNER JOIN orders o ON o.OrderID = p.OrderID
			LEFT JOIN customers c ON c.CustomerID = o.CustomerID
			WHERE p.PaymentID = :paymentId
			""";
		List<PaymentDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("paymentId", paymentId),
			(rs, rowNum) -> mapPayment(rs)
		);
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
	
	private PaymentDTO mapPayment(ResultSet rs) throws SQLException {
		PaymentDTO dto = new PaymentDTO();
		dto.setPaymentId(rs.getInt("PaymentID"));
		dto.setPaymentMethod(rs.getString("PaymentMethod"));
		dto.setTransactionNo(rs.getString("TransactionNo"));
		dto.setAmount(rs.getBigDecimal("amount"));
		dto.setPaymentTime(rs.getTimestamp("PaymentTime").toLocalDateTime());
		dto.setPaymentStatus(rs.getString("PaymentStatus"));
		return dto;
	}
}
