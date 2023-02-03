package com.situ.hotel.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.Result;
import com.situ.hotel.entity.User;
import com.situ.hotel.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 预订
	 */
	@PostMapping({"/add","/user/add"})
	public Result add(Order order, User user) {	
		try {
			orderService.add(order, user);
			//添加成功
			return Result.success(1, null);
		} catch (Exception e) {
			e.printStackTrace();
			//添加失败
			return Result.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 获取所有订单
	 */
	@GetMapping("/getAll")
	public Result getAll(Integer page, Integer limit, User user) {
		
		PageInfo pageInfo = orderService.getByPage(page, limit, user);
		
		return Result.success(0, pageInfo);
	}
	
	/**
	 *  获取我的订单
	 */
	@GetMapping("/user/getAll")
	public Result getAll(Integer page, Integer limit, HttpSession session) {
		//获取当前登录的用户
		User user = (User)session.getAttribute("user");
		
		PageInfo pageInfo = orderService.getByPage(page, limit, user);
		
		return Result.success(0, pageInfo);
	}
	
	/**
	 * 编辑订单
	 */
	@PostMapping({"/edit","/user/edit"})
	public Result edit(Order order) {
		int code = orderService.edit(order);
		
		if (code > 0) {
			return Result.success(code, null);
		} else {
			return Result.error(code, "未知错误，修改订单失败");
		}
	}
	
	/**
	 * 入住方法
	 */
	@PostMapping("/checkin")
	public Result checkin(@RequestBody Order order) {	//@RequestBody 以JSON读取客户端的数据
		
		//处理入住
		try {
			int code = orderService.checkin(order);
			return Result.success(code, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(-1, e.getMessage());
		}
		
	}
	
	/**
	 * 退房接口
	 */
	@PostMapping("/checkout")
	public Result checkout(Order order) {
		// 客户端回传订单的Id过来
		order.setOut(new Date());  //设置退房时间为当前时间
		order.setStatus(3);  //修改订单状态为完成
		
		
		int code = orderService.edit(order);
		
		if (code > 0) {
			return Result.success(code, null);
		} else {
			return Result.error(code, "退房失败，未知错误!!!");
		}
	}
}
