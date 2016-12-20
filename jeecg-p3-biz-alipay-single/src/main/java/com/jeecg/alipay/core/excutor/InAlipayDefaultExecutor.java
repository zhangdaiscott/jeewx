/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.excutor;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.core.exception.MyException;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;

import net.sf.json.JSONObject;

/**
 * 默认执行器(该执行器仅发送ack响应)
 * 
 * @author baoxing.gbx
 * @version $Id: InAlipayDefaultExecutor.java, v 0.1 Jul 30, 2014 10:22:11 AM baoxing.gbx Exp $
 */
public class InAlipayDefaultExecutor implements ActionExecutor {

	@Autowired
	private AlipayAccountService alipayAccountService;
	
    /** 业务参数 */
    private JSONObject bizContent;

    public InAlipayDefaultExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayDefaultExecutor() {
        super();
    }

    /**
     * 
     * @see com.alipay.executor.ActionExecutor#execute()
     */
    @Override
    public String execute() throws MyException {

        //取得发起请求的支付宝账号id
        final String fromUserId = bizContent.getString("FromUserId");
      //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        return AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
    }
}
