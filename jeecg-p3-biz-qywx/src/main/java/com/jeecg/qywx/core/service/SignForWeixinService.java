package com.jeecg.qywx.core.service;

public interface SignForWeixinService {

	/**
	 * 验证签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param appid 应用ID
	 * @return
	 * @throws Exception 
	 */
	public abstract String checkSignature(String signature, String timestamp,
			String nonce,String echostr,String corpid,Integer appid) throws Exception;

}