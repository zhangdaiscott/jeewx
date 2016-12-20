package weixin.p3.oauth2.pojo.oauth2;

/**
 * 类 名 称： Oauth2CodePojo
 * 类 描 述： 授权请求的封装
 * 第二步：通过code换取网页授权access_token 
 * 参考链接：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
 * 创 建 人： Administrator
 * 创建时间： 2015年1月12日 下午9:44:35
 *
 * 修 改 人： Administrator
 * 操作时间： 2015年1月12日 下午9:44:35
 * 操作原因： 
 *
 */
public class Oauth2CodePojo {
	private String appId;
	private String appSecret;
	private String code;
	private String grant_type;
	
	public Oauth2CodePojo(String appId, String appSecret, String code) {
		super();
		this.appId = appId;
		this.appSecret = appSecret;
		this.code = code;
	}
	
	public Oauth2CodePojo() {
		super();
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
}
