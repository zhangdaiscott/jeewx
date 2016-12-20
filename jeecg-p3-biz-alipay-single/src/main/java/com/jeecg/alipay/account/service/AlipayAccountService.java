package com.jeecg.alipay.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.alipay.account.dao.AlipayAccountDao;
import com.jeecg.alipay.account.entity.AlipayAccount;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.core.contants.AlipayServiceEnvConstants;
import com.jeecg.alipay.util.SystemUtil;
/**
 * 
 * 微信企业号
 *
 */
@Service
public class AlipayAccountService{
	@Autowired
	private AlipayAccountDao alipayAccountDao;
	
	AlipayConfig config;
	/**
	 * 获取微信企业号 AccessToken
	 * @return
	 */
	public void setAlipayConfig(){
		 //update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		List<AlipayAccount> allAlipayAccount = alipayAccountDao.getAllAlipayAccount();
		if(allAlipayAccount.size() > 0){
			AlipayAccount alipayAccount = allAlipayAccount.get(0);
			config = new AlipayConfig(alipayAccount.getAppid(), alipayAccount.getRsaPrivateKey(), alipayAccount.getAlipayPublicKey(),alipayAccount.getPublicKey());
			AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY = alipayAccount.getAlipayPublicKey();
			AlipayServiceEnvConstants.PUBLIC_KEY = alipayAccount.getPublicKey();
			AlipayServiceEnvConstants.PRIVATE_KEY = alipayAccount.getRsaPrivateKey();
			AlipayServiceEnvConstants.APP_ID = alipayAccount.getAppid();
		}
		//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
	}
	
	//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
	public AlipayConfig getAlipayConfig(){
		if(this.config == null){
			setAlipayConfig();
		}
		return this.config;
	}
	//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
	public AlipayAccount getAccount(){
		 AlipayAccount alipayAccount = alipayAccountDao.get(SystemUtil.getOnlieAlipayAccountId());
		 return alipayAccount;
	}
}
