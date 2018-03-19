package weixin.p3.oauth2.rule;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.p3.oauth2.def.WeiXinOpenConstants;
import weixin.p3.oauth2.pojo.oauth2.Oauth2CodePojo;
import weixin.p3.oauth2.pojo.templateMsg.TemplateMsgPojo;
import weixin.p3.oauth2.util.OAuth2Util;
import weixin.util.WeiXinConstants;

/**
 * 模板消息接口
 * @author zhangdaihao
 *
 */
@Service
public class RemoteWeixinMethod extends RemoteWeixinMethodBase implements RemoteWeixinMethodI {
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	/**
	 * 注意：productType,name,remark,number与模板中的数据项对应
	 * @param pojo  json数据
	 * @param url   请求地址
	 * @return
	 */
	public Map<String,Object> callWeixinTemplateMsg(String url,TemplateMsgPojo pojo){
		return callWeixinRemoteMethod(url, pojo);
	}


	/**
	 * 调用微信author2.0 通用方法
	 * @param request    前台请求
	 * @param paramsMap  请求页面带的参数
	 * @param accountId    微信公众账号信息ID
	 * @return
	 */
	public String callWeixinAuthor2(HttpServletRequest request,String accountId,String tagetUrl){
		/**通过Oauth2.0获取openid_end**/
		String openId = ResourceUtil.getOpenid(request);
		if(oConvertUtils.isNullOrEmpty(openId)){
				openId = ResourceUtil.getUserOpenId();
		}
		if(StringUtil.isEmpty(openId)){
			WeixinAccountEntity account = weixinAccountService.get(WeixinAccountEntity.class, accountId);
			String code = request.getParameter("code");
			LogUtil.info("code的值="+code);
			//1.判断是否有code值,没有则跳转到授权地址
			if(StringUtil.isEmpty(code)){
				LogUtil.info("targetURL的值="+tagetUrl);
				String redirectURL = OAuth2Util.obtainWeixinOAuth2Url(tagetUrl,  account.getAccountappid(),OAuth2Util.SNSAPI_BASE);
				return "redirect:" + redirectURL;
			}
			// 2.不用用户同意即可获取了code的值
			if (!"authdeny".equals(code)) {
				Map<String,Object> map = new RemoteWeixinMethod().getOauth2AccessToken(new Oauth2CodePojo(account.getAccountappid(), account.getAccountappsecret(), code));
				openId = (String)map.get("openid");
			}
			request.getSession().setAttribute(WeiXinConstants.USER_OPENID, openId);
		}
		
		if(StringUtil.isEmpty(openId)){
			request.setAttribute("msg", "请联系商家，检查微信公众号的配置");
			return "weixin/idea/huodong2/zp/zhuanpanover";
		}
		/**通过Oauth2.0获取openid_end**/
		return null;
	}
	
	
	/**
	 * 调用微信author2.0 通用方法
	 * @param request    前台请求
	 * @param accountId    微信公众账号信息ID
	 * @return
	 */
	public String callWeixinAuthor2ReturnUrl(HttpServletRequest request,String accountId,String tagetUrl){
		/**通过Oauth2.0获取openid_end**/
		String openId = ResourceUtil.getOpenid(request);
		if(oConvertUtils.isNullOrEmpty(openId)){
			openId = ResourceUtil.getUserOpenId();
		}
		if(StringUtil.isEmpty(openId)){
			WeixinAccountEntity account = weixinAccountService.get(WeixinAccountEntity.class, accountId);
			String code = request.getParameter("code");
			LogUtil.info("-----author2.0--------code的值--------------"+code);
			//1.模式一：需要授权页面，则判断是否有code值,没有则跳转到授权地址
			if(StringUtil.isEmpty(code)){
				LogUtil.info("-----------author2.0-------------targetURL的值-------------"+tagetUrl);
				String redirectURL = OAuth2Util.obtainWeixinOAuth2Url(tagetUrl,  account.getAccountappid(),OAuth2Util.SNSAPI_BASE);
				return redirectURL;
			}
			// 2.模式一：用户已经关注微信公众账号，不需要授权页面，即可获取了code的值
			if (!"authdeny".equals(code)) {
				Map<String,Object> map = new RemoteWeixinMethod().getOauth2AccessToken(new Oauth2CodePojo(account.getAccountappid(), account.getAccountappsecret(), code));
				openId = (String)map.get("openid");
			}
			request.getSession().setAttribute(WeiXinConstants.USER_OPENID, openId);
		}
		return null;
	}

	/**
	 * 方法描述:  获取网页授权凭证
	 * 作    者： Administrator
	 * 日    期： 2015年1月12日-下午10:13:49
	 * @param url
	 * @param oauth2CodePojo
	 * @return 
	 * 返回类型： Map<String,Object>
	 */
	public  Map<String,Object> getOauth2AccessToken(Oauth2CodePojo oauth2CodePojo) {
		String requestUrl = WeiXinOpenConstants.WEB_OAUTH_ACCESSTOKEN_URL;
		requestUrl = requestUrl.replace("APPID", oauth2CodePojo.getAppId());
		requestUrl = requestUrl.replace("SECRET", oauth2CodePojo.getAppSecret());
		requestUrl = requestUrl.replace("CODE", oauth2CodePojo.getCode());
		return callWeixinRemoteMethod(requestUrl, oauth2CodePojo);
	}


	public RemoteWeixinMethod() {
		super();
		// TODO Auto-generated constructor stub
	}
}
