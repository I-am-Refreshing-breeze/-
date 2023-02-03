package com.situ.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Result;
import com.situ.hotel.entity.User;
import com.situ.hotel.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 添加
	 */
	@PostMapping({"/add","/reg"})
	public Result add(User user) {
		try {
			userService.add(user);
			return Result.success(1, user);
		} catch (Exception e) {
			e.printStackTrace();
			//添加失败
			return Result.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/edit")
	public Result edit(User user) {
		try {
			userService.edit(user);
			return Result.success(1, null);
		} catch (Exception e) {
			e.printStackTrace();
			//修改失败
			return Result.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 查询
	 */
	@GetMapping("/search")
	public Result search(Integer page, Integer limit, User user) {
		if (page == null) {
			// 不分页
			List<User> list = userService.search(user);
			return Result.success(0, list);
		} else {
			// 分页查询
			PageInfo pageInfo = userService.searchByPage(page, limit, user);
			return Result.success(0, pageInfo);
		}
	}
	
	/**
	 * 前台用户登录
	 */
	@PostMapping("/login")
	public Result login(User user, HttpSession session) {
		
		try {
			user = userService.login(user);
			
			//保存用户的登录信息
			session.setAttribute("user", user);
			
			return Result.success(1, user);
		} catch (Exception e) {
			e.printStackTrace();
			//失败
			return Result.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 用户退出的接口
	 */
	@GetMapping("/logout")
	public Result logout(HttpSession session) {
		// 清空session
		session.invalidate();
		
		return Result.success(1, null);
	}
	
	/**
	 * 获取登陆用户信息
	 */
	@GetMapping("/loginUser")
	public Result loginUser(HttpSession session) {
		// 去除当前登录用户的信息
		User user = (User)session.getAttribute("user");
		
		if (user != null) {
			return Result.success(1, user);
		} else {
			return Result.error(-1, null);
		}
	}
}
