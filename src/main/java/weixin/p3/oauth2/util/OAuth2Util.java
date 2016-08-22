package weixin.p3.oauth2.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import jodd.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import weixin.p3.oauth2.def.WeiXinOpenConstants;

public class OAuth2Util {
	
	//弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
	public static final String SNSAPI_USERINFO = "snsapi_userinfo";
	//不弹出授权页面，直接跳转，只能获取用户openid
	public static final String SNSAPI_BASE = "snsapi_base";

	/**
	 * 方法描述: 获取redirect的URL地址
	 * 作    者： Administrator
	 * 日    期： 2015年1月11日-下午12:36:32
	 * @param targetUrl
	 * @param appid
	 * @return
	 * @throws UnsupportedEncodingException 
	 * 返回类型： String
	 */
	public static String obtainWeixinOAuth2Url(String targetUrl, String appid,String scope) {
		String shareurl = "";
		String encodeTargetURL = "";
		try {
			encodeTargetURL = URLEncoder.encode(targetUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//这种一般不做处理
			e.printStackTrace();
		}
		shareurl = WeiXinOpenConstants.WEB_OAUTH_URL.replace("APPID", appid).replace("REDIRECT_URI", encodeTargetURL).replace("SCOPE", scope);
		return shareurl;
	}
	
	
	
	/**
	 * 方法描述: 获取URL值
	 * 作    者： Administrator
	 * 日    期： 2015年1月13日-下午10:17:01
	 * @param clazz
	 * @param currentMethodName
	 * @param paramsMap
	 * @return 
	 * 返回类型： String
	 */
	public static String obtainTargetUrl(Class clazz ,String currentMethodName,Map<String,String> paramsMap) {
		if(StringUtil.isEmpty(currentMethodName ) || clazz == null){
			return null;
		}
		StringBuffer targetURL =  new StringBuffer();
		String suffixStr = ".do?";
		targetURL.append(ResourceBundle.getBundle("sysConfig").getString("domain"));
		RequestMapping annotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
		if(annotation != null){
			targetURL.append(annotation.value()[0]).append(suffixStr);
		}
		Method[] methodArray = clazz.getMethods();
		for (Method tempMethod : methodArray) {
			if(currentMethodName.equals(tempMethod.getName())){
				targetURL.append(tempMethod.getAnnotation(RequestMapping.class).params()[0]);
				break;
			}
		}
		if(paramsMap != null && paramsMap.size() > 0){
			Set<String> keys = paramsMap.keySet();
			for (String key : keys) {
				targetURL.append("&").append(key).append("=").append(paramsMap.get(key));
			}
		}
		return targetURL.toString();
	}

	/**
	 * 方法描述:  重载不带参数的方法
	 * 作    者： Administrator
	 * 日    期： 2015年1月13日-下午10:16:53
	 * @param clazz
	 * @param currentMethodName
	 * @return 
	 * 返回类型： String
	 */
	public static String obtainTargetUrl(Class clazz ,String currentMethodName) {
		return obtainTargetUrl(clazz, currentMethodName, null);
	}
}
