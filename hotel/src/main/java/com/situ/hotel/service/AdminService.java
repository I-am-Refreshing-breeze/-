package com.situ.hotel.service;

import com.situ.hotel.entity.Admin;

public interface AdminService {

	/**
	 * 登录方法
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin) throws Exception;
}
