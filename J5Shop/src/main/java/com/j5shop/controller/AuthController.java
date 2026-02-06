package com.j5shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.j5shop.model.Account;
import com.j5shop.repositoy.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class AuthController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	HttpSession session;
	@GetMapping("dang-nhap") //=>auth/login
	public String login(Model model) {
		return "client/login";
	}
	@PostMapping("dang-nhap") //=>auth/login
	public String login(@RequestParam("email") String email, 
						@RequestParam("password")String password,
						@RequestParam(name = "remember", defaultValue = "false") boolean remember,
						Model model) {
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("remember: " + remember);
		Account account = accountRepository.findByEmailAndActive(email, true);
		
		if(account != null && password.equals(account.getPassword())) {
			session.setAttribute("account", account);
			model.addAttribute("success", "Đăng nhập thành công");
		}else {
			model.addAttribute("error", "Sai thông tin đăng nhập");
		}
		return "client/login";
	}
}
