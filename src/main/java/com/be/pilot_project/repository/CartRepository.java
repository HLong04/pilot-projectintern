package com.be.pilot_project.repository;

import com.be.pilot_project.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findByCustomer_CustomerID(Integer customerID);
}
