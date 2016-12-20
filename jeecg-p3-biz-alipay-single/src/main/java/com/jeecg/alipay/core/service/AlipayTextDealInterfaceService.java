package com.jeecg.alipay.core.service;

import com.jeecg.alipay.base.entity.AlipayReceivetext;

/**
 * 文本处理接口
 * @author 付明星
 *
 */
public interface AlipayTextDealInterfaceService {
	/**
	 * 文本消息处理接口
	 * @param receiveText 文本消息实体类
	 */
	void dealTextMessage(AlipayReceivetext receiveText);
}
