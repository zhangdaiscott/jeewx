package com.jeecg.qywx.core.weixin.model;

/**
 * 部门实体类
 * @author Administrator
 *
 */
public class Group {
	private int id;
	private String name;
	private String parentid;
	private String order;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
