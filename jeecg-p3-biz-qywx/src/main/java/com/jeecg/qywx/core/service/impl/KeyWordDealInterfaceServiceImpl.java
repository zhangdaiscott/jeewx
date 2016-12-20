package com.jeecg.qywx.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.qywx.base.dao.QywxAutoresponseDao;
import com.jeecg.qywx.base.entity.QywxAutoresponse;
import com.jeecg.qywx.core.service.KeyWordDealInterfaceService;
import com.jeecg.qywx.core.util.WeixinUtil;
import com.jeecg.qywx.sucai.dao.QywxNewsitemDao;
import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.dao.QywxTexttemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewsitem;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

@Service("keyWordDealInterfaceServcie")
@Transactional
public class KeyWordDealInterfaceServiceImpl implements KeyWordDealInterfaceService {

	 @Autowired
	 private QywxAutoresponseDao qywxAutoresponseDao;
	 @Autowired
	 private QywxTexttemplateDao qywxTexttemplateDao;
	 @Autowired
	 private QywxNewstemplateDao qywxNewstemplateDao;
	 @Autowired
	 private QywxNewsitemDao qywxNewsitemDao;
	 
	@Override
	public String dealKeyMessage(String content, String toUserName,
			String fromUser,String accountid,String agentid) {
		String responseMessage = "";
		List<QywxAutoresponse> autoList = qywxAutoresponseDao.getQywxAutoresponseByAccountid(accountid);
		for(QywxAutoresponse autoResponse:autoList){
			String keyWord = autoResponse.getKeyWord();
			System.out.println(" [1] --keyWord-- "+keyWord);
			//如果含有字母，则把所有字母都变成小写，进行匹配
			if(content.toLowerCase().indexOf(keyWord.toLowerCase())>=0){
				String lx = autoResponse.getMsgType();
				String tempalteId = autoResponse.getResContent();
				System.out.println(" [2] --lx-- "+lx + " -- tempalteId -- " + tempalteId);
				if("text".equals(lx)){
					QywxTexttemplate textTemplate = qywxTexttemplateDao.get(tempalteId);
					if(textTemplate!=null){
						System.out.println(" [3] --responseMessage--" + responseMessage);
						responseMessage = WeixinUtil.wrapperTextMessage(textTemplate, fromUser, toUserName);
					}
				}else if("news".equals(lx)){
					QywxNewstemplate newsTemplate = qywxNewstemplateDao.get(tempalteId);
					if(newsTemplate!=null){
						List<QywxNewsitem> newsList = qywxNewsitemDao.getQywxNewsitemByTemplateId(newsTemplate.getId());
						if(newsList!=null&&newsList.size()>0){
							responseMessage = WeixinUtil.wrapperNewsMessage(newsList, fromUser, toUserName,accountid,agentid);
							System.out.println(" [4] --responseMessage--" + responseMessage);
						}
					}
				}
				break;
				
			}
		}
    	return responseMessage;
	}

}
