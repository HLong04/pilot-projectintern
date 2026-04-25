package com.be.pilot_project.service.impl;

import com.be.pilot_project.dao.OrderDao;
import com.be.pilot_project.dto.OrderDTO;
import com.be.pilot_project.dto.OrderDetailDTO;
import com.be.pilot_project.entity.*;
import com.be.pilot_project.repository.*;
import com.be.pilot_project.service.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderStatusRepository orderStatusRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final CustomerRepository customerRepository;
	private final BranchRepository branchRepository;
	private final ProductRepository productRepository;
	private final OrderDao orderDao;
	
	@Override
	public List<OrderDTO> findAll() {
		return orderDao.findAllWithRelations();
	}
	
	@Override
	public List<OrderDTO> findByCustomerId(Integer customerId) {
		return orderDao.findByCustomerIdWithRelations(customerId);
	}
	
	@Override
	public OrderDTO getById(Integer id) {
		return orderDao.findByIdWithRelations(id)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy order id: " + id));
	}
	
	@Override
	public OrderDTO create(OrderDTO dto) {
		Order order = new Order();
		order.setOrderTime(dto.getOrderTime() == null ? LocalDateTime.now() : dto.getOrderTime());
		order.setDeliveryProvince(dto.getDeliveryProvince());
		order.setDeliveryAddress(dto.getDeliveryAddress());
		order.setDiscountAmount(dto.getDiscountAmount() == null ? BigDecimal.ZERO : dto.getDiscountAmount());
		
		attachRelations(order, dto.getCustomerId(), dto.getBranchId(), dto.getStatusId());
		Order savedOrder = orderRepository.save(order);
		
		List<OrderDetail> details = buildOrderDetails(savedOrder, dto.getOrderDetails());
		savedOrder.setOrderDetails(details);
		orderRepository.save(savedOrder);
		
		return getById(savedOrder.getOrderID());
	}
	
	@Override
	public OrderDTO updateStatus(Integer orderId, Integer status) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy order id: " + orderId));
		
		OrderStatus orderStatus = orderStatusRepository.findById(status)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy status: " + status));
		
		order.setOrderStatus(orderStatus);
		if (status != null && status == 4) {
			order.setFinishedTime(LocalDateTime.now());
		}
		
		orderRepository.save(order);
		return getById(order.getOrderID());
	}
	
	@Override
	public void delete(Integer orderId) {
		orderRepository.deleteById(orderId);
	}
	
	private void attachRelations(Order order, Integer customerId, Integer branchId, Integer statusId) {
		if (customerId != null) {
			Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy customer id: " + customerId));
			order.setCustomer(customer);
		}
		
		if (branchId != null) {
			Branch branch = branchRepository.findById(branchId)
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy branch id: " + branchId));
			order.setBranch(branch);
		}
		
		Integer finalStatus = statusId == null ? 1 : statusId;
		OrderStatus orderStatus = orderStatusRepository.findById(finalStatus)
			.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy status: " + finalStatus));
		order.setOrderStatus(orderStatus);
	}
	
	private List<OrderDetail> buildOrderDetails(Order order, List<OrderDetailDTO> detailDTOS) {
		if (detailDTOS == null || detailDTOS.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<OrderDetail> details = new ArrayList<>();
		for (OrderDetailDTO detailDTO : detailDTOS) {
			Product product = productRepository.findById(detailDTO.getProductId())
				.orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product id: " + detailDTO.getProductId()));
			
			OrderDetail detail = new OrderDetail();
			detail.setId(new OrderDetail.OrderDetailId(order.getOrderID(), product.getProductID()));
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setQuantity(detailDTO.getQuantity());
			detail.setSalePrice(detailDTO.getSalePrice() == null ? product.getPrice() : detailDTO.getSalePrice());
			details.add(orderDetailRepository.save(detail));
		}
		return details;
	}
	
}
