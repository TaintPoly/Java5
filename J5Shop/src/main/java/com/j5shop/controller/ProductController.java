package com.j5shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.j5shop.model.Product;
import com.j5shop.repositoy.ProductRepository;

@Controller
public class ProductController {
	//trang sp
	
	@Autowired 
	ProductRepository productRepository;
	
	//chi tiết
	@GetMapping("san-pham/{slug}")
	public String detailProduct(Model model, @PathVariable("slug") String slug) {
		Product product = productRepository.findBySlugAndActive(slug, true);
		model.addAttribute("product", product);
		return "client/detail-product";
	}
}
