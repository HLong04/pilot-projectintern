package com.be.pilot_project.repository;

import com.be.pilot_project.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItem.CartItemId> {
	List<CartItem> findByCart_CartID(Integer cartID);
}
