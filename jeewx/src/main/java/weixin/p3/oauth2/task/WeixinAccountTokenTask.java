package weixin.p3.oauth2.task;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.coupon.qrcode.JwQrcodeAPI;
import org.jeewx.api.coupon.qrcode.model.GetticketRtn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.p3.oauth2.def.WeiXinOpenConstants;
import weixin.util.DateUtils;

@Service("weixinAccountTokenTask")
@Transactional
public class WeixinAccountTokenTask{

	@Autowired
	private SystemService systemService;

 	
	 /**
	 * 循环每秒执行，查询超时的公众账号，重置Token
	 */
	public void autoResetToken() {
		long start = System.currentTimeMillis();
		
		//提前半小时重置Token
		long currentTime = new Date().getTime() - 1000 * 3600 * 2 + 30*60*1000;
		String currentDatetime = DateUtils.date2Str(DateUtils.getDate(currentTime), DateUtils.datetimeFormat);
		String hql = "from WeixinAccountEntity where addtoekntime < '"+currentDatetime+"'";
		List<WeixinAccountEntity> list = systemService.findHql(hql);
		
		org.jeecgframework.core.util.LogUtil.info("===================定时任务【重置超过2小时失效token】开始===================");
		
		for(WeixinAccountEntity account : list){
			try {
				restWeiXinToken(account);
			} catch (Exception e) {
				LogUtil.info("---------定时任务【重置超过2小时失效token】失败公众号------------------"+account.getAccountname());
			}
			
		}
		long end = System.currentTimeMillis();
		long times = end - start;
		org.jeecgframework.core.util.LogUtil.info("====================定时任务【重置超过2小时失效token】结束，" + "====执行重置公众号数量："+ (list!=null?list.size():0) +" ========总耗时"+times+"毫秒==========");
	}

	/**
	 * 重置restWeiXinToken
	 * @param account
	 */
	private void restWeiXinToken(WeixinAccountEntity account){
		String token = null;
		// 失效 重新获取
		String requestUrl = WeixinUtil.access_token_url.replace("APPID", account.getAccountappid()).replace("APPSECRET", account.getAccountappsecret());
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);
		Date getAccessTokenDate = new Date();
		if (null != jsonObject) {
			try {
				//[1].获取token凭证
				token = jsonObject.getString("access_token");
				// 重置token
				account.setAccountaccesstoken(token);
				// 重置获取token时间
				account.setAddtoekntime(getAccessTokenDate);
				
				
					//------------------------------------------------------------------------------------------------
					try {
						//[2].获取api凭证
						GetticketRtn getticketRtn = JwQrcodeAPI.doGetticket(token);
						if (null != getticketRtn) {
							try {
								// 重置token
								account.setApiticket(getticketRtn.getTicket());
								// 重置事件
								account.setApiticketttime(getAccessTokenDate);
								LogUtil.info("---------定时任务重置超过2小时失效token------------------"+"获取Apiticket成功");
							} catch (Exception e) {
								// 获取api凭证失败
								String wrongMessage = "获取api凭证失败 errcode:{"+ getticketRtn.getErrcode()+"} errmsg:{"+getticketRtn.getErrmsg()+"}";
								LogUtil.info(wrongMessage);
							}
						}
					} catch (Exception e) {
						LogUtil.info("---------------------定时任务异常--【获取api凭证】--------------"+e.toString());
					}
					//------------------------------------------------------------------------------------------------
					//[3].获取jsapi凭证
					try {
						String jsapiticket = null;
						String jsapi_ticket_url = WeiXinOpenConstants.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", token);
						JSONObject jsapi_ticket_json = WeixinUtil.httpRequest(jsapi_ticket_url, "GET", null);
						if (null != jsapi_ticket_json) {
							try {
								jsapiticket = jsapi_ticket_json.getString("ticket");
								// 重置token
								account.setJsapiticket(jsapiticket);
								// 重置事件
								account.setJsapitickettime(getAccessTokenDate);
								LogUtil.info("---------定时任务重置超过2小时失效token------------------"+"获取Jsapiticket成功");
							} catch (Exception e) {
								//获取jsapi凭证失败
								String wrongMessage = "获取jsapi凭证失败 errcode:{"+ (jsonObject.containsKey("errcode")?jsonObject.get("errcode"):"") +"} errmsg:{"+ (jsonObject.containsKey("errmsg")?jsonObject.getString("errmsg"):"") +"}";
								LogUtil.info(wrongMessage);
							}
						}
					} catch (Exception e) {
						LogUtil.info("---------------------定时任务异常--【获取jsapi凭证】--------------"+e.toString());
					}
					//------------------------------------------------------------------------------------------------
				
					systemService.saveOrUpdate(account);
				LogUtil.info("---------定时任务定时任务【重置超过2小时失效token】成功公众号------------------" + account.getAccountname());
			} catch (Exception e) {
				// 获取token失败
				String wrongMessage = "获取token失败 errcode:{"+ (jsonObject.containsKey("jsonObject")?jsonObject.get("errcode"):"")+"} errmsg:{"+ (jsonObject.containsKey("errmsg")?jsonObject.getString("errmsg"):"") +"}";
				LogUtil.info(wrongMessage);
				// 重置获取token时间【上次定时任务获取Token时间-失败保存】
				account.setAddtoekntime(getAccessTokenDate);
				// 重置获取token时间【上次定时任务获取Token时间-失败保存】
				account.setAddtoekntime(getAccessTokenDate);
				systemService.saveOrUpdate(account);
				LogUtil.info("---------定时任务定时任务【重置超过2小时失效token】失败保存公众号------------------" + account.getAccountname());
			}
		}
	}
}