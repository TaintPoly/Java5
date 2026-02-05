package com.j5shop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
