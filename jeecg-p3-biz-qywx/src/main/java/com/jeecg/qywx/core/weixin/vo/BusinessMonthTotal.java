package com.jeecg.qywx.core.weixin.vo;

public class BusinessMonthTotal {
	
	public String month;
	public int totalNot;
	public int totalYes;
	public String beginTime;
	public String endTime;

	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getTotalNot() {
		return totalNot;
	}
	public void setTotalNot(int totalNot) {
		this.totalNot = totalNot;
	}
	public int getTotalYes() {
		return totalYes;
	}
	public void setTotalYes(int totalYes) {
		this.totalYes = totalYes;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
