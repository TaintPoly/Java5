package com.j5shop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
