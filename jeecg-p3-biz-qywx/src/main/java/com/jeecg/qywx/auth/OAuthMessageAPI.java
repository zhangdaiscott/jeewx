package com.jeecg.qywx.auth;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.qywx.api.core.util.HttpUtil;
import com.jeecg.qywx.api.message.JwMessageAPI;
import com.jeecg.qywx.api.message.vo.Text;

public class OAuthMessageAPI {
private static final Logger logger = LoggerFactory.getLogger(OAuthMessageAPI.class);
private static String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
private static String code_receive_url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";


public static int authsend(HttpServletRequest request,HttpServletResponse response,String appid,String redirecturl ,String resposetype,String scope,String state,String wechatredirect) throws IOException{ 
	    //首先判断code是否为空，如果为空访问第三方认证
	  int result = 0;  
	    String url = code_receive_url.replace("CORPID", appid).replace("REDIRECT_URI", redirecturl).replace("code", resposetype).replace("SCOPE", scope).replace("STATE",state).replace("wechat_redirect", wechatredirect);
		JSONObject jsonObject = HttpUtil.sendPost(url);
		  logger.info("[CREATEMENU]", "sendMessage response:{}", new Object[]{jsonObject.toJSONString()});
		    if (null != jsonObject) {  
		    	int errcode = jsonObject.getIntValue("errcode");
		        result = errcode;
		    }
			return result;  
	}
//拿到code之后根据code获取成员变量信息。
public static int receivecode(String  code, String accessToken) {  
	logger.info("[CREATEMENU]", "receivecode param:accessToken:{},code:{}", new Object[]{accessToken,code});
    int result = 0;
    // 拼装发送信息的url  
    String url = code_receive_url.replace("ACCESS_TOKEN", accessToken).replace("CODE", code);  
    // 调用接口发送信息 
    JSONObject jsonObject = HttpUtil.sendPost(url);
    logger.info("[CREATEMENU]", "sendMessage response:{}", new Object[]{jsonObject.toJSONString()});
    if (null != jsonObject) {  
    	int errcode = jsonObject.getIntValue("errcode");
        result = errcode;
    }  
    return result;  
}  


}
