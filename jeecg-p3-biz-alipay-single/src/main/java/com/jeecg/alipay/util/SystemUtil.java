package com.jeecg.alipay.util;

import java.util.List;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;

import com.jeecg.alipay.account.dao.AlipayAccountDao;
import com.jeecg.alipay.account.entity.AlipayAccount;



/**
 * Description: 系统工具
 * @author junfeng.zhou
 * @version V1.0
 */
public class SystemUtil {
	/**
	 * 服务窗帐号ID[默认写死]
	 * @TODO
	 */
	private static String ALIPAY_ACCOUNT_ID;
	
	public static String getOnlieAlipayAccountId(){
		if(oConvertUtils.isNotEmpty(ALIPAY_ACCOUNT_ID)){
			return ALIPAY_ACCOUNT_ID;
		}else{
			AlipayAccountDao alipayAccountDao = ApplicationContextUtil.getContext().getBean(AlipayAccountDao.class);
			List<AlipayAccount> allAlipayAccount = alipayAccountDao.getAllAlipayAccount();
			if(allAlipayAccount!=null && allAlipayAccount.size()>0){
				ALIPAY_ACCOUNT_ID = allAlipayAccount.get(0).getId();
			}
			return ALIPAY_ACCOUNT_ID;
		}
	}
}
