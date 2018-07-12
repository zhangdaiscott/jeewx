package com.jeecg.qywx.core.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeecg.qywx.account.dao.QywxAgentDao;
import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.core.service.SignForWeixinService;
import com.jeecg.qywx.core.service.WeixinCoreService;
import com.jeecg.qywx.core.util.MessageUtil;
import com.jeecg.qywx.core.util.WXBizMsgCrypt;

/**
 * 企业微信客户端，请求处理核心类
 * @author zhoujf
 *
 */
@Controller
@RequestMapping("/weixinCoreController")
public class WeixinCoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeixinCoreController.class);
	@Autowired
	private SignForWeixinService signForWeixinService;
	@Autowired
	private WeixinCoreService weixinCoreService;
	@Autowired
	private QywxAgentDao qywxAgentDao;
	
	@RequestMapping(params="wechat", method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "msg_signature") String msg_signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,
			@RequestParam(value = "corpid") String corpid,
			@RequestParam(value = "appid") Integer appid) {
		logger.info(request, "wechatGet param：msg_signature:{},timestamp:{},nonce:{},echostr:{},corpid:{},appid:{}", new Object[]{msg_signature,timestamp,nonce,echostr,corpid,appid});
        PrintWriter out;
		try {
			out = response.getWriter();
			//通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			echostr = this.signForWeixinService.checkSignature(msg_signature, timestamp, nonce,echostr,corpid,appid);
			out.print(echostr);
	        out.close();
	        out = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(params="wechat", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "msg_signature") String msg_signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce) throws Exception {
		try {
			String sReqData = MessageUtil.readStrFromInputStream(request);
			Map<String, String> dealMap = MessageUtil.parseXml(sReqData);
			String appid = dealMap.get("AgentID");
			String corpid = dealMap.get("ToUserName");
			String sEncryptMsg = "";
			QywxAgent appInfo = qywxAgentDao.getQywxAgentByCorpidAndAppid(corpid, appid);
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(appInfo.getToken(),appInfo.getEncodingAESKey(),corpid);
			String sMsg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, sReqData);
			logger.info("[WECHAT]", "wechat msg:\n{}", new Object[]{sMsg});
			String respMessage = weixinCoreService.processRequest(sMsg);
			logger.info("[WECHAT]", "wechat resmsg:\n{}", new Object[]{respMessage});
			sEncryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
			logger.info("[WECHAT]", "wechat EncryptMsg:\n{}", new Object[]{sEncryptMsg});
			PrintWriter out = response.getWriter();
			out.print(sEncryptMsg);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
