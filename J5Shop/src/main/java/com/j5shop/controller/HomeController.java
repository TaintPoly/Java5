package com.j5shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j5shop.model.Product;
import com.j5shop.repositoy.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	//HT
	//Mssv
	@Autowired
	HttpSession session;
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/")
	public String home(Model model, @RequestParam("page-size")Optional<Integer> pageSize) {
		int pageN = 0;
		int pageS = pageSize.orElse(5);
		
		Pageable pageable = PageRequest.of(pageN, pageS, Direction.DESC, "id");
		Page<Product> list = productRepository.findByActive(true, pageable);
		model.addAttribute("active", "home");
		model.addAttribute("list", list.getContent());
		model.addAttribute("pageNumber", pageN + 1);
		model.addAttribute("pageSize", pageS + 5);
		return "client/home";
	}
	
	@GetMapping("/gioi-thieu")
	public String about(Model model) {
		
		return "client/about";
	}
	
	
	@GetMapping("/url2")
	public String url2(Model model) {
		
		return "redirect:/auth/login";
	}
	
	@GetMapping("/url3")
	@ResponseBody
	public String url3(Model model) {
		
		return "{name:nvteo}";
	}
}
