package com.situ.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.situ.hotel.entity.User;

@Component
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
		throws Exception {
		//用户登录进行验证
		User user = (User) request.getSession().getAttribute("user");
		
		if (user!=null) {
			return true;
		} else {
			//跳转到登录页面
			response.sendRedirect("/user/login.html");
			//阻止处理器的执行
			return false;
		}
	}
}
