package com.be.pilot_project.repository;

import com.be.pilot_project.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.OrderDetailId> {
	List<OrderDetail> findByOrder_OrderID(Integer orderID);
}
