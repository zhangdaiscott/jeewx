/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.excutor;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessageCustomSendRequest;
import com.alipay.api.response.AlipayMobilePublicMessageCustomSendResponse;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayClientFactory;
import com.jeecg.alipay.base.entity.AlipayReceivetext;
import com.jeecg.alipay.core.exception.MyException;
import com.jeecg.alipay.core.service.AlipayKeyWordDealInterfaceService;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.util.SystemUtil;

import net.sf.json.JSONObject;

/**
 * 聊天执行器(纯文本消息)
 * 
 * @author baoxing.gbx
 * @version $Id: InAlipayChatExecutor.java, v 0.1 Jul 28, 2014 5:17:04 PM baoxing.gbx Exp $
 */
@Service("inAlipayChatTextExecutor")
public class InAlipayChatTextExecutor implements ActionExecutor {
	@Autowired
	public AlipayKeyWordDealInterfaceService alipayKeyWordDealInterfaceService;
	@Autowired
	private AlipayAccountService alipayAccountService;
    /** 线程池 */
    private static ExecutorService executors = Executors.newSingleThreadExecutor();

    /** 业务参数 */
    private JSONObject             bizContent;

    public InAlipayChatTextExecutor(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public InAlipayChatTextExecutor() {
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
        //1. 首先同步构建ACK响应
        String syncResponseMsg = AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
      //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
        //2. 异步发送消息
        executors.execute(new Runnable() {
            @Override
            public void run() {
                try {
                	String content = bizContent.getJSONObject("Text").getString("Content");
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
                	//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
                    AlipayClient alipayClient = AlipayClientFactory.getAlipayClientInstance(alipayAccountService.getAlipayConfig());
                  //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
                    AlipayMobilePublicMessageCustomSendRequest request = new AlipayMobilePublicMessageCustomSendRequest();
                    String requestMsg = alipayKeyWordDealInterfaceService.dealKeyMessage(content, alipayAccountService.getAccount().getId(),fromUserId);
                    request.setBizContent(requestMsg);
                    // 2.2 使用SDK接口类发送响应
                    AlipayMobilePublicMessageCustomSendResponse response = alipayClient.execute(request);

                    // 2.3 商户根据响应结果处理结果
                    //这里只是简单的打印，请商户根据实际情况自行进行处理
                    if (null != response && response.isSuccess()) {
                        System.out.println("异步发送成功，结果为：" + response.getBody());
                    } else {
                        System.out.println("异步发送失败 code=" + response.getCode() + "msg："
                                           + response.getMsg());
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("异步发送失败");
                }
            }
        });

        // 3.返回同步的ACK响应
        return syncResponseMsg;
    }

}
