/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.excutor;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.core.exception.MyException;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;

import net.sf.json.JSONObject;

/**
 * 普通进入服务窗事件处理器
 * 用户进入服务窗，可在此处理器处理开发者需要的业务逻辑
 * 
 * @author taixu.zqq
 * @version $Id: InAlipayEnterExecutor.java, v 0.1 2014年7月24日 下午7:58:25 taixu.zqq Exp $
 */
public class InAlipayEnterExecutor implements ActionExecutor {

	@Autowired
	private AlipayAccountService alipayAccountService;
	
    /** 业务参数 */
    private JSONObject bizContent;

    public InAlipayEnterExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayEnterExecutor() {
        super();
    }

    /** 
     * @see com.alipay.executor.ActionExecutor#executor(java.util.Map)
     */
    @Override
    public String execute() throws MyException {
        //自身业务处理,这里只是简单打印下
        //建议开发者自行处理采用异步方式，参见InAlipayChatTextExecutor
        System.out.println("欢迎光临！");

        // 同步返回ack响应
        return this.setResponse();
    }

    /**
     * 设置response返回数据
     * 
     * @return
     */
    private String setResponse() throws MyException {

        //取得发起请求的支付宝账号id
        String fromUserId = bizContent.getString("FromUserId");
      //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        return AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
    }
}
