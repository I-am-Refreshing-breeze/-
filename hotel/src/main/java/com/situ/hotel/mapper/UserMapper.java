package com.situ.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.hotel.entity.User;

@Mapper
public interface UserMapper {

	/**
	 * *添加
	 */
	@Insert({"insert into user (name , idcard, phone, password)",
		"value(#{name}, #{idcard}, #{phone}, #{password})"})
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int insert(User user);
	
	/**
	 * *更新
	 */
	@Update({"<script>",
		"update user",
		"<set>",
		"<if test='name!=null'> name=#{name},</if>",
		"<if test='idcard!=null'> idcard=#{idcard},</if>",
		"<if test='phone!=null'> phone=#{phone},</if>",
		"<if test='password!=null'> password=#{password},</if>",
		"<if test='status!=null'> status=#{status},</if>",
		"</set>",
		"where id = #{id}",
		"</script>"})
	int update(User user);
	
	/**
	 * *多条件组合查询
	 */
	@Select({"<script>",
		"select * from user",
		"<where>",
		"<if test='name!=null and name.length>0'> name like '%${name}%'</if>",
		"<if test='idcard!=null and idcard.length>0'> and idcard like '%${idcard}%'</if>",
		"<if test='phone!=null and phone.length>0'> and phone=#{phone}</if>",
		"<if test='status!=null'> and status=#{status}</if>",
		"</where>",
		"</script>"})
	List<User> select(User user);
	
	/**
	 * *根据ID查询
	 */
	@Select("select * from user where id=#{id}")
	User selectById(int id);
	
	/**
	 * 根据身份证号查询
	 */
	@Select("select * from user where idcard=#{idcard}")
	User selectByIdcard(String idcard);
	/**
	 * 根据电话查询
	 */
	@Select("select * from user where phone = #{phone}")
	User selectByPhone(String phone);
}
