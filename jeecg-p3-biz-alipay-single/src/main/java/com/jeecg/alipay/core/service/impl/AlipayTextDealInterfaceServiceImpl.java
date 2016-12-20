package com.jeecg.alipay.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.alipay.base.dao.AlipayReceivetextDao;
import com.jeecg.alipay.base.entity.AlipayReceivetext;
import com.jeecg.alipay.core.service.AlipayTextDealInterfaceService;

@Service("alipayTextDealInterfaceService")
@Transactional
public class AlipayTextDealInterfaceServiceImpl implements AlipayTextDealInterfaceService{
	
	@Autowired
	private AlipayReceivetextDao alipayReceivetextDao;

	@Override
	public void dealTextMessage(AlipayReceivetext receiveText) {
		alipayReceivetextDao.insert(receiveText);
	}
	
}
