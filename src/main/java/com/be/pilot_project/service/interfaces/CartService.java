package com.be.pilot_project.service.interfaces;

import com.be.pilot_project.dto.CartDTO;

public interface CartService {
	CartDTO getByCustomerId(Integer customerId);
	CartDTO addItem(Integer customerId, Integer productId, Integer quantity);
	CartDTO updateItemQuantity(Integer customerId, Integer productId, Integer quantity);
	CartDTO removeItem(Integer customerId, Integer productId);
	CartDTO clearCart(Integer customerId);
}
