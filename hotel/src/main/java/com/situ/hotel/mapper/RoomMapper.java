package com.situ.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.hotel.entity.Order;
import com.situ.hotel.entity.Room;

@Mapper   //mybatis的注解,当SpringBoot项目启动时，扫描到这个接口，并且把它放到Spring容器中
			//就可以直接在其他的地方直接使用这个接口的对象了
public interface RoomMapper {
	/**
	 * 添加房间的方法
	 * 
	 * insert into 表名 (字段列表) value/values(值列表)
	 * value 速度比values略高
	 */
	@Insert({"insert into room (number, type, dscp, price, pic)",
		"value(#{number},#{type}, #{dscp}, #{price}, #{pic})"})
	//#{}可以从参数room中取出相应的属性，绑定到SQL中
	@Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "id")
	//在向数据表中插入数据时，获取自动生成的主键id，再将值设置到参数room的id属性中
	int insert(Room room);
	
	/**
	 * 根据房间号查询房间
	 */
	@Select("select * from room where number=#{number}")
	Room selectByNumber(String number);
	
	/**
	 * 查询房间	MyBatis的动态SQL:执行SQL语句时，可以根据一定的条件生成SQL语句
	 */
	@Select({"<script>",	//在注解中使用动态SQL，必须由<script>，否则动态SQL将不会解析
		"select * from room",
		"<where>",		//如果标签内有内容，生成where关键字,否则不生成where
		"<if test='number!=null and number.length>0'> number = #{number} </if>",		//房间号
		"<if test='type!=null'> and type=#{type}</if>",	//房间类型
		"<if test='dscp!=null and dscp.length>0'> and dscp like '%${dscp}%'</if>",		//描述信息的模糊查询
		"<if test='status!=null'> and status=#{status}</if>",
		"</where>",
		"</script>"})
	List<Room> select(Room room);
	
	/**
	 * 修改的方法
	 */
	@Update({"update room set number=#{number},",
			"type=#{type},",
			"dscp=#{dscp},",
			"price=#{price},",
			"pic=#{pic},",
			"status=#{status}",
			"where id=#{id}"})
	int update(Room room);
	
	/**
	 * 根据ID查询房间
	 */
	@Select("select * from room where id=#{id}")
	Room selectById(int id);

	/**
	 * 高级的查询方法
	 * @param room
	 * @param order
	 * @return
	 */
	@Select({
		"<script>",
		"select * from room",
		"<where>",
		"<if test='room.number != null and room.number.length>0'> and room.number=#{room.number}</if>",
		"<if test='room.type != null'> and room.type=#{room.type}</if>",
		"<if test='room.dscp != null and room.dscp.length>0'> and room.dscp like '%${room.dscp}%'</if>",
		"and room.status = 0",	//只查询状态正常的房间
		"<if test='order.start!=null and order.end!=null'>",
		"and room.id not in ( ",
		"	select room_id from `order` ",
		"	where ( `start` &lt;= #{order.start}",
		"		and `end` &gt;= #{order.start}",
		"	or `start` &lt;= #{order.end}",
		"		and `end` &gt;= #{order.end}",
		"	or `start` &gt;= #{order.start}",
		"		and `end` &lt;= #{order.end} )",
		"	and status != 1",	// 去掉已经取消的订单
		"	)",	//	去掉时间上冲突的房间 start end
		"</if>",
		"</where>",
		"</script>"
	})
	List<Room> selectByOrder(@Param("room")Room room, @Param("order")Order order);
}




