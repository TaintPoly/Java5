package com.j5shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
public class AuthController {
	@GetMapping("login") //=>auth/login
	public String login(Model model) {
		return "client/login";
	}
	@PostMapping("login") //=>auth/login
	public String login(@RequestParam("email") String email, 
						@RequestParam("password")String password,
						@RequestParam(name = "remember", defaultValue = "false") boolean remember,
						Model model) {
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("remember: " + remember);
		if(email.equalsIgnoreCase("admin@gmail.com") && password.equals("123")) {
			model.addAttribute("success", "Đăng nhập thành công");
		}else {
			model.addAttribute("error", "Sai thông tin đăng nhập");
		}
		return "client/login";
	}
}
