package weixin.p3.oauth2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.util.DateUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 捷微插件，对外接口
 * 
 */
@Controller
@RequestMapping("/openDataController")
public class OpenDataController {

	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	/**
	 * 获取微信公众账号信息
	 * @param weixinId : 微信原始ID
	 * 
	 * @return
	 */
	@RequestMapping(params = "getWeixinToken")
	public void getWeixinToken(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		String weixinId = request.getParameter("weixinId");
		if (oConvertUtils.isEmpty(weixinId)) {
			map.put("success", "false");
			map.put("error", "微信ID为空");
		} else {
			WeixinAccountEntity po = weixinAccountService.getWeixinAccountByWeixinOldId(weixinId);
			if (po != null) {
				// [1] 获取Token
				map.put("success", "true");
				map.put("accountname", po.getAccountname());
				map.put("accountaccesstoken", po.getAccountaccesstoken());
				if (po.getAddtoekntime() != null) {
					java.util.Date toekntime = new java.util.Date(po.getAddtoekntime().getTime());
					map.put("tokentime", DateUtils.date2Str(toekntime, DateUtils.datetimeFormat));
				}
				// [2] jssdk开发 - jsapi凭证
				map.put("jsapiticket", po.getJsapiticket());
				if (po.getJsapitickettime() != null) {
					java.util.Date jsapiticket_time = new java.util.Date(po.getJsapitickettime().getTime());
					map.put("jsapitickettime", DateUtils.date2Str(jsapiticket_time, DateUtils.datetimeFormat));
				}
				// [3] 微信卡券JS API的临时票据
				map.put("apiticket", po.getApiticket());
				if (po.getApiticketttime() != null) {
					java.util.Date apiticket_time = new java.util.Date(po.getApiticketttime().getTime());
					map.put("apiticketttime", DateUtils.date2Str(apiticket_time, DateUtils.datetimeFormat));
				}
			} else {
				map.put("success", "false");
				map.put("error", "微信ID无效");
			}
		}
		try {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter pw = response.getWriter();
			pw.write(JSONObject.toJSONString(map));
			pw.flush();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}


	/**
	 * [接口：获取微信分享签名]
	 * @param weixinId : 微信原始ID
	 * @param nonceStr : nonceStr
	 * @param timestamp : timestamp
	 * @param url : url
	 * @return
	 */
	@RequestMapping(params = "getSignature")
	public void getSignature(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String weixinId = request.getParameter("weixinId");
		String nonceStr = request.getParameter("nonceStr");
		String timestamp = request.getParameter("timestamp");
		String url = request.getParameter("url");// .replace("@", "&");

		// 解码
		url = URLDecoder.decode(url, "UTF-8");
		String signature = "";
		Map<String, String> returnmap = new HashMap<String, String>();

		WeixinAccountEntity weixinAccountEntity = weixinAccountService.getWeixinAccountByWeixinOldId(weixinId);
		if (weixinAccountEntity == null) {
			returnmap.put("success", "false");
			returnmap.put("error", "微信原始ID无效");
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.write(JSONObject.toJSONString(returnmap));
				pw.flush();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String jsapi_ticket = weixinAccountEntity.getJsapiticket();
		String need_make_string;
		// 注意这里参数名必须全部小写，且必须有序
		need_make_string = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		returnmap.put("success", "true");
		LogUtil.info("-----------捷微对外接口：getSignature-----------------need_make_string--------------：" + need_make_string);
		try {
			signature = DigestUtils.shaHex(need_make_string);
		} catch (Exception e) {
			e.printStackTrace();
			returnmap.put("success", "false");
			LogUtil.info("---------------捷微对外接口：getSignature---------" + e.toString());
		}

		returnmap.put("url", url);
		returnmap.put("signature", signature);
		try {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter pw = response.getWriter();
			pw.write(JSONObject.toJSONString(returnmap));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * [接口：获取关注用户信息]
	 * @param openid : 粉丝openid
	 * @param weixinId : 微信原始ID
	 * @return
	 */
	@RequestMapping(params = "getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		String openid = request.getParameter("openid");
		String weixinId = request.getParameter("weixinId");

		if (oConvertUtils.isEmpty(openid)) {
			map.put("success", "false");
			map.put("error", "openid不能为空!");
		} else {
			// 获取对应微信公众号配置信息
			WeixinAccountEntity weixinAccountEntity = weixinAccountService.getWeixinAccountByWeixinOldId(weixinId);
			try {
				Wxuser wxuser = JwUserAPI.getWxuser(weixinAccountEntity.getAccountaccesstoken(), openid);
				if (wxuser != null) {
					map.put("subscribe", wxuser.getSubscribe().toString());
					map.put("nickname", wxuser.getNickname());
					map.put("sex", wxuser.getSex());
					map.put("city", wxuser.getCity());
					map.put("province", wxuser.getProvince());
					map.put("country", wxuser.getCountry());
					map.put("headimgurl", wxuser.getHeadimgurl());
					map.put("subscribe_time", wxuser.getSubscribe_time());
				} else {
					map.put("success", "false");
					map.put("error", "openid无效");
				}
			} catch (Exception e) {
				LogUtil.error("获取用户信息接口:" + e.toString());
				map.put("success", "false");
				map.put("error", "openid无效");
			}
		}
		try {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter pw = response.getWriter();
			pw.write(JSONObject.toJSONString(map));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
