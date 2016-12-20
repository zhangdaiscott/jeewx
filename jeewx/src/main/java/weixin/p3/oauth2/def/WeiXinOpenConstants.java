package weixin.p3.oauth2.def;

public class WeiXinOpenConstants {
	//素材状态，共享和非共享
	public static final String SUCAI_SHARE_STATUS = "Y";
	public static final String SUCAI_UNSHARE_STATUS = "N";
	
	// jsapi调用接口临时凭证的接口地址（GET） 限200（次/天）
 	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	/**微信网页授权获取CODE**/
    public static String WEB_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	 /**微信网页授权获取网页accesstoken和OPENID**/
    public static String WEB_OAUTH_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"; 
}