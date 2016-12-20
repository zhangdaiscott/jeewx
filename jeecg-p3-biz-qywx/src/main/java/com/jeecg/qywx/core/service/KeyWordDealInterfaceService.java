package com.jeecg.qywx.core.service;


/**
 * 关键字处理接口，以后对关注有任何的处理只实现接口
 * @author 付明星
 *
 */
public interface KeyWordDealInterfaceService {
	/**
	 * 关键字处理接口方法
	 * @param content 接受文本内容
	 * @param toUserName 微信公众账号ID
	 */
	String dealKeyMessage(String content,String toUserName,String fromUser,String accountid,String agentid);
}
