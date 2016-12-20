package com.jeecg.alipay.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.alipay.api.base.vo.SendMessageImageTextOneVo.SendMessageImageText;
import com.jeecg.alipay.api.base.vo.SendMessageTextOneVo.SendMessage;
import com.jeecg.alipay.base.dao.AlipayAutoresponseDefaultDao;
import com.jeecg.alipay.base.entity.AlipayAutoresponseDefault;
import com.jeecg.alipay.core.service.AlipayAutoResponseDefaultServiceI;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.sucai.dao.AlipayNewsitemDao;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.dao.AlipayTexttemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;


@Service("alipayAutoResponseDefaultService")
@Transactional
public class AlipayAutoResponseDefaultServiceImpl implements AlipayAutoResponseDefaultServiceI {

	@Autowired
	private AlipayAutoresponseDefaultDao alipayAutoresponseDefaultDao;
	@Autowired
	 private AlipayTexttemplateDao alipayTexttemplateDao;
	 @Autowired
	 private AlipayNewstemplateDao alipayNewstemplateDao;
	 @Autowired
	 private AlipayNewsitemDao alipayNewsitemDao;
	
	 /**
	  * 获取默认的回复消息
	  */
	 @Override
	public String getWorkDefaultResponse(String toUserid,String accountId) {
		 List<AlipayAutoresponseDefault> defaultResponseList = alipayAutoresponseDefaultDao.getAutoresponseDefault(accountId, "1");
		 if(defaultResponseList!=null&&defaultResponseList.size()>0){
			 AlipayAutoresponseDefault defaultResponse =  defaultResponseList.get(0);
			 String msgType = defaultResponse.getMsgtype();
			 String templateId = defaultResponse.getTemplateid();
			 String respMessage = "";
			 if("text".equals(msgType)){
				AlipayTexttemplate textTemplate = alipayTexttemplateDao.get(templateId);
				if(textTemplate!=null){
					 SendMessage sendMessage = AlipayUtil.wrapperTextMessage(textTemplate, toUserid);
					 respMessage =JSONObject.toJSONString(sendMessage);
				}
			 }else if("news".equals(msgType)){
				AlipayNewstemplate newsTemplate = alipayNewstemplateDao.get(templateId);
				if(newsTemplate!=null){
					List<AlipayNewsitem> newsList = alipayNewsitemDao.getAlipayNewsitemByTemplateId(newsTemplate.getId());
					if(newsList!=null&&newsList.size()>0){
						SendMessageImageText sendMessage = AlipayUtil.wrapperNewsMessage(newsList,toUserid);
						 respMessage =JSONObject.toJSONString(sendMessage);
					}
				}
			 }
			 return respMessage;
		 }
		return null;
	}
	
}