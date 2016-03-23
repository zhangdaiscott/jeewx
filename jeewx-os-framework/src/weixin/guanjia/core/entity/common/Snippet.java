package weixin.guanjia.core.entity.common;

import org.apache.log4j.Logger;

public class Snippet {
	private static Logger log = Logger.getLogger(Snippet.class);
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.guanjia.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

}
