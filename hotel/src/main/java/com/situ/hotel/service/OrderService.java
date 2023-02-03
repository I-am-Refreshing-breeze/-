package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;

import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.User;

public interface OrderService {

	/**
	 * 添加
	 */
	int add(Order order, User user) throws Exception;
	
	/**
	 * 编辑
	 */
	int edit(Order order);
	
	/**
	 * 查询
	 * @param user 
	 */
	PageInfo getByPage(Integer page, Integer limit, User user);
	
	/**
	 * 办理入住
	 * @param order
	 * @return
	 * @throws Exception 
	 */

	int checkin(Order order) throws Exception;
}
