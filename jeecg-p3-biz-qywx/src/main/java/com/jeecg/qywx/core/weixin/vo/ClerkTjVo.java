package com.jeecg.qywx.core.weixin.vo;


public class ClerkTjVo {
	
	private String userId;
	public String name;
	public int totalNum;
	public int totalYes;
	
	public ClerkTjVo(){};
	
	public ClerkTjVo(String name, int totalNum, int totalYes,String userId) {
		super();
		this.userId=userId;
		this.name = name;
		this.totalNum = totalNum;
		this.totalYes = totalYes;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalYes() {
		return totalYes;
	}
	public void setTotalYes(int totalYes) {
		this.totalYes = totalYes;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
