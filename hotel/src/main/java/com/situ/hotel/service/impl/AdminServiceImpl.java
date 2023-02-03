package com.situ.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.situ.hotel.entity.Admin;
import com.situ.hotel.mapper.AdminMapper;
import com.situ.hotel.service.AdminService;
import com.situ.hotel.util.MD5Util;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	@Override
	public Admin login(Admin admin) throws Exception {
		// 1.验证账号和密码的格式
		if (admin.getUsername() == null || admin.getUsername().length() < 3 || admin.getUsername().length() > 16) {
			throw new Exception("账号必须是3-16位的字符串");
		}
		if (admin.getPassword() == null || admin.getPassword().length() < 3 || admin.getPassword().length() > 16) {
			throw new Exception("密码必须是3-16位的字符串");
		}
		
		//2.账号是否存在
		Admin sAdmin = adminMapper.selectByUsername(admin.getUsername());
		
		if ( sAdmin == null) {
			throw new Exception("账号不存在");
		}
		
		//3.判断密码是否正确
		if( ! sAdmin.getPassword().equals(MD5Util.getMD5(admin.getPassword()))) {
			throw new Exception("密码错误");
		}
		
		return sAdmin;	//一定要注意，这里返回sAdmin
	}

}
