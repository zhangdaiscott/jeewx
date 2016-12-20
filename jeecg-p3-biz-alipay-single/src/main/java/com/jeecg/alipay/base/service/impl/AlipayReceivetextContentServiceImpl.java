package com.jeecg.alipay.base.service.impl;

import javax.annotation.Resource;

import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

import com.jeecg.alipay.base.dao.AlipayReceivetextContentDao;
import com.jeecg.alipay.base.entity.AlipayReceivetextContent;
import com.jeecg.alipay.base.service.AlipayReceivetextContentService;


/**
 * 描述：</b>AlipayReceivetextContentServiceImpl<br>
 * @author:p3.jeecg
 * @since：2016年07月15日 11时48分47秒 星期五 
 * @version:1.0
 */

@Service("alipayReceivetextContentService")
public class AlipayReceivetextContentServiceImpl implements AlipayReceivetextContentService {
	@Resource
	private AlipayReceivetextContentDao alipayReceivetextContentDao;

	@Override
	public AlipayReceivetextContent get(String id) {
		return alipayReceivetextContentDao.get(id);
	}

	@Override
	public int update(AlipayReceivetextContent alipayReceivetextContent) {
		return alipayReceivetextContentDao.update(alipayReceivetextContent);
	}

	@Override
	public void insert(AlipayReceivetextContent alipayReceivetextContent) {
		alipayReceivetextContentDao.insert(alipayReceivetextContent);
		
	}

	@Override
	public MiniDaoPage<AlipayReceivetextContent> getAll(AlipayReceivetextContent alipayReceivetextContent, int page, int rows) {
		return alipayReceivetextContentDao.getAll(alipayReceivetextContent, page, rows);
	}

	@Override
	public void delete(AlipayReceivetextContent alipayReceivetextContent) {
		alipayReceivetextContentDao.delete(alipayReceivetextContent);
		
	}
}
