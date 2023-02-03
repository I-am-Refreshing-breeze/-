package com.situ.hotel.entity;

import java.math.BigDecimal;

/**
 * 房间实体类，与数据库中的room表相对应
 * 不要使用基本数据类型，要用包装类（包装类可以保存Null）
 * @author Zhi Jiu
 *
 */
public class Room {
	private Integer id;
	private String number;
	private Integer type;
	private String dscp;
	private BigDecimal price;
	private String pic;
	private Integer status;
	
	//get和set方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDscp() {
		return dscp;
	}
	public void setDscp(String dscp) {
		this.dscp = dscp;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
