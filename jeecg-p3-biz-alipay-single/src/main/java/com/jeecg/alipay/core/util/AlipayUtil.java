package com.jeecg.alipay.core.util;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.jeecg.alipay.api.base.vo.SendMessageImageTextMoreVo.SendMessageImageTextMore;
import com.jeecg.alipay.api.base.vo.SendMessageImageTextOneVo.Articles;
import com.jeecg.alipay.api.base.vo.SendMessageImageTextOneVo.SendMessageImageText;
import com.jeecg.alipay.api.base.vo.SendMessageTextMoreVo.ConTextMore;
import com.jeecg.alipay.api.base.vo.SendMessageTextMoreVo.SendMessageTextMore;
import com.jeecg.alipay.api.base.vo.SendMessageTextOneVo.ConTent;
import com.jeecg.alipay.api.base.vo.SendMessageTextOneVo.SendMessage;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;
import com.jeecg.alipay.util.AlipayResourceUtil;

/**
 * 公众平台通用接口工具类
* 
 * @author zhoujf
 * @date 2016-04-06
 */
public class AlipayUtil {
	public static final String REQ_MESSAGE_TYPE_TEXT ="text";
	/**
	 * 构建群发文本消息,按模版
	 * @param textTemplate
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static SendMessageTextMore wrapperGroupTextMessage(AlipayTexttemplate textTemplate){
		String content = textTemplate.getTemplateContent();
		SendMessageTextMore textMessage = new SendMessageTextMore();
        textMessage.setMsgType("text");
        ConTextMore text =new ConTextMore();
        text.setContent(content);
        textMessage.setText(text);
        return textMessage;    
	} 
	/**
	 * 按内容，构建群发文本消息
	 * @param content
	 * @return
	 */
	public static SendMessageTextMore wrapperGroupTextMessage(String content){
		SendMessageTextMore textMessage = new SendMessageTextMore();
        textMessage.setMsgType("text");
        ConTextMore text =new ConTextMore();
        text.setContent(content);
        textMessage.setText(text);
        return textMessage;    
	} 
	/**
	 * 构建群发图文消息
	 * @param newsList
	 * @return
	 */
	public static SendMessageImageTextMore wrapperGroupNewsMessage(List<AlipayNewsitem> newsList){
		
		List<Articles> articleList = new ArrayList<Articles>();
		for(AlipayNewsitem news:newsList){
			Articles article = new Articles();
			article.setDesc(news.getDescription());
			article.setActionName("立即查看");
			article.setTitle(news.getTitle());
			article.setImageUrl(AlipayResourceUtil.getDomain()+"/"+news.getImagePath());
            article.setUrl(AlipayResourceUtil.getDomain()+"/alipay/alipayNewsitem.do?goContent&id="+news.getId());
			articleList.add(article);
		}
		SendMessageImageTextMore newsResp = new SendMessageImageTextMore();
		newsResp.setMsgType("image-text");
		newsResp.setArticles(articleList);
		return newsResp;
	}
	/**
	 * 构建单条文本消息
	 * @param textTemplate
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static SendMessage wrapperTextMessage(AlipayTexttemplate textTemplate,String toUserName){
		String content = textTemplate.getTemplateContent();
		SendMessage textMessage = new SendMessage();
        textMessage.setToUserId(toUserName);
        textMessage.setMsgType("text");
        ConTent text =new ConTent();
        text.setContent(content);
        textMessage.setText(text);
        return textMessage;    
	} 
	
	/**
	 * 发送图文消息
	 * @param newsList
	 * @param fromUserName
	 * @param toUserName
	 * @param accountId
	 * @return
	 */
	public static SendMessageImageText wrapperNewsMessage(List<AlipayNewsitem> newsList,String toUserName){
		
		List<Articles> articleList = new ArrayList<Articles>();
		for(AlipayNewsitem news:newsList){
			Articles article = new Articles();
			article.setDesc(news.getDescription());
			article.setActionName("立即查看");
			article.setTitle(news.getTitle());
			article.setImageUrl(AlipayResourceUtil.getDomain()+"/"+news.getImagePath());
            article.setUrl(AlipayResourceUtil.getDomain()+"/alipay/alipayNewsitem.do?goContent&id="+news.getId());
			articleList.add(article);
		}
		SendMessageImageText newsResp = new SendMessageImageText();
		newsResp.setToUserId(toUserName);
		newsResp.setMsgType("image-text");
		newsResp.setArticles(articleList);
		return newsResp;
	}
	//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
	  /**
     * 构造基础的响应消息
     * 
     * @return
     */
    public static String buildBaseAckMsg(String fromUserId,AlipayConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append("<XML>");
        sb.append("<ToUserId><![CDATA[" + fromUserId + "]]></ToUserId>");
        sb.append("<AppId><![CDATA[" + config.getAppid()+ "]]></AppId>");
        sb.append("<CreateTime>" + Calendar.getInstance().getTimeInMillis() + "</CreateTime>");
        sb.append("<MsgType><![CDATA[ack]]></MsgType>");
        sb.append("</XML>");
        return sb.toString();
    }
  //update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
    /**
     * 构造免登图文消息
     * 
     * @param fromUserId
     * @return
     */
    public static String buildImgTextLoginAuthMsg(String fromUserId) {

        StringBuilder sb = new StringBuilder();

        //免登连接地址，开发者需根据部署服务修改相应服务ip地址
        String url = "http://10.15.132.68:8080/AlipayFuwuDemo/loginAuth.html";

        //构建json格式的单发免登图文消息体     authType 等于 "loginAuth"表示免登消息 ： 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{'articles':[{'actionName':'立即查看','desc':'这是图文内容','imageUrl':'http://pic.alipayobjects.com/e/201311/1PaQ27Go6H_src.jpg','title':'这是标题','url':'"
                  + url
                  + "', 'authType':'loginAuth'}],'msgType':'image-text', 'toUserId':'"
                  + fromUserId + "'}");

        return sb.toString();
    }

	
	
}