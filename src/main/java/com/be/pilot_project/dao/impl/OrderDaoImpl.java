package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.OrderDao;
import com.be.pilot_project.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderDaoImpl implements OrderDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<OrderDTO> findAllWithRelations() {
		return queryOrders(null);
	}
	
	@Override
	public List<OrderDTO> findByCustomerIdWithRelations(Integer customerId) {
		return queryOrders(customerId);
	}
	
	@Override
	public Optional<OrderDTO> findByIdWithRelations(Integer orderId) {
		String sql = baseSql() + " AND o.OrderID = :orderId ORDER BY o.OrderID DESC, od.ProductID";
		List<OrderDTO> orders = jdbcTemplate.query(sql, new MapSqlParameterSource("orderId", orderId), (ResultSetExtractor<List<OrderDTO>>) rs -> mapOrders(rs));
		return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
	}
	
	private List<OrderDTO> queryOrders(Integer customerId) {
		String sql = baseSql();
		MapSqlParameterSource params = new MapSqlParameterSource();
		if (customerId != null) {
			sql += " AND o.CustomerID = :customerId";
			params.addValue("customerId", customerId);
		}
		sql += " ORDER BY o.OrderID DESC, od.ProductID";
		return jdbcTemplate.query(sql, params, (ResultSetExtractor<List<OrderDTO>>) rs -> mapOrders(rs));
	}
	
	private String baseSql() {
		return """
			SELECT o.OrderID, o.OrderTime, o.DeliveryProvince, o.DeliveryAddress, o.AcceptTime, o.ShippedTime, o.FinishedTime,
			       o.DiscountAmount, o.CustomerID, o.BranchID, o.Status, o.VoucherID,
			       c.CustomerName, c.ContactName, c.Address AS CustomerAddress, c.Phone AS CustomerPhone, c.Email,
			       b.BranchName,
			       os.description AS StatusDescription,
			       od.ProductID, od.Quantity, od.SalePrice,
			       p.ProductName, p.Photo
			FROM orders o
			LEFT JOIN customers c ON c.CustomerID = o.CustomerID
			LEFT JOIN branches b ON b.BranchID = o.BranchID
			LEFT JOIN orderstatus os ON os.Status = o.Status
			LEFT JOIN orderdetails od ON od.OrderID = o.OrderID
			LEFT JOIN products p ON p.ProductID = od.ProductID
			WHERE 1 = 1
			""";
	}
	
	private List<OrderDTO> mapOrders(ResultSet rs) throws SQLException {
		Map<Integer, OrderDTO> orderMap = new LinkedHashMap<>();
		
		while (rs.next()) {
			Integer orderId = rs.getInt("OrderID");
			OrderDTO dto = orderMap.get(orderId);
			
			if (dto == null) {
				dto = new OrderDTO();
				dto.setOrderId(orderId);
				dto.setOrderTime(toLocalDateTime(rs.getTimestamp("OrderTime")));
				dto.setDeliveryProvince(rs.getString("DeliveryProvince"));
				dto.setDeliveryAddress(rs.getString("DeliveryAddress"));
				dto.setAcceptTime(toLocalDateTime(rs.getTimestamp("AcceptTime")));
				dto.setShippedTime(toLocalDateTime(rs.getTimestamp("ShippedTime")));
				dto.setFinishedTime(toLocalDateTime(rs.getTimestamp("FinishedTime")));
				dto.setDiscountAmount(rs.getBigDecimal("DiscountAmount"));
				dto.setCustomerId((Integer) rs.getObject("CustomerID"));
				dto.setBranchId((Integer) rs.getObject("BranchID"));
				dto.setStatusId((Integer) rs.getObject("Status"));
				dto.setVoucherId((Integer) rs.getObject("VoucherID"));
				dto.setOrderDetails(new ArrayList<>());
				
				Integer customerId = (Integer) rs.getObject("CustomerID");
				if (customerId != null) {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setCustomerId(customerId);
					customerDTO.setCustomerName(rs.getString("CustomerName"));
					customerDTO.setContactName(rs.getString("ContactName"));
					customerDTO.setAddress(rs.getString("CustomerAddress"));
					customerDTO.setPhone(rs.getString("CustomerPhone"));
					customerDTO.setEmail(rs.getString("Email"));
					dto.setCustomer(customerDTO);
				}
				
				Integer branchId = (Integer) rs.getObject("BranchID");
				if (branchId != null) {
					BranchDTO branchDTO = new BranchDTO();
					branchDTO.setBranchId(branchId);
					branchDTO.setBranchName(rs.getString("BranchName"));
					dto.setBranch(branchDTO);
				}
				
				Integer status = (Integer) rs.getObject("Status");
				if (status != null) {
					OrderStatusDTO statusDTO = new OrderStatusDTO();
					statusDTO.setStatus(status);
					statusDTO.setDescription(rs.getString("StatusDescription"));
					dto.setOrderStatus(statusDTO);
				}
				
				orderMap.put(orderId, dto);
			}
			
			Integer productId = (Integer) rs.getObject("ProductID");
			if (productId != null) {
				OrderDetailDTO detailDTO = new OrderDetailDTO();
				detailDTO.setProductId(productId);
				detailDTO.setProductName(rs.getString("ProductName"));
				detailDTO.setPhoto(rs.getString("Photo"));
				detailDTO.setQuantity(rs.getInt("Quantity"));
				BigDecimal salePrice = rs.getBigDecimal("SalePrice");
				detailDTO.setSalePrice(salePrice);
				detailDTO.setSubTotal(salePrice.multiply(BigDecimal.valueOf(detailDTO.getQuantity())));
				dto.getOrderDetails().add(detailDTO);
			}
		}
		
		return new ArrayList<>(orderMap.values());
	}
	
	private LocalDateTime toLocalDateTime(Timestamp value) {
		return value == null ? null : value.toLocalDateTime();
	}
}
