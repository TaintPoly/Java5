package com.j5shop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer>{

}
