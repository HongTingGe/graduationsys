package com.ght.graduationsys.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Integer code; // 状态码 0 表示成功，1表示处理中，-1表示失败
	private Object data; // 数据
	private String msg;// 描述
	private LinkedHashMap<PermissionModule,List<Permission>> menus;

	public JsonData() {
	}

	public JsonData(Integer code, Object data) {
		this.code = code;
		this.data = data;
		this.msg = null;
	}

	public JsonData(Integer code, String msg) {
		this.code = code;
		this.data = null;
		this.msg = msg;
	}

	public JsonData(Integer code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public JsonData(User user,Integer code, Object data, String msg) {
		this.user=user;
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public JsonData(User user,Integer code, Object data, String msg,LinkedHashMap<PermissionModule,List<Permission>> menus) {
		this.user=user;
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.menus=menus;
	}

	// 成功，传入数据
	public static JsonData buildSuccess() {
		return new JsonData(0, null, null);
	}

	// 成功，传入描述
	public static JsonData buildSuccess(String msg) {
		return new JsonData(0, null, msg);
	}

	// 成功，传入数据
	public static JsonData buildSuccess(User user,Object data,LinkedHashMap<PermissionModule,List<Permission>> menus) {
		return new JsonData(user,0, data, null,menus);
	}

	// 失败，传入描述信息
	public static JsonData buildError(String msg) {
		return new JsonData(-1, null, msg);
	}

	public static JsonData buildError(Object data) {
		return new JsonData(-1, data, null);
	}

	public static JsonData buildError(Object data,int code) {
		return new JsonData(code, data, null);
	}


	public static JsonData buildError(String msg,int code) {
		return new JsonData(-1,msg);
	}



	// 失败，传入描述信息,状态码
	public static JsonData buildError(String msg, Integer code) {
		return new JsonData(code, null, msg);
	}

	// 成功，传入数据,及描述信息
	public static JsonData buildSuccess(Object data, String msg) {
		return new JsonData(0, data, msg);
	}

	// 成功，传入数据,及状态码
	public static JsonData buildSuccess(Object data, int code) {
		return new JsonData(code, data, null);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LinkedHashMap<PermissionModule, List<Permission>> getMenus() {
		return menus;
	}

	public void setMenus(LinkedHashMap<PermissionModule, List<Permission>> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "JsonData{" +
				"user=" + user +
				", code=" + code +
				", data=" + data +
				", msg='" + msg + '\'' +
				", menus=" + menus +
				'}';
	}
}
