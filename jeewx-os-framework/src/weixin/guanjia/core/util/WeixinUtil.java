package weixin.guanjia.core.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.jeecgframework.web.system.service.SystemService;

import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.entity.model.AccessTokenYw;

/**
 * 公众平台通用接口工具类
* 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //客服接口地址
    public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    //
//    private static final ResourceBundle bundle  = ResourceBundle.getBundle("weixin");

    /**
     * 发起https请求并获取结果
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();

                URL url = new URL(requestUrl);
                HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
                httpUrlConn.setSSLSocketFactory(ssf);

                httpUrlConn.setDoOutput(true);
                httpUrlConn.setDoInput(true);
                httpUrlConn.setUseCaches(false);
                // 设置请求方式（GET/POST）
                httpUrlConn.setRequestMethod(requestMethod);

                if ("GET".equalsIgnoreCase(requestMethod))
                        httpUrlConn.connect();

                // 当有数据需要提交时
                if (null != outputStr) {
                        OutputStream outputStream = httpUrlConn.getOutputStream();
                        // 注意编码格式，防止中文乱码
                        outputStream.write(outputStr.getBytes("UTF-8"));
                        outputStream.close();
                }

                // 将返回的输入流转换成字符串
                InputStream inputStream = httpUrlConn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                        buffer.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                // 释放资源
                inputStream.close();
                inputStream = null;
                httpUrlConn.disconnect();
                jsonObject = JSONObject.fromObject(buffer.toString());
                //jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
        	org.jeecgframework.core.util.LogUtil.info("Weixin server connection timed out.");
        } catch (Exception e) {
        	org.jeecgframework.core.util.LogUtil.info("https request error:{}"+e.getMessage());
        }
        return jsonObject;
    }
    
    /**
     * 获取access_token
     * @param appid 凭证
    * @param appsecret 密钥
    * @return
     */
    public static AccessToken getAccessToken(SystemService systemService,String appid,String appsecret) {
    	// 第三方用户唯一凭证
//        String appid = bundle.getString("appId");
//        // 第三方用户唯一凭证密钥
//        String appsecret = bundle.getString("appSecret");
        
    	AccessTokenYw accessTocken = getRealAccessToken(systemService);
    	
    	if(accessTocken!=null){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		java.util.Date end = new java.util.Date();
    		java.util.Date start = new java.util.Date(accessTocken.getAddTime().getTime());
        	if(end.getTime()-accessTocken.getAddTime().getTime()>accessTocken.getExpires_in()*1000){
        		 AccessToken accessToken = null;
                 String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
                 JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
                 // 如果请求成功
                 if (null != jsonObject) {
                     try {
                         accessToken = new AccessToken();
                         accessToken.setToken(jsonObject.getString("access_token"));
                         accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                         //凭证过期更新凭证
                         AccessTokenYw atyw = new AccessTokenYw();
                         atyw.setId(accessTocken.getId());
                         atyw.setExpires_in(jsonObject.getInt("expires_in"));
                         atyw.setAccess_token(jsonObject.getString("access_token"));
                         updateAccessToken(atyw,systemService);
                     } catch (Exception e) {
                         accessToken = null;
                         // 获取token失败
                         String wrongMessage = "获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+jsonObject.getString("errmsg");
                         org.jeecgframework.core.util.LogUtil.info(wrongMessage);
                     }
                 }
                 return accessToken;
        	}else{
        		
        		 AccessToken  accessToken = new AccessToken();
                 accessToken.setToken(accessTocken.getAccess_token());
                 accessToken.setExpiresIn(accessTocken.getExpires_in());
        		return accessToken;
        	}
    	}else{
    		
    		 AccessToken accessToken = null;
             String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
             JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
             // 如果请求成功
             if (null != jsonObject) {
                 try {
                     accessToken = new AccessToken();
                     accessToken.setToken(jsonObject.getString("access_token"));
                     accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                     
                     AccessTokenYw atyw = new AccessTokenYw();
                     atyw.setExpires_in(jsonObject.getInt("expires_in"));
                     atyw.setAccess_token(jsonObject.getString("access_token"));
                     saveAccessToken(atyw,systemService);
                     
                 } catch (Exception e) {
                     accessToken = null;
                     // 获取token失败
                     String wrongMessage = "获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+jsonObject.getString("errmsg");
                     org.jeecgframework.core.util.LogUtil.info(wrongMessage);
                 }
             }
             return accessToken;
    	}
    }
    
  
    /**
     * 从数据库中读取凭证
     * @return
     */
    public static AccessTokenYw getRealAccessToken(SystemService systemService){
        List<AccessTokenYw> accessTockenList = systemService.findByQueryString("from AccessTokenYw");
 		return accessTockenList.get(0);
    }
    
    /**
     * 保存凭证
     * @return
     */
    public static void saveAccessToken( AccessTokenYw accessTocken,SystemService systemService){
    	systemService.save(accessTocken);
    }
    
    /**
     * 更新凭证
     * @return
     */
    public static void updateAccessToken( AccessTokenYw accessTocken,SystemService systemService){
    	String sql = "update accesstoken set access_token='"+accessTocken.getAccess_token()+"',expires_ib="+accessTocken.getExpires_in()+",addtime=now() where id='"+accessTocken.getId()+"'";
    	systemService.updateBySqlString(sql);
    }
    
  
    /** 
     * 编码 
     * @param bstr 
     * @return String 
     */  
    public static String encode(byte[] bstr){  
    	return new sun.misc.BASE64Encoder().encode(bstr);  
    }  
  
    /** 
     * 解码 
     * @param str 
     * @return string 
     */  
    public static byte[] decode(String str){ 
    	
	    byte[] bt = null;  
	    try {  
	        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	        bt = decoder.decodeBuffer( str );  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
        return bt;  
        
    }  
    
}