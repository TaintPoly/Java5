package com.j5shop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
