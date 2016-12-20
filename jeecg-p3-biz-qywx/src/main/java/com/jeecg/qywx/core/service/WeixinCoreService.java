package com.jeecg.qywx.core.service;


public interface WeixinCoreService {

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public abstract String processRequest(String resultXml);

}