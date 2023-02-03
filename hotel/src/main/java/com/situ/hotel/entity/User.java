package com.situ.hotel.entity;

/**
 * *实体类设计的四个步骤
 * 1. 实体类的类名与数据库的表名相对应
 * 2. 实体类的属性与数据表的字段相对应
 * 3. 属性私有化，提供公共的get和set方法
 * 4. 无参构造方法
 * @author Zhi Jiu
 *
 */
public class User {
	private Integer id;
	private String name;
	private String idcard;
	private String phone;
	private String password;
	private Integer status;
	
	//右键-> Source -> Generate -> Getters and Setters ... -> Select All -> Generate
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
