package weixin.guanjia.message.service;

import net.sf.json.JSONObject;

import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;

/**
 * 客服消息service
 * @author 付明星
 *
 */
@Service("customerMessageService")
public class CustomerMessageService {
	
	//客服接口地址
    public String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
    @Autowired
	private WeixinAccountServiceI weixinAccountService;

    public String sendMessage(String json){
    	// 调用接口获取access_token
        String accessTocken = weixinAccountService.getAccessToken();
        if(StringUtil.isNotEmpty(accessTocken)){
        	System.out.println("....token...."+accessTocken);
        	String url = send_message_url.replace("ACCESS_TOKEN",accessTocken);
        	JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json);
        	System.out.println("...jsonObject..."+jsonObject.toString());
        	return jsonObject.toString();
        }
        return null;
    }
}
