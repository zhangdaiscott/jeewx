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
 * 取消关注服务窗执行器
 * 
 * @author baoxing.gbx
 * @version $Id: InAlipayUnFollowExecutor.java, v 0.1 Jul 24, 2014 4:29:29 PM baoxing.gbx Exp $
 */
public class InAlipayUnFollowExecutor implements ActionExecutor {

	@Autowired
	private AlipayAccountService alipayAccountService;
    /** 业务参数 */
    private JSONObject bizContent;

    public InAlipayUnFollowExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayUnFollowExecutor() {
        super();
    }

    @Override
    public String execute() {

        //取得发起请求的支付宝账号id
        final String fromUserId = bizContent.getString("FromUserId");

        //TODO 根据支付宝请求参数，开发者可以删除之前保存的本地支付宝UID-服务窗ID的关注关系
        // 这里只是个样例程序，所以这步省略。
      //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        return AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
    }
}
