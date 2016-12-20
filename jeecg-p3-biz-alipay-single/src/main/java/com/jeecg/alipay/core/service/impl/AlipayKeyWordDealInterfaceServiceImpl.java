package com.jeecg.alipay.core.service.impl;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.alipay.account.entity.AlipayAccount;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.base.vo.SendMessageImageTextOneVo.SendMessageImageText;
import com.jeecg.alipay.api.base.vo.SendMessageTextOneVo.SendMessage;
import com.jeecg.alipay.base.dao.AlipayAutoresponseDao;
import com.jeecg.alipay.base.entity.AlipayAutoresponse;
import com.jeecg.alipay.core.service.AlipayKeyWordDealInterfaceService;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.sucai.dao.AlipayNewsitemDao;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.dao.AlipayTexttemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;
import com.jeecg.alipay.util.AlipayResourceUtil;

@Service("alipayKeyWordDealInterfaceService")
@Transactional
public class AlipayKeyWordDealInterfaceServiceImpl implements AlipayKeyWordDealInterfaceService {

	@Autowired
	private AlipayAutoresponseDao alipayAutoresponseDao;
	@Autowired
	 private AlipayTexttemplateDao alipayTexttemplateDao;
	 @Autowired
	 private AlipayNewstemplateDao alipayNewstemplateDao;
	 @Autowired
	 private AlipayNewsitemDao alipayNewsitemDao;
	 @Autowired
	 private AlipayAccountService alipayAccountService;
	
	 
	@Override
	public String dealKeyMessage(String content,String accountid,String toUserid) {
		AlipayAccount account = alipayAccountService.getAccount();
		String responseMessage = "";
		List<AlipayAutoresponse> autoList = alipayAutoresponseDao.getAlipayAutoresponseByAccountid(accountid);
		//---------【测试】auhthor2.0  网页授权--------------------
		if("授权测试".equals(content)){
			AlipayTexttemplate textTemplate = new AlipayTexttemplate();
			String testurl =AlipayResourceUtil.getDomain()+"/alipay/alipayGzuserinfo.do?userinfo";
			try {
				String authurl="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id="+account.getAppid()+"&scope=auth_userinfo&redirect_uri="+URLEncoder.encode(testurl, "GBK");
				textTemplate.setTemplateContent("<a href='"+authurl+"'>授权测试，点击后获取用户信息</a>");
				SendMessage sendMessage = AlipayUtil.wrapperTextMessage(textTemplate, toUserid);
				responseMessage =JSONObject.toJSONString(sendMessage);
				return responseMessage;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//---------【测试】auhthor2.0 静默授权--------------------
		if("静默授权".equals(content)){
			AlipayTexttemplate textTemplate = new AlipayTexttemplate();
			String testurl =AlipayResourceUtil.getDomain()+"/alipay/alipayGzuserinfo.do?userinfo";
			try {
				String authurl="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id="+account.getAppid()+"&scope=auth_base&redirect_uri="+URLEncoder.encode(testurl, "GBK");
				textTemplate.setTemplateContent("<a href='"+authurl+"'>静默授权测试，点击后获取用户信息</a>");
				SendMessage sendMessage = AlipayUtil.wrapperTextMessage(textTemplate, toUserid);
				responseMessage =JSONObject.toJSONString(sendMessage);
				return responseMessage;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for(AlipayAutoresponse autoResponse:autoList){
			String keyWord = autoResponse.getKeyWord();
			//如果含有字母，则把所有字母都变成小写，进行匹配
			if(content.toLowerCase().indexOf(keyWord.toLowerCase())>=0){
				String lx = autoResponse.getMsgType();
				String tempalteId = autoResponse.getResContent();
				System.out.println(" [1] --keyWord-- "+keyWord);
				System.out.println(" [2] --lx-- "+lx + " -- tempalteId -- " + tempalteId);
				if("text".equals(lx)){
					AlipayTexttemplate textTemplate = alipayTexttemplateDao.get(tempalteId);
					if(textTemplate!=null){
						SendMessage sendMessage = AlipayUtil.wrapperTextMessage(textTemplate, toUserid);
						responseMessage =JSONObject.toJSONString(sendMessage);
					}
				}else if("news".equals(lx)){
					AlipayNewstemplate newsTemplate = alipayNewstemplateDao.get(tempalteId);
					if(newsTemplate!=null){
						List<AlipayNewsitem> newsList = alipayNewsitemDao.getAlipayNewsitemByTemplateId(newsTemplate.getId());
						if(newsList!=null&&newsList.size()>0){
							SendMessageImageText sendMessage = AlipayUtil.wrapperNewsMessage(newsList,toUserid);
							responseMessage =JSONObject.toJSONString(sendMessage);
						}
					}
				}
				break;
				
			}
		}
    	return responseMessage;
	}

}
