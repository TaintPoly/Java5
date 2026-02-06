package com.j5shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.j5shop.model.Account;
import com.j5shop.model.CartDetail;
import com.j5shop.model.Product;
import com.j5shop.repositoy.CartDetailRepository;
import com.j5shop.repositoy.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	HttpSession session;
	@Autowired
	CartDetailRepository cartDetailRepository;
	@Autowired
	ProductRepository productRepository;
	//danh sách
	
	//them vao gio hàng
	@GetMapping("gio-hang/them-vao-gio/{productId}")
	public String addToCart(@PathVariable("productId") Integer productId) {
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			return "redirect:/dang-nhap";
		}
		Product product = productRepository.findById(productId).orElse(null);
		CartDetail cartDetail = new CartDetail();
		cartDetail.setAccount(account);
		cartDetail.setProduct(product);
		cartDetail.setQuantity(1);
		cartDetailRepository.save(cartDetail);
		return "redirect:/gio-hang";
	}
}
