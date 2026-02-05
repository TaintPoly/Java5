package com.j5shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j5shop.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	//HT
	//Mssv
	@Autowired
	HttpSession session;
	List<Product> list = new ArrayList<Product>();
	
	@GetMapping("/")
	public String home(Model model) {
		list = (List<Product>) session.getAttribute("list");
		model.addAttribute("active", "home");
		model.addAttribute("list", list);
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
