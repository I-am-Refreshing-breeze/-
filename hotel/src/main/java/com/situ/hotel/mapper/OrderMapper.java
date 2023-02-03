package com.situ.hotel.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.User;

@Mapper
public interface OrderMapper {
	/**
	 * *添加订单	在MySQL中order 是一个关键字,用来排序的,order by xxx
	 * 在SQL语句中，会优先将order识别为一个关键字,使用反单引号(ESC下面的那个键)
	 */
	@Insert({
		"<script>",
		"insert into `order` (",
		"<if test='inTime != null'> `in_time`,</if>",
		"<if test='out!=null'> `out`,</if>",
		"<if test='deposit!=null'> deposit,</if>",
		"<if test='cost!=null'> cost,</if>",
		"<if test='userId!=null'> user_id,</if>",
		"start, end, room_id) value (",
		
		"<if test='inTime != null'> #{in_time},</if>",
		"<if test='out!=null'> #{out},</if>",
		"<if test='deposit!=null'> #{deposit},</if>",
		"<if test='cost!=null'> #{cost},</if>",
		"<if test='userId!=null'> #{userId},</if>",
		
		"#{start}, #{end}, #{roomId})",
		"</script>"})
	int insert(Order order);
	
	/**
	 * *修改订单
	 */
	
	/**
	 * 查询订单
	 */
	@Select({"select * from `order`"})
	@Results({	//映射查询结果 -> 将数据库中的查询语句的结果集，映射到实体类的属性上.
		@Result(column = "id", property = "id", id = true),
		// 映射房间信息
		@Result(column = "room_id", property = "room",
				one = @One(select = "com.situ.hotel.mapper.RoomMapper.selectById")),
		// 映射用户信息
		@Result(column = "user_id", property = "user",
				one = @One(select = "com.situ.hotel.mapper.UserMapper.selectById"))
	})
	List<Order> selectAll();

	/**
	 * *多条件组合查询
	 * @param user
	 * @return
	 */
	@Select({
		"<script>",
		"select `order`.* from `order`, `user`",
		"where `order`.user_id = `user`.id",
		"<if test='name!=null and name.length>0'> and `user`.name like '%${name}%'</if>",
		"<if test='phone!=null and phone.length>0'> and `user`.phone like '%${phone}%'</if>",
		"</script>"
		})
	@Results({	//映射查询结果 -> 将数据库中的查询语句的结果集，映射到实体类的属性上.
		@Result(column = "id", property = "id", id = true),
		// 映射房间信息
		@Result(column = "room_id", property = "room",
				one = @One(select = "com.situ.hotel.mapper.RoomMapper.selectById")),
		// 映射用户信息
		@Result(column = "user_id", property = "user",
				one = @One(select = "com.situ.hotel.mapper.UserMapper.selectById"))
	})
	List<Order> select(User user);
	
	/**
	 * 修改订单
	 */
	@Update({
		"<script>",
		"update `order`",
		"<set>",
		"<if test='start!=null'> `start` = #{start}, </if>",
		"<if test='end!=null'> `end` = #{end}, </if>",
		"<if test='inTime!=null'> in_time = #{inTime}, </if>",
		"<if test='out!=null'> `out` = #{out}, </if>",
		"<if test='deposit!=null'> deposit = #{deposit}, </if>",
		"<if test='cost!=null'> cost = #{cost},</if>",
		"<if test='status!=null'> status = #{status}, </if>",
		"</set>",
		"where id = #{id}",
		"</script>"
	})
	int update(Order order);
}
