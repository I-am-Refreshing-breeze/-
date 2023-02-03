package com.situ.hotel.entity;

/**
 * JSON数据的一个封装类
 * @author Zhi Jiu
 *
 */
public class Result {
	private Integer code;   //代码
	private String msg;    //信息，比如错误信息
	private Object data;   //数据
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	// 成功
	public static Result success(Integer code,Object data) {
		Result result = new Result();
		result.setCode(code);
		result.setData(data);
		return result;
	}
	//失败
	public static Result error(Integer code,String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
