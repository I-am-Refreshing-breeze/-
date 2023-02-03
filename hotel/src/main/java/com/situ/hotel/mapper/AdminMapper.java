package com.situ.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.situ.hotel.entity.Admin;

@Mapper
public interface AdminMapper {

	/**
	 * 根据用户名查询
	 */
	@Select("select * from `admin` where username = #{username}")
	Admin selectByUsername(String username);
}
