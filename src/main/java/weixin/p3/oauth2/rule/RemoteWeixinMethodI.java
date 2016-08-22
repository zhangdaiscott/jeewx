package weixin.p3.oauth2.rule;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import weixin.p3.oauth2.pojo.oauth2.Oauth2CodePojo;
import weixin.p3.oauth2.pojo.templateMsg.TemplateMsgPojo;



/**
 * 微信官方接口
 * 
 * @1.客服转发接口
 * @2.Autho2.0接口
 * @3.消息模板接口
 * @4.群发消息接口
 * @5.地理位置接口 
 * @author zhangdaihao
 *
 */
public interface RemoteWeixinMethodI {
	/**
	 * 调用微信消息模板
	 * @param url
	 * @param pojo
	 * @return
	 */
	public Map<String,Object> callWeixinTemplateMsg(String url,TemplateMsgPojo pojo);
	
	
	/**
	 * 方法描述:  获取授权信息
	 * 作    者： Administrator
	 * 日    期： 2015年1月12日-下午11:27:16
	 * @param oauth2CodePojo
	 * @return 
	 * 返回类型： Map<String,Object>
	 */
	public  Map<String,Object> getOauth2AccessToken(Oauth2CodePojo oauth2CodePojo);
	
	
	/**
	 * 调用微信author2.0 通用方法
	 * @param request    前台请求
	 * @param paramsMap  请求页面带的参数
	 * @param account    微信公众账号信息
	 * @return
	 */
	public String callWeixinAuthor2(HttpServletRequest request,String accountId,String tagetUrl);
	
	//获取用户地理位置
	//高级群发接口
	//将消息转发到多客服
	
	
}
