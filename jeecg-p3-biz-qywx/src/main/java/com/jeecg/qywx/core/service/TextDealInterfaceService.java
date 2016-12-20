package com.jeecg.qywx.core.service;

import com.jeecg.qywx.base.entity.QywxReceivetext;

/**
 * 文本处理接口
 * @author 付明星
 *
 */
public interface TextDealInterfaceService {
	/**
	 * 文本消息处理接口
	 * @param receiveText 文本消息实体类
	 */
	void dealTextMessage(QywxReceivetext receiveText);
}
