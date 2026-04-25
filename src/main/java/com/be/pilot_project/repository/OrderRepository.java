package com.be.pilot_project.repository;

import com.be.pilot_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByCustomer_CustomerID(Integer customerID);
	List<Order> findByOrderStatus_Status(Integer status);
}
