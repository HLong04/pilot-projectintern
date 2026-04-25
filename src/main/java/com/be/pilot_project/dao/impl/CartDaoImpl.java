package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.CartDao;
import com.be.pilot_project.dto.CartDTO;
import com.be.pilot_project.dto.CartItemDTO;
import com.be.pilot_project.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CartDaoImpl implements CartDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<CartDTO> findByCustomerId(Integer customerId) {
		String sql = """
			SELECT c.CartID, c.CustomerID, c.CreatedTime, c.UpdatedTime,
			       cu.CustomerName, cu.ContactName, cu.Address AS CustomerAddress, cu.Phone AS CustomerPhone, cu.Email,
			       ci.ProductID, ci.Quantity,
			       p.ProductName, p.Photo, p.price
			FROM carts c
			INNER JOIN customers cu ON cu.CustomerID = c.CustomerID
			LEFT JOIN cartitems ci ON ci.CartID = c.CartID
			LEFT JOIN products p ON p.ProductID = ci.ProductID
			WHERE c.CustomerID = :customerId
			ORDER BY ci.ProductID
			""";
		
		List<CartDTO> rows = jdbcTemplate.query(
			sql,
			new MapSqlParameterSource("customerId", customerId),
			rs -> {
				CartDTO dto = null;
				BigDecimal total = BigDecimal.ZERO;
				List<CartItemDTO> items = new ArrayList<>();
				
				while (rs.next()) {
					if (dto == null) {
						dto = new CartDTO();
						dto.setCartId(rs.getInt("CartID"));
						dto.setCustomerId(rs.getInt("CustomerID"));
						dto.setCreatedTime(rs.getTimestamp("CreatedTime").toLocalDateTime());
						dto.setUpdatedTime(rs.getTimestamp("UpdatedTime").toLocalDateTime());
						dto.setItems(items);
						
						CustomerDTO customerDTO = new CustomerDTO();
						customerDTO.setCustomerId(rs.getInt("CustomerID"));
						customerDTO.setCustomerName(rs.getString("CustomerName"));
						customerDTO.setContactName(rs.getString("ContactName"));
						customerDTO.setAddress(rs.getString("CustomerAddress"));
						customerDTO.setPhone(rs.getString("CustomerPhone"));
						customerDTO.setEmail(rs.getString("Email"));
						dto.setCustomer(customerDTO);
					}
					
					Integer productId = (Integer) rs.getObject("ProductID");
					if (productId != null) {
						CartItemDTO item = new CartItemDTO();
						item.setProductId(productId);
						item.setQuantity(rs.getInt("Quantity"));
						item.setProductName(rs.getString("ProductName"));
						item.setPhoto(rs.getString("Photo"));
						BigDecimal price = rs.getBigDecimal("price");
						item.setPrice(price);
						BigDecimal subTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));
						item.setSubTotal(subTotal);
						items.add(item);
						total = total.add(subTotal);
					}
				}
				
				if (dto != null) {
					dto.setTotalAmount(total);
				}
				
				return dto == null ? List.<CartDTO>of() : List.of(dto);
			}
		);
		
		return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
	}
}
