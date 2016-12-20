/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.excutor;

import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessageCustomSendRequest;
import com.alipay.api.response.AlipayMobilePublicMessageCustomSendResponse;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayClientFactory;
import com.jeecg.alipay.base.dao.AlipayAutoresponseDao;
import com.jeecg.alipay.base.entity.AlipayReceivetext;
import com.jeecg.alipay.core.service.AlipayTextDealInterfaceService;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.util.SystemUtil;

import net.sf.json.JSONObject;

/**
 * 菜单点击异步响应执行器
 * 
 * @author baoxing.gbx
 * @version $Id: InAlipayAsyncMsgSendExecutor.java, v 0.1 Jul 24, 2014 4:30:38 PM baoxing.gbx Exp $
 */
public class InAlipayAsyncMsgSendExecutor implements ActionExecutor {

	@Autowired
	private AlipayTextDealInterfaceService textDealInterfaceService;
	@Autowired
	private AlipayAccountService alipayAccountService;
	
    /** 线程池 */
    private static ExecutorService executors = Executors.newSingleThreadExecutor();

    /** 业务参数 */
    private JSONObject             bizContent;

    public InAlipayAsyncMsgSendExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayAsyncMsgSendExecutor() {
        super();
    }

    @Override
    public String execute() {
        //取得发起请求的支付宝账号id
        final String fromUserId = bizContent.getString("FromUserId");
        
      //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        //1. 首先同步响应一个消息
        String syncResponseMsg = AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        final String content = bizContent.getJSONObject("Text").getString("Content");
        //2. 异步发送消息
        executors.execute(new Runnable() {
            @Override
            public void run() {
                try {
                	AlipayReceivetext receiveText = new AlipayReceivetext();
                	receiveText.setContent(content);
                	String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                	receiveText.setId(randomSeed);
                	receiveText.setAccountid(SystemUtil.getOnlieAlipayAccountId());
                	receiveText.setCreatetime(new Date());
                	receiveText.setCreateDate(new Date());
                	receiveText.setFromusername(fromUserId);
                	receiveText.setMsgtype(AlipayUtil.REQ_MESSAGE_TYPE_TEXT);
                	receiveText.setResponse("0");
                	receiveText.setNickname("");
                	//保存消息
                	textDealInterfaceService.dealTextMessage(receiveText);
                	//遍历关键字列表
                    String requestMsg = AlipayMsgBuildUtil.buildSingleImgTextMsg(fromUserId);
                  //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
                    AlipayClient alipayClient = AlipayClientFactory.getAlipayClientInstance(alipayAccountService.getAlipayConfig());
                  //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
                    AlipayMobilePublicMessageCustomSendRequest request = new AlipayMobilePublicMessageCustomSendRequest();
                    request.setBizContent(requestMsg);

                    // 2.2 使用SDK接口类发送响应
                    AlipayMobilePublicMessageCustomSendResponse response = alipayClient.execute(request);

                    // 2.3 开发者根据响应结果处理结果
                    //这里只是简单的打印，请开发者根据实际情况自行进行处理
                    if (null != response && response.isSuccess()) {
                        System.out.println("异步发送成功，结果为：" + response.getBody());
                    } else {
                        System.out.println("异步发送失败 code=" + response.getCode() + "msg："
                                           + response.getMsg());
                    }
                } catch (Exception e) {
                    System.out.println("异步发送失败");
                }
            }
        });

        return syncResponseMsg;
    }
}
