package com.jeecg.qywx.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jeecg.qywx.core.entity.message.resp.LinkMessageResp;
import com.jeecg.qywx.core.weixin.model.resp.Article;
import com.jeecg.qywx.core.weixin.model.resp.MusicMessageResp;
import com.jeecg.qywx.core.weixin.model.resp.NewsMessageResp;
import com.jeecg.qywx.core.weixin.model.resp.TextMessageResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
	/**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 返回消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 返回消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    
    /**
     * 返回消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    
    
    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 请求消息类型：转发至多客服
     */
    public static final String REQ_MESSAGE_TYPE_CUSTOMERSERVICE = "transfer_customer_service";
    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    
    /**
     * 事件类型：扫描二维码
     */
    public static final String EVENT_TYPE_SCAN="SCAN";
    
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "click";
    /**
     * 事件类型：VIEW(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_VIEW = "view";
    /**
     * 地理位置通知事件
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    
    /**
     * 礼券：审核事件推送
     */
    public static final String EVENT_CARD_PASS_CHECK = "card_pass_check";
    /**
     * 礼券：领取事件推送
     */
    public static final String EVENT_USER_GET_CARD = "user_get_card";
    /**
     * 礼券：删除事件推送
     */
    public static final String EVENT_USER_DEL_CARD = "user_del_card";
    /**
     * 礼券：核销事件推送
     */
    public static final String EVENT_USER_CONSUME_CARD = "user_consume_card";
    /**
     * 礼券：进入会员卡事件推送
     */
    public static final String EVENT_USER_VIEW_CARD = "user_view_card";
    /**
     * 礼券：从卡券进入公众号会话事件推送
     */
    public static final String EVENT_USER_ENTER_SESSION_FROM_CARD = "user_enter_session_from_card";
    
    public static String readStrFromInputStream(HttpServletRequest request){
    	// 从request中取得输入流
    	StringBuffer sbf = new StringBuffer();
        try {
			InputStream inputStream = request.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	         char[] bufferChar = new char[1024];
	         int index = 0;
	         while((index=in.read(bufferChar))!=-1){
	        	 sbf.append(bufferChar,0,index);
	         }
	         inputStream.close();
	         inputStream =null;
	         return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }

    /**
     * 解析微信发来的请求（XML）
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(String resultXml) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        
        Document document = reader.read(new ByteArrayInputStream(resultXml.getBytes("UTF-8")));
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList){
//        	System.out.println("....name..."+e.getName());
        	 map.put(e.getName(), e.getText());
        }
            
        return map;
    }
    
    /**
     * 解析微信发来的请求（XML）
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
                map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }
    /**
     * 文本消息对象转换成xml
     * 
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessageResp textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 音乐消息对象转换成xml
     * 
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String musicMessageToXml(MusicMessageResp musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     * 
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessageResp newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }
    
    /**
     * 链接消息对象转换成xml
     * 
     * @param newsMessage 链接消息对象
     * @return xml
     */
    public static String linkMessageToXml(LinkMessageResp linkMessage) {
        xstream.alias("xml", linkMessage.getClass());
        return xstream.toXML(linkMessage);
    }

    /**
     * 消息对象转换成xml
     * 
     * @param 消息对象
     * @return xml
     */
    public static String messageToXml(Object obj) {
        xstream.alias("xml", obj.getClass());
        return xstream.toXML(obj);
    }
    
    /**
     * 扩展xstream，使其支持CDATA块
     * 
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                    } else {
                            writer.write(text);
                    }
                }
            };
        }
    });
}
