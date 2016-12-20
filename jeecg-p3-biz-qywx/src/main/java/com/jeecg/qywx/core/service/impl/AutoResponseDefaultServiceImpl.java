package com.jeecg.qywx.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.qywx.base.dao.QywxAutoresponseDefaultDao;
import com.jeecg.qywx.base.entity.QywxAutoresponseDefault;
import com.jeecg.qywx.core.service.AutoResponseDefaultServiceI;
import com.jeecg.qywx.core.util.WeixinUtil;
import com.jeecg.qywx.sucai.dao.QywxNewsitemDao;
import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.dao.QywxTexttemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewsitem;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

@Service("autoResponseDefaultService")
@Transactional
public class AutoResponseDefaultServiceImpl implements AutoResponseDefaultServiceI {

	@Autowired
	private QywxAutoresponseDefaultDao qywxAutoresponseDefaultDao;
	@Autowired
	 private QywxTexttemplateDao qywxTexttemplateDao;
	 @Autowired
	 private QywxNewstemplateDao qywxNewstemplateDao;
	 @Autowired
	 private QywxNewsitemDao qywxNewsitemDao;
	
	 @Override
	public String getWorkDefaultResponse(String fromUserName,String toUserName,String accountId,String agentId) {
		 List<QywxAutoresponseDefault> defaultResponseList = qywxAutoresponseDefaultDao.getAutoresponseDefault(accountId, "1");
		 if(defaultResponseList!=null&&defaultResponseList.size()>0){
			 QywxAutoresponseDefault defaultResponse =  defaultResponseList.get(0);
			 String msgType = defaultResponse.getMsgtype();
			 String templateId = defaultResponse.getTemplateid();
			 String respMessage = "";
			 if("text".equals(msgType)){
				QywxTexttemplate textTemplate = qywxTexttemplateDao.get(templateId);
				if(textTemplate!=null){
					respMessage = WeixinUtil.wrapperTextMessage(textTemplate, fromUserName, toUserName);
				}
			 }else if("news".equals(msgType)){
				QywxNewstemplate newsTemplate = qywxNewstemplateDao.get(templateId);
				if(newsTemplate!=null){
					List<QywxNewsitem> newsList = qywxNewsitemDao.getQywxNewsitemByTemplateId(newsTemplate.getId());
					if(newsList!=null&&newsList.size()>0){
						respMessage = WeixinUtil.wrapperNewsMessage(newsList, fromUserName, toUserName,accountId,agentId);
					}
				}
			 }
			 return respMessage;
		 }
		return null;
	}
	
}