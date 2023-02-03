package com.situ.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.User;
import com.situ.hotel.mapper.UserMapper;
import com.situ.hotel.service.UserService;
import com.situ.hotel.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 可用户后台管理员添加用户
	 * 也可用于前台游客注册用户
	 */
	@Override
	public int add(User user) throws Exception {
		// 1.姓名，身份证号，电话，格式正确，密码如果有，也必须符合要求
		if (user.getName() == null || user.getName().length()<2 || user.getName().length()>32) {
			throw new Exception("姓名必须是2-32位字符串！");
		}
		if (user.getIdcard() == null || user.getIdcard().length() != 18) {
			throw new Exception("身份证号必须是18位！！！");
		}
		if (user.getPhone() == null || user.getPhone().length() != 11) {
			throw new Exception("电话号码必须是11位！！！");
		}
		if (user.getPassword() != null &&
				(user.getPassword().length() < 6 || user.getPassword().length() > 16)) {
			throw new Exception("密码必须是6-16位的字符串！！！");
		}
		
		// 2.验证身份证号，电话是不能重复的
		if (userMapper.selectByIdcard(user.getIdcard()) != null) {
			throw new Exception("身份证号已经存在!!!");
		}
		if (userMapper.selectByPhone(user.getPhone()) != null) {
			throw new Exception("电话号码已经存在!!!");
		}
		
		// 3.如果没有设置密码，将身份证号的后六位作为默认密码
		if (user.getPassword() == null) {
			// 截取身份证的后六位
			user.setPassword(user.getIdcard().substring(user.getIdcard().length() - 6));
		}
		
		// 4.对密码进行MD5加密
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		
		// 5.保存到数据表中
		return userMapper.insert(user);
	}

	@Override
	public int edit(User user) throws Exception {
		// 1.姓名，身份证，电话，密码
		if (user.getName() != null && (user.getName().length()<2 || user.getName().length()>32)) {
			throw new Exception("姓名必须是2-32位字符串！");
		}
		if (user.getIdcard() != null && user.getIdcard().length() != 18) {
			throw new Exception("身份证号必须是18位！！！");
		}
		if (user.getPhone() != null && user.getPhone().length() != 11) {
			throw new Exception("电话号码必须是11位！！！");
		}
		if (user.getPassword() != null &&
				(user.getPassword().length() < 6 || user.getPassword().length() > 16)) {
			throw new Exception("密码必须是6-16位的字符串！！！");
		}
		
		// 2.用户是否存在
		if (userMapper.selectById(user.getId()) == null) {
			throw new Exception("ID不存在！！！");
		}
		
		// 3.身份证号、电话不可重复
		User sUser = userMapper.selectByIdcard(user.getIdcard());
		if (sUser != null && sUser.getId() != user.getId()) {
			throw new Exception("身份证号已存在！！！");
		}
		sUser = userMapper.selectByPhone(user.getPhone());
		if (sUser != null && sUser.getPhone() != user.getPhone()) {
			throw new Exception("电话号码已存在！！！");
		}
		
		// 3.5对密码进行加密操作
		if (user.getPassword() != null) user.setPassword(MD5Util.getMD5(user.getPassword()));
		
		// 4.更新数据库
		return userMapper.update(user);
	}

	@Override
	public List<User> search(User user) {
		return userMapper.select(user);
	}

	@Override
	public PageInfo searchByPage(Integer page, Integer limit, User user) {
		if (limit == null) limit = 10;
		
		// 开启PageHelper的分页
		PageHelper.startPage(page, limit);
		//调用查询语句
		List<User> list = userMapper.select(user);
		//生成分页信息
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}

	@Override
	public User getById(Integer userId) {
		return userMapper.selectById(userId);
	}

	@Override
	public User login(User user) throws Exception {
		// 1. 验证参数
		if (user.getPhone() == null || user.getPhone().length() != 11) {
			throw new Exception("电话号码 必须是11位的!!!");
		}
		if (user.getPassword() == null || user.getPassword().length() < 3 || user.getPassword().length() > 16) {
			throw new Exception("密码必须是3-16位的字符串!!!");
		}
		
		// 2. 根据手机号查询用户
		User sUser = userMapper.selectByPhone(user.getPhone());
		if (sUser == null) {
			throw new Exception("电话号码未注册!!!");
		}
		
		// 3.验证密码
		if ( !sUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
			throw new Exception("密码错误!!!");
		}
		
		// 4.验证用户状态
		if (sUser.getStatus() == 1) {
			throw new Exception("当前用户已被禁用,请联系管理员!!!");
		}
		
		return sUser;	//这里一定不要忘了改
	}

}
