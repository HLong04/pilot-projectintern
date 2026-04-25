package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.ProductStockDao;
import com.be.pilot_project.dto.ProductStockDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class ProductStockDaoImpl implements ProductStockDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ProductStockDTO> findAllWithRelations() {
		String sql = baseSql() + " ORDER BY ps.ProductID, ps.BranchID";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapProductStock(rs));
	}
	
	@Override
	public List<ProductStockDTO> findByProductIdWithRelations(Integer productId) {
		String sql = baseSql() + " WHERE ps.ProductID = :productId ORDER BY ps.BranchID";
		return jdbcTemplate.query(
				sql,
				new MapSqlParameterSource("productId", productId),
				(rs, rowNum) -> mapProductStock(rs)
		);
	}
	
	@Override
	public List<ProductStockDTO> findByBranchIdWithRelations(Integer branchId) {
		String sql = baseSql() + " WHERE ps.BranchID = :branchId ORDER BY ps.ProductID";
		return jdbcTemplate.query(
				sql,
				new MapSqlParameterSource("branchId", branchId),
				(rs, rowNum) -> mapProductStock(rs)
		);
	}
	
	private String baseSql() {
		return """
				SELECT ps.ProductID, ps.BranchID, ps.StockQuantity,
				       p.ProductName,
				       b.BranchName
				FROM productstocks ps
				INNER JOIN products p ON p.ProductID = ps.ProductID
				INNER JOIN branches b ON b.BranchID = ps.BranchID
				""";
	}
	
	private ProductStockDTO mapProductStock(ResultSet rs) throws SQLException {
		ProductStockDTO dto = new ProductStockDTO();
		dto.setProductId(rs.getInt("ProductID"));
		dto.setBranchId(rs.getInt("BranchID"));
		dto.setStockQuantity(rs.getInt("StockQuantity"));
		dto.setProductName(rs.getString("ProductName"));
		dto.setBranchName(rs.getString("BranchName"));
		return dto;
	}
}
