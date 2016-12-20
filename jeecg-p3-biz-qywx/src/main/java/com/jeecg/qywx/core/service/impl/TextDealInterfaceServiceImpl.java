package com.jeecg.qywx.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.qywx.base.dao.QywxReceivetextDao;
import com.jeecg.qywx.base.entity.QywxReceivetext;
import com.jeecg.qywx.core.service.TextDealInterfaceService;

@Service("textDealInterfaceService")
@Transactional
public class TextDealInterfaceServiceImpl implements TextDealInterfaceService{
	
	@Autowired
	private QywxReceivetextDao qywxReceivetextDao;

	@Override
	public void dealTextMessage(QywxReceivetext receiveText) {
		qywxReceivetextDao.insert(receiveText);
	}
	
}
