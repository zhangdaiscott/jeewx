package com.jeecg.qywx.core.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.core.weixin.model.resp.Article;
import com.jeecg.qywx.core.weixin.model.resp.NewsMessageResp;
import com.jeecg.qywx.core.weixin.model.resp.TextMessageResp;
import com.jeecg.qywx.sucai.entity.QywxNewsitem;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

/**
 * 公众平台通用接口工具类
* 
 * @author zhoujf
 * @date 2016-04-06
 */
public class WeixinUtil {

//    private static final ResourceBundle bundle  = ResourceBundle.getBundle("weixin");
    
   
	
	public static String wrapperTextMessage(QywxTexttemplate textTemplate,String fromUserName,String toUserName){
		String content = textTemplate.getTemplateContent();
		TextMessageResp textMessage = new TextMessageResp();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setContent(content);
        return MessageUtil.textMessageToXml(textMessage);    
	} 
	
	public static String wrapperNewsMessage(List<QywxNewsitem> newsList,String fromUserName,String toUserName,String accountId,String agentid){
		
		List<Article> articleList = new ArrayList<Article>();
		for(QywxNewsitem news:newsList){
			Article article = new Article();
			article.setTitle(news.getTitle());
			article.setPicUrl(QywxResourceUtil.getDomain()+"/"+news.getImagePath());
            article.setUrl(QywxResourceUtil.getDomain()+"/qywx/qywxNewsitem.do?goContent&id="+news.getId());
			articleList.add(article);
		}
		NewsMessageResp newsResp = new NewsMessageResp();
		newsResp.setCreateTime(new Date().getTime());
		newsResp.setFromUserName(toUserName);
		newsResp.setToUserName(fromUserName);
		newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsResp.setArticleCount(newsList.size());
		newsResp.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsResp);
	}
	
}