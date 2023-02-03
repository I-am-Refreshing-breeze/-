package com.situ.hotel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.Room;
import com.situ.hotel.mapper.RoomMapper;
import com.situ.hotel.service.RoomService;

@Service   //Spring的注解，语义注解，表示当前类是一个Service层的类，将当前类注册到Spring容器中
			//在SpringBoot启动时，扫描这个类，放到Spring容器中
public class RoomServiceImpl implements RoomService {
	
		//当一个类实现接口时，会继承接口中的方法
		//因为接口中的方法，都是抽象方法
		//1.重写/实现父接口中的方法
		//2.当前类变成一个抽象类 否决
	
		//使用Spring自动注入一个RoomMapper的对象
		//@Autowired     根据类型自动注入
	@Resource  //根据名字自动注入
	private RoomMapper roomMapper;
	@Override
	public int add(Room room) {
		// 1.校验参数
		// 1.1房间号不能为空，也不能超过8个字符
		if (room.getNumber() == null || room.getNumber().length() == 0 || room.getNumber().length()>8) {
			//房间号格式问题
			return -1;
		}
		// 1.2房间类型只能是，0，1，2
		if (room.getType()<0 || room.getType()>2) {
			//房间类型不正确
			return -2;
		}
		// 1.3描述不能超过128个字符
		if (room.getDscp().length() > 128) {
			return -3;
		}
		// 1.4价格应该是正数
		if (room.getPrice().compareTo(new BigDecimal(0)) <= 0) {
			return -4;
		}
		// 2.保证房间号不能重复
		// 2.1先根据房间号查询数据库，
		if ( roomMapper.selectByNumber(room.getNumber()) !=null ) {
			return -5;
		}
		
		// 3.将房间信息保存到数据库。
		return roomMapper.insert(room);
	}
	
	@Override
	public PageInfo search(Integer page, Integer limit, Room room) {
		// 1.参数验证
		if (page == null) page = 1;
		if (limit == null) limit = 10;
		// 2.逻辑处理	分页
		PageHelper.startPage(page, limit);	//	开启分页，在执行SQL语句时，会根据当前的分页信息拼接limit
		// 3.访问数据库
		List list = roomMapper.select(room);
		// 自动生成分页信息
		PageInfo pageInfo = new PageInfo<>(list);	
		
		return pageInfo;
	}

	@Override
	public int edit(Room room) {
		// 1.id是否存在
		if (roomMapper.selectById(room.getId()) == null) {
			//id不存在
			return -1;
		}
		// 2.房间号不能重复
		Room sRoom = roomMapper.selectByNumber(room.getNumber());
		if (sRoom != null && sRoom.getId() != room.getId()) {
			//房间号不能重复
			return -2;
		}
		
		// 3.保存到数据库中
		return roomMapper.update(room);
	}

	@Override
	public int remove(int id) {
		// 软删除,并不是从数据表中直接delete，而是修改表记位
		Room room = roomMapper.selectById(id);
		room.setStatus(1);
		
		return roomMapper.update(room);
	}

	@Override
	public PageInfo search(Integer page, Integer limit, Room room, Order order) {
		if (page == null) page = 1;
		if (limit == null) limit = 10;
		
		PageHelper.startPage(page, limit);
		
		List<Room> list = roomMapper.selectByOrder(room,order);
		
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;	//这里不要忘了返回pageInfo
	}
	
	
}
