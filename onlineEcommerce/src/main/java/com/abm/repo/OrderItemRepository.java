package com.abm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.entity.OrderItem;



public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
