package com.situ.hotel.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.User;
import com.situ.hotel.mapper.OrderMapper;
import com.situ.hotel.service.OrderService;
import com.situ.hotel.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	// 在添加订单时，如果用户时新用户，需要先添加用户
	@Autowired
	private UserService userService;
	
	@Override
	public int add(Order order, User user) throws Exception {
		// 1. 判断用户是新用户还是老用户
		// 如果Order里面的userId不是空的，这是一个老用户
		if (order.getUserId() == null) {
			//这是一个新用户
			userService.add(user);	//将用户插入数据库
			
			order.setUserId(user.getId());	//	设置userId	订单房间的用户的ID
		} else {
			// 2.老用户，判断它是不是禁用了
			User oldUser = userService.getById(order.getUserId());
			
			if (oldUser == null) {
				throw new Exception("用户信息与系统不匹配!");
			} else if (oldUser.getStatus() == 1) {
				throw new Exception("当前用户已被禁用，无法预定房间!");
			}
		}
	
		// 保存订单信息		
		return orderMapper.insert(order);
	}

	@Override
	public PageInfo getByPage(Integer page, Integer limit, User user) {
		if(page == null) page = 1;
		if(limit == null) limit = 10;
		
		PageHelper.startPage(page, limit);
		List<Order> list = orderMapper.select(user);		//查询数据库的操作
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;	//这里一定不要忘了改
	}

	@Override
	public int edit(Order order) {
		//省略了验证
		return orderMapper.update(order);
	}

	@Override
	public int checkin(Order order) throws Exception {
		// 1.用户情况,如果用户数据是空的,要删除掉
		if (order.getUsers() != null) {
			//	遍历
			for (int i=order.getUsers().size()-1; i>=0; i--) {
				User user = order.getUsers().get(i);
				
				if (user.getName() == null && user.getIdcard() == null && user.getPhone() == null) {
					order.getUsers().remove(i);
					continue;
				}
				
				//如果用户是新用户,添加,如果用户是老用户,检查这个用户是不是禁用了
				if (user.getId() == null) {
					userService.add(user);
				} else {
					// 查询用户的状态
					if (userService.getById(user.getId()).getStatus() == 1) {
						throw new Exception("用户["+user.getName()+"]已被禁用!");
					}
				}
			}
			
			// 判断用户是否为空列表
			if (order.getUsers().size() == 0) {
				throw new Exception("办理入住时，至少要添加一个用户！");
			}
		}
		// 2.将order 更新到数据库中
		order.setInTime(new Date());  //入住时间
		order.setStatus(2);  //修改状态为入住状态
		
		return orderMapper.update(order);	//更新订单信息
	}

}
