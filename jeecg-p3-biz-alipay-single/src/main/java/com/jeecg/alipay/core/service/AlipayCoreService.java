package com.jeecg.alipay.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.response.AlipayMobilePublicMessageCustomSendResponse;
import com.jeecg.alipay.account.dao.AlipayMenuDao;
import com.jeecg.alipay.account.entity.AlipayMenu;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.base.JwSendMessageAPI;
import com.jeecg.alipay.base.dao.AlipayReceivetextDao;
import com.jeecg.alipay.base.entity.AlipayReceivetext;
import com.jeecg.alipay.core.contants.AlipayServiceEventConstants;
import com.jeecg.alipay.core.contants.AlipayServiceNameConstants;
import com.jeecg.alipay.core.exception.MyException;
import com.jeecg.alipay.core.excutor.InAlipayVerifyExecutor;
import com.jeecg.alipay.core.util.AlipayMsgBuildUtil;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.sucai.dao.AlipayNewsitemDao;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.dao.AlipayTexttemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;
import com.jeecg.alipay.util.SystemUtil;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


@Service("alipayCoreService")
public class AlipayCoreService {
	@Autowired
	public AlipayKeyWordDealInterfaceService alipayKeyWordDealInterfaceService;
	@Autowired
	private AlipayAccountService alipayAccountService;
	@Autowired
	private AlipayAutoResponseDefaultServiceI autoResponseDefaultService;
	@Autowired
	private AlipayReceivetextDao alipayReceivetextDao;
	@Autowired
	private AlipayTexttemplateDao alipayTexttemplateDao;
	/** 线程池 */
	private static ExecutorService executors = Executors.newSingleThreadExecutor();
	@Autowired
	private AlipayMenuDao alipayMenuDao;
	@Autowired
	private AlipayNewstemplateDao alipayNewstemplateDao;
	@Autowired
	private AlipayNewsitemDao alipayNewsitemDao;

	/**
	 * 
	 * @param params
	 * @return 返回加密验证串
	 * @throws MyException
	 */
	public String excute(Map<String, String> params) throws MyException {
		// 获取服务信息
		String service = params.get("service");
		if (StringUtils.isEmpty(service)) {
			throw new MyException("无法取得服务名");
		}
		// 获取内容信息
		String bizContent = params.get("biz_content");
		if (StringUtils.isEmpty(bizContent)) {
			throw new MyException("无法取得业务内容信息");
		}

		// 将XML转化成json对象
		JSONObject bizContentJson = (JSONObject) new XMLSerializer().read(bizContent);

		// 1.获取消息类型信息
		String msgType = bizContentJson.getString("MsgType");
		if (StringUtils.isEmpty(msgType)) {
			throw new MyException("无法取得消息类型");
		}

		// 2.根据消息类型(msgType)进行执行器的分类转发
		// 2.1 纯文本聊天类型
		if ("text".equals(msgType)) {
			//文本，按关键字消息进行回复	
			return doSendKeyMessage(bizContentJson);

			// 2.2 事件类型
		} else if ("event".equals(msgType)) {

			return doEventExcute(service, bizContentJson);
		} else {

			// 2.3 后续支付宝还会新增其他类型，因此默认返回ack应答
			return doSendDefaultMessage(bizContentJson);
		}

	}
	
	/**
	 * 关键字消息回复
	 * @param bizContent
	 * @return
	 */
	private String doSendKeyMessage(JSONObject bizContent) { 
		// 取得发起请求的支付宝账号id
		final String fromUserId = bizContent.getString("FromUserId");
		final String content = bizContent.getJSONObject("Text").getString("Content");
		//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		String responseMsg = alipayKeyWordDealInterfaceService.dealKeyMessage(content, alipayAccountService.getAccount().getId(), fromUserId);
		//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		return sendMsg(responseMsg, bizContent,content,"文本消息");
	}
	
	/**
	 * 默认返回私钥
	 * @param bizContent
	 * @return
	 */
	private String doSendDefaultMessage(JSONObject bizContent) {
		final String fromUserId = bizContent.getString("FromUserId");
		String syncResponseMsg = AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
		return syncResponseMsg;
	}

	/**
	 * 事件执行
	 * @param service
	 * @param bizContentJson
	 * @return
	 * @throws MyException
	 */
	private String doEventExcute(String service, JSONObject bizContentJson) throws MyException {

		// 1. 获取事件类型
		String eventType = bizContentJson.getString("EventType");

		if (StringUtils.isEmpty(eventType)) {
			throw new MyException("无法取得事件类型");
		}

		// 2.根据事件类型再次区分服务类型
		// 2.1 激活验证开发者模式
		if (AlipayServiceNameConstants.ALIPAY_CHECK_SERVICE.equals(service)&& eventType.equals(AlipayServiceEventConstants.VERIFYGW_EVENT)) {
			return new InAlipayVerifyExecutor().execute();
			// 2.2 其他消息通知类
		} else if (AlipayServiceNameConstants.ALIPAY_PUBLIC_MESSAGE_NOTIFY.equals(service)) {
			return getMsgNotifyExecutor(eventType, bizContentJson);
			// 2.3 对于后续支付宝可能新增的类型，统一默认返回AKC响应
		} else {
			return doSendDefaultMessage(bizContentJson);
		}
	}
	
	/**
	 * 其他消息业务
	 * @param eventType
	 * @param bizContentJson
	 * @return
	 * @throws MyException
	 */
	private String getMsgNotifyExecutor(String eventType, JSONObject bizContentJson) throws MyException {
		if (eventType.equals(AlipayServiceEventConstants.FOLLOW_EVENT)) {

			// 服务窗关注事件
			return  alipayFollowExecutor(bizContentJson);

		} else if (eventType.equals(AlipayServiceEventConstants.UNFOLLOW_EVENT)) {

			// 服务窗取消关注事件
			return alipayFollowExecutor(bizContentJson);

			// 根据actionParam进行执行器的转发
		} else if (eventType.equals(AlipayServiceEventConstants.CLICK_EVENT)) {

			// 点击事件
			return clickEventExecutor(bizContentJson);

		} else if (eventType.equals(AlipayServiceEventConstants.ENTER_EVENT)) {

			// 进入事件
			return enterEventTypeExecutor(bizContentJson);

		} else {
			// 对于后续支付宝可能新增的类型，统一默认返回AKC响应
			return doSendDefaultMessage(bizContentJson);
		}

	}
	/**
	 * 菜单点击事件
	 * @param bizContentJson
	 * @return
	 */
	private String clickEventExecutor(JSONObject bizContentJson) {
		// 取得发起请求的支付宝账号id
		final String fromUserId = bizContentJson.getString("FromUserId");
		final String content = bizContentJson.getString("ActionParam");
		AlipayMenu menu = alipayMenuDao.getMenuByMenuKey(content);
		String lx = menu.getMsgType();
		String tempalteId = menu.getTemplateId();
		String responseMessage = "";
		if("text".equals(lx)){
			AlipayTexttemplate textTemplate = alipayTexttemplateDao.get(tempalteId);
			if(textTemplate!=null){
				responseMessage =com.alibaba.fastjson.JSONObject.toJSONString(AlipayUtil.wrapperTextMessage(textTemplate,fromUserId));
			}
		}else if("news".equals(lx)){
			AlipayNewstemplate newsTemplate = alipayNewstemplateDao.get(tempalteId);
			if(newsTemplate!=null){
				List<AlipayNewsitem> newsList = alipayNewsitemDao.getAlipayNewsitemByTemplateId(newsTemplate.getId());
				if(newsList!=null&&newsList.size()>0){
					responseMessage =com.alibaba.fastjson.JSONObject.toJSONString(AlipayUtil.wrapperNewsMessage(newsList, fromUserId));
				}
			}
		}
		return sendMsg(responseMessage, bizContentJson,menu.getMenuName(),"菜单点击");
	}

	private String enterEventTypeExecutor(JSONObject bizContentJson) {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * 关注事件执行
	 * @param bizContentJson
	 * @return
	 */
	private String alipayFollowExecutor(JSONObject bizContentJson) {
		String toUserid = bizContentJson.getString("FromUserId");
		String responseMsg = autoResponseDefaultService.getWorkDefaultResponse(toUserid, SystemUtil.getOnlieAlipayAccountId());
		return sendMsg(responseMsg, bizContentJson, "用户关注", "用户关注");
	}
	/**
	 * 消息发送方法
	 * @param requestMsg
	 * @param bizContent
	 * @param requestMsg 收到的消息
	 * @param type 消息类型:菜单点击，文本消息，用户关注等
	 * @return
	 */
	private String sendMsg(final String responseMsg,JSONObject bizContent,final String requestMsg,String type){
		
		 // 取得发起请求的支付宝账号id
		final String fromUserId = bizContent.getString("FromUserId");
		// 1. 首先同步构建ACK响应
		String syncResponseMsg = AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
		// 2. 异步发送消息
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AlipayMobilePublicMessageCustomSendResponse response =JwSendMessageAPI.messageCustomSend(null, responseMsg,alipayAccountService.getAlipayConfig());
					// 2.3 商户根据响应结果处理结果
					// 这里只是简单的打印，请商户根据实际情况自行进行处理
					AlipayReceivetext rt = new AlipayReceivetext();
			    	String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			    	rt.setId(randomSeed);
					rt.setAccountid(SystemUtil.getOnlieAlipayAccountId());
					rt.setContent(requestMsg);
					rt.setCreatetime(new Date());
					rt.setFromusername(fromUserId);
					rt.setTousername(alipayAccountService.getAccount().getAccontName());
					rt.setMsgtype("文本消息");
					if (null != response && response.isSuccess()) {
						rt.setResponse("回复成功");
						System.out.println("异步发送成功，结果为：" + response.getBody());
					} else {
						rt.setResponse("回复失败");
						System.out.println("异步发送失败 code=" + response.getCode() + "msg：" + response.getMsg());
					}
					alipayReceivetextDao.insert(rt);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("异步发送失败");
				}
			}
		});
		return syncResponseMsg;
	}
	
	public String sendMsg(final String responseMsg,String fromUserId){
		//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		// 1. 首先同步构建ACK响应
		String syncResponseMsg = AlipayMsgBuildUtil.buildBaseAckMsg(fromUserId,alipayAccountService.getAlipayConfig());
		//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		// 2. 异步发送消息
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
					AlipayMobilePublicMessageCustomSendResponse response =JwSendMessageAPI.messageCustomSend(null, responseMsg,alipayAccountService.getAlipayConfig());
					//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
					if (null != response && response.isSuccess()) {
						System.out.println("异步发送成功，结果为：" + response.getBody());
					} else {
						System.out.println("异步发送失败 code=" + response.getCode() + "msg：" + response.getMsg());
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("异步发送失败");
				}
			}
		});
		return syncResponseMsg;
	}
}
