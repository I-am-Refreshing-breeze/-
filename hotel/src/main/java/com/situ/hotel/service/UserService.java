package com.situ.hotel.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.User;

public interface UserService {
	
	/**
	 * 添加
	 */
	int add(User user) throws Exception;
	/**
	 * 编辑
	 * @throws Exception 
	 */
	int edit(User user) throws Exception;
	/**
	 * 不分页查询
	 */
	List<User> search(User user);
	/**
	 * 分页查询
	 */
	PageInfo searchByPage(Integer page, Integer limit, User user);
	
	/**
	 * 根据ID获取用户
	 * @param userId
	 * @return
	 */
	User getById(Integer userId);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User login(User user) throws Exception;
}
