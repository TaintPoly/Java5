package com.j5shop.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.j5shop.model.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Account account =  (Account) request.getSession().getAttribute("account");
		if (account == null) {
			response.sendRedirect("/dang-nhap");
			return false;
		}
		String uriString = request.getRequestURI();
		if(uriString.contains("admin") && !account.isAdmin()) {
			response.sendRedirect("/403");
			return false;
		}
		return true;
	}
}
