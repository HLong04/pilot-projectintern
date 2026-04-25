package com.be.pilot_project.service.impl;

import com.be.pilot_project.dao.CartDao;
import com.be.pilot_project.dto.CartDTO;
import com.be.pilot_project.entity.Cart;
import com.be.pilot_project.entity.CartItem;
import com.be.pilot_project.entity.Customer;
import com.be.pilot_project.entity.Product;
import com.be.pilot_project.repository.CartItemRepository;
import com.be.pilot_project.repository.CartRepository;
import com.be.pilot_project.repository.CustomerRepository;
import com.be.pilot_project.repository.ProductRepository;
import com.be.pilot_project.service.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final CartDao cartDao;
	
	@Override
	public CartDTO getByCustomerId(Integer customerId) {
		Cart cart = getOrCreateCart(customerId);
		return cartDao.findByCustomerId(cart.getCustomer().getCustomerID())
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cart của customer id: " + customerId));
	}
	
	@Override
	public CartDTO addItem(Integer customerId, Integer productId, Integer quantity) {
		int safeQuantity = sanitizeQuantity(quantity);
		Cart cart = getOrCreateCart(customerId);
		Product product = getProduct(productId);
		
		CartItem.CartItemId itemId = new CartItem.CartItemId(cart.getCartID(), productId);
		Optional<CartItem> existingItem = cartItemRepository.findById(itemId);
		
		if (existingItem.isPresent()) {
			CartItem item = existingItem.get();
			item.setQuantity(item.getQuantity() + safeQuantity);
			cartItemRepository.save(item);
		} else {
			CartItem newItem = new CartItem();
			newItem.setId(itemId);
			newItem.setCart(cart);
			newItem.setProduct(product);
			newItem.setQuantity(safeQuantity);
			cartItemRepository.save(newItem);
		}
		
		cart.setUpdatedTime(LocalDateTime.now());
		cartRepository.save(cart);
		return cartDao.findByCustomerId(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cart của customer id: " + customerId));
	}
	
	@Override
	public CartDTO updateItemQuantity(Integer customerId, Integer productId, Integer quantity) {
		Cart cart = getOrCreateCart(customerId);
		CartItem.CartItemId itemId = new CartItem.CartItemId(cart.getCartID(), productId);
		CartItem item = cartItemRepository.findById(itemId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy item trong cart"));
		
		if (quantity == null || quantity <= 0) {
			cartItemRepository.delete(item);
		} else {
			item.setQuantity(quantity);
			cartItemRepository.save(item);
		}
		
		cart.setUpdatedTime(LocalDateTime.now());
		cartRepository.save(cart);
		return cartDao.findByCustomerId(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cart của customer id: " + customerId));
	}
	
	@Override
	public CartDTO removeItem(Integer customerId, Integer productId) {
		Cart cart = getOrCreateCart(customerId);
		CartItem.CartItemId itemId = new CartItem.CartItemId(cart.getCartID(), productId);
		cartItemRepository.deleteById(itemId);
		cart.setUpdatedTime(LocalDateTime.now());
		cartRepository.save(cart);
		return cartDao.findByCustomerId(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cart của customer id: " + customerId));
	}
	
	@Override
	public CartDTO clearCart(Integer customerId) {
		Cart cart = getOrCreateCart(customerId);
		cartItemRepository.findByCart_CartID(cart.getCartID()).forEach(cartItemRepository::delete);
		cart.setUpdatedTime(LocalDateTime.now());
		cartRepository.save(cart);
		return cartDao.findByCustomerId(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy cart của customer id: " + customerId));
	}
	
	private Cart getOrCreateCart(Integer customerId) {
		Cart cart = cartRepository.findByCustomer_CustomerID(customerId);
		if (cart != null) {
			return cart;
		}
		
		Customer customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy customer id: " + customerId));
		
		Cart newCart = new Cart();
		newCart.setCustomer(customer);
		newCart.setCreatedTime(LocalDateTime.now());
		newCart.setUpdatedTime(LocalDateTime.now());
		newCart.setItems(new ArrayList<>());
		return cartRepository.save(newCart);
	}
	
	private Product getProduct(Integer productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product id: " + productId));
	}
	
	private int sanitizeQuantity(Integer quantity) {
		return quantity == null || quantity <= 0 ? 1 : quantity;
	}
}
