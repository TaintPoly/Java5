package com.j5shop.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class SendMailController {
	@Scheduled(cron = "0 12 19 24 FEB *")
	public void sendMail() {
		System.out.println("Hello! Welcome to Java 5");
	}
}
