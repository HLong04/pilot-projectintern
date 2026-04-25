package com.be.pilot_project.controller;

import com.be.pilot_project.dto.OrderDTO;
import com.be.pilot_project.dto.OrderStatusUpdateDTO;
import com.be.pilot_project.service.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
public class OrderController {
	private final OrderService orderService;
	
	@GetMapping("/api/user/orders")
	public List<OrderDTO> getUserOrders(@RequestParam Integer customerId) {
		return orderService.findByCustomerId(customerId);
	}
	
	@GetMapping("/api/user/orders/{id}")
	public OrderDTO getUserOrderDetail(@PathVariable Integer id) {
		return orderService.getById(id);
	}
	
	@PostMapping("/api/user/orders")
	public OrderDTO createOrder(@RequestBody OrderDTO dto) {
		return orderService.create(dto);
	}
	
	@GetMapping("/api/admin/orders")
	public List<OrderDTO> getAdminOrders() {
		return orderService.findAll();
	}
	
	@GetMapping("/api/admin/orders/{id}")
	public OrderDTO getAdminOrderDetail(@PathVariable Integer id) {
		return orderService.getById(id);
	}
	
	@PutMapping("/api/admin/orders/{id}/status")
	public OrderDTO updateOrderStatus(@PathVariable Integer id, @RequestBody OrderStatusUpdateDTO dto) {
		return orderService.updateStatus(id, dto.getStatus());
	}
	
	@DeleteMapping("/api/admin/orders/{id}")
	public void deleteOrder(@PathVariable Integer id) {
		orderService.delete(id);
	}
}
