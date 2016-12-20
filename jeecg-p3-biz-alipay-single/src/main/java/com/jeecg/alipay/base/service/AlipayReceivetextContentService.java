package com.jeecg.alipay.base.service;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

import com.jeecg.alipay.base.entity.AlipayReceivetextContent;

/**
 * 描述：</b>AlipayReceivetextContentService<br>
 * @author:p3.jeecg
 * @since：2016年07月15日 11时48分47秒 星期五 
 * @version:1.0
 */
public interface AlipayReceivetextContentService {
	public AlipayReceivetextContent get(String id);

	public int update(AlipayReceivetextContent alipayReceivetextContent);

	public void insert(AlipayReceivetextContent alipayReceivetextContent);

	public MiniDaoPage<AlipayReceivetextContent> getAll(AlipayReceivetextContent alipayReceivetextContent,int page,int rows);

	public void delete(AlipayReceivetextContent alipayReceivetextContent);
	
}
