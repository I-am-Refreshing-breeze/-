package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.Room;

public interface RoomService {
	/**
	 * 添加房间
	 */
	int add(Room room);
	
	/**
	 * 搜索
	 */
	PageInfo search(Integer page, Integer limit, Room room);
	
	/**
	 * 修改
	 */
	int edit(Room room);
	
	/**
	 * 删除
	 */
	int remove(int id);

	/**
	 * 高级搜索方法
	 * @param page
	 * @param limit
	 * @param room
	 * @param order
	 * @return
	 */
	PageInfo search(Integer page, Integer limit, Room room, Order order);
}
