package com.be.pilot_project.controller;

import com.be.pilot_project.dto.CartDTO;
import com.be.pilot_project.dto.CartItemRequestDTO;
import com.be.pilot_project.service.interfaces.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/api/user/cart")
public class CartController {
	private final CartService cartService;
	
	@GetMapping
	public CartDTO getCart(@RequestParam Integer customerId) {
		return cartService.getByCustomerId(customerId);
	}
	
	@PostMapping("/items")
	public CartDTO addItem(@RequestParam Integer customerId, @RequestBody CartItemRequestDTO request) {
		return cartService.addItem(customerId, request.getProductId(), request.getQuantity());
	}
	
	@PutMapping("/items/{productId}")
	public CartDTO updateItem(@RequestParam Integer customerId, @PathVariable Integer productId, @RequestParam Integer quantity) {
		return cartService.updateItemQuantity(customerId, productId, quantity);
	}
	
	@DeleteMapping("/items/{productId}")
	public CartDTO removeItem(@RequestParam Integer customerId, @PathVariable Integer productId) {
		return cartService.removeItem(customerId, productId);
	}
	
	@DeleteMapping
	public CartDTO clearCart(@RequestParam Integer customerId) {
		return cartService.clearCart(customerId);
	}
}
