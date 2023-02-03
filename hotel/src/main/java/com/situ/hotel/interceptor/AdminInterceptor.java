package com.situ.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.situ.hotel.entity.Admin;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
		throws Exception {
		//用户登录进行验证
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		
		if (admin!=null) {
			return true;
		} else {
			//跳转到登录页面
			response.sendRedirect("/admin/login.html");
			//阻止处理器的执行
			return false;
		}
	}
}
