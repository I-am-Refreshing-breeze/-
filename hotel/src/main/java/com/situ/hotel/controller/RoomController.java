package com.situ.hotel.controller;

import javax.annotation.Resource;
import javax.management.Query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.Result;
import com.situ.hotel.entity.Room;
import com.situ.hotel.service.RoomService;

@RestController      //注册到Spring容器中，当前类的方法都返回JSON格式数据
@RequestMapping("/api/room")  // 添加所有方法的URL的前缀
public class RoomController {
	
	//注入Service层的对象
	@Resource
	private RoomService roomService;
	
	@PostMapping("/add")
	public Result add (Room room) {
		//1.获取参数 Spring可以给方法自动注入参数
		//2.业务逻辑
		int code = roomService.add(room);
		//3.返回数据
		if (code > 0) { // 成功
			return Result.success(code, null);
		}else if(code == -1) {
			return Result.error(code, "房间号不能为空，也不能超过8个字符");
		}else if(code == -2) {
			return Result.error(code, "房间类型不正确");
		}else if(code == -3) {
			return Result.error(code, "描述不能超过128个字符");
		}else if(code == -4) {
			return Result.error(code, "房间价格应该是正数");
		}else if(code == -5) {
			return Result.error(code, "房间号已经存在，请不要重复添加");
		}else {
			return Result.error(code, "未知错误，请联系技术人员");
		}
	}
	
	/**
	 * 搜索方法
	 */
	@GetMapping("/search")
	public Result search(Integer page, Integer limit, Room room) {
		// 1.获取参数			上面面已经获取需要的参数
		// 2.业务逻辑 调用Service层
		PageInfo pageInfo = roomService.search(page, limit, room);
		// 3.返回数据
		return Result.success(0, pageInfo);
	}
	
	/**
	 * 高级搜索方法
	 */
	@GetMapping({"/query","/user/query"})
	public Result query(Integer page, Integer limit, Room room,Order order) {
		PageInfo pageInfo = roomService.search(page, limit, room, order);
		return Result.success(0, pageInfo);
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/edit")
	public Result edit(Room room) {
		int code = roomService.edit(room);
		
		if(code > 0) {
			return Result.success(code, null);
		} else if (code == -1) {
			return Result.error(code, "ID不存在");
		} else if (code == -2) {
			return Result.error(code, "房间名重复");
		} else {
			return Result.error(code, "未知错误");
		}
	}
	
	/**
	 * 删除
	 */
	@GetMapping("/remove")
	public Result remove(int id) {
		int code = roomService.remove(id);
		
		if (code > 0) {
			return Result.success(code, null);
		} else {
			return Result.error(code, "未知错误");
		}
	}
}
