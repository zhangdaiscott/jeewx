/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.excutor;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;

import net.sf.json.JSONObject;

/**
 * 关注服务窗执行器
 * 
 * @author baoxing.gbx
 * @version $Id: InAlipayFollowExecutor.java, v 0.1 Jul 24, 2014 4:29:04 PM baoxing.gbx Exp $
 */
public class InAlipayFollowExecutor implements ActionExecutor {

	@Autowired
	private AlipayAccountService alipayAccountService;
	
    /** 业务参数 */
    private JSONObject bizContent;

    public InAlipayFollowExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayFollowExecutor() {
        super();
    }

    @Override
    public String execute() {

        //TODO 根据支付宝请求参数，可以将支付宝账户UID-服务窗ID关系持久化，用于后续开发者自己的其他操作
        // 这里只是个样例程序，所以这步省略。
        // 直接构造简单响应结果返回
        final String fromUserId = bizContent.getString("FromUserId");
      //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        return AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
    }
}
