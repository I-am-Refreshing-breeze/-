package com.situ.hotel.controller;

import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.situ.hotel.entity.Admin;
import com.situ.hotel.entity.Result;
import com.situ.hotel.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 登陆的方法
	 */
	@PostMapping("/login")
	public Result login(Admin admin, HttpSession session) {
		try {
			admin = adminService.login(admin);
			//登陆成功
			//保存登录信息到session中
			session.setAttribute("admin",admin);
			
			return Result.success(1, admin);
		} catch (Exception e) {
			e.printStackTrace();
			// 登陆失败
			return Result.error(-1, e.getMessage());
		}
	}
	/**
	 * 退出的方法
	 */
	@GetMapping("/logout")
	public Result logout(HttpSession session) {
		//清空session中的数据
		session.invalidate();
		
		return Result.success(1, null);
	}
	
	/**
	 * 获取当前登录用户的信息
	 */
	@GetMapping("/loginAdmin")
	public Result get(HttpSession session) {
		// 获取当前登录用户信息
		Admin admin = (Admin)session.getAttribute("admin");
		
		//成功
		if (admin != null) {
			return Result.success(1, admin);
		} else {
			//失败
			return Result.error(-1, "当前无登录信息");
		}
	}
	
}
