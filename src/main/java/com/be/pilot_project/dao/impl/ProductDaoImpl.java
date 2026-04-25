package com.be.pilot_project.dao.impl;

import com.be.pilot_project.dao.ProductDao;
import com.be.pilot_project.dto.*;
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
public class ProductDaoImpl implements ProductDao {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ProductDTO> findAllWithRelations() {
		String sql = """
			SELECT p.ProductID, p.ProductName, p.Unit, p.price, p.Photo, p.ProductDescription, p.IsSelling,
			       c.CategoryID, c.CategoryName, c.Description,
			       s.SupplierID, s.SupplierName, s.ContactName, s.Province, s.Address, s.Phone, s.email
			FROM products p
			LEFT JOIN categories c ON c.CategoryID = p.CategoryID
			LEFT JOIN suppliers s ON s.SupplierID = p.SupplierID
			ORDER BY p.ProductID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapProduct(rs));
	}
	
	@Override
	public List<ProductDTO> findSellingWithRelations() {
		String sql = """
			SELECT p.ProductID, p.ProductName, p.Unit, p.price, p.Photo, p.ProductDescription, p.IsSelling,
			       c.CategoryID, c.CategoryName, c.Description,
			       s.SupplierID, s.SupplierName, s.ContactName, s.Province, s.Address, s.Phone, s.email
			FROM products p
			LEFT JOIN categories c ON c.CategoryID = p.CategoryID
			LEFT JOIN suppliers s ON s.SupplierID = p.SupplierID
			WHERE p.IsSelling = 1
			ORDER BY p.ProductID DESC
			""";
		return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> mapProduct(rs));
	}
	
	@Override
	public Optional<ProductDTO> findByIdWithRelations(Integer productId) {
		String productSql = """
			SELECT p.ProductID, p.ProductName, p.Unit, p.price, p.Photo, p.ProductDescription, p.IsSelling,
			       c.CategoryID, c.CategoryName, c.Description,
			       s.SupplierID, s.SupplierName, s.ContactName, s.Province, s.Address, s.Phone, s.email
			FROM products p
			LEFT JOIN categories c ON c.CategoryID = p.CategoryID
			LEFT JOIN suppliers s ON s.SupplierID = p.SupplierID
			WHERE p.ProductID = :productId
			""";
		
		List<ProductDTO> products = jdbcTemplate.query(
			productSql,
			new MapSqlParameterSource("productId", productId),
			(rs, rowNum) -> mapProduct(rs)
		);
		
		if (products.isEmpty()) {
			return Optional.empty();
		}
		
		ProductDTO product = products.get(0);
		
		String photoSql = """
			SELECT PhotoID, ProductID, Photo, Description, DisplayOrder, IsHidden
			FROM productphotos
			WHERE ProductID = :productId
			ORDER BY DisplayOrder, PhotoID
			""";
		
		List<ProductPhotoDTO> photos = jdbcTemplate.query(
			photoSql,
			new MapSqlParameterSource("productId", productId),
			(rs, rowNum) -> new ProductPhotoDTO(
				rs.getLong("PhotoID"),
				rs.getInt("ProductID"),
				rs.getString("Photo"),
				rs.getString("Description"),
				rs.getInt("DisplayOrder"),
				rs.getBoolean("IsHidden")
			)
		);
		
		String attributeSql = """
			SELECT AttributeID, ProductID, AttributeName, AttributeValue, DisplayOrder
			FROM productattributes
			WHERE ProductID = :productId
			ORDER BY DisplayOrder, AttributeID
			""";
		
		List<ProductAttributeDTO> attributes = jdbcTemplate.query(
			attributeSql,
			new MapSqlParameterSource("productId", productId),
			(rs, rowNum) -> new ProductAttributeDTO(
				rs.getLong("AttributeID"),
				rs.getInt("ProductID"),
				rs.getString("AttributeName"),
				rs.getString("AttributeValue"),
				rs.getInt("DisplayOrder")
			)
		);
		
		product.setPhotos(photos);
		product.setAttributes(attributes);
		return Optional.of(product);
	}
	
	private ProductDTO mapProduct(ResultSet rs) throws SQLException {
		ProductDTO dto = new ProductDTO();
		dto.setProductId(rs.getInt("ProductID"));
		dto.setProductName(rs.getString("ProductName"));
		dto.setUnit(rs.getString("Unit"));
		dto.setPrice(rs.getBigDecimal("price"));
		dto.setPhoto(rs.getString("Photo"));
		dto.setProductDescription(rs.getString("ProductDescription"));
		dto.setIsSelling(rs.getObject("IsSelling") != null && rs.getBoolean("IsSelling"));
		dto.setCategoryId((Integer) rs.getObject("CategoryID"));
		dto.setSupplierId((Integer) rs.getObject("SupplierID"));
		
		Integer categoryId = (Integer) rs.getObject("CategoryID");
		if (categoryId != null) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(categoryId);
			categoryDTO.setCategoryName(rs.getString("CategoryName"));
			categoryDTO.setDescription(rs.getString("Description"));
			dto.setCategory(categoryDTO);
		}
		
		Integer supplierId = (Integer) rs.getObject("SupplierID");
		if (supplierId != null) {
			SupplierDTO supplierDTO = new SupplierDTO();
			supplierDTO.setSupplierId(supplierId);
			supplierDTO.setSupplierName(rs.getString("SupplierName"));
			supplierDTO.setContactName(rs.getString("ContactName"));
			supplierDTO.setProvince(rs.getString("Province"));
			supplierDTO.setAddress(rs.getString("Address"));
			supplierDTO.setPhone(rs.getString("Phone"));
			supplierDTO.setEmail(rs.getString("email"));
			dto.setSupplier(supplierDTO);
		}
		return dto;
	}
}
