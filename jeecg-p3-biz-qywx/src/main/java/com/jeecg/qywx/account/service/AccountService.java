package com.jeecg.qywx.account.service;

import java.util.List;

import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeecg.qywx.account.dao.QywxAccountDao;
import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.api.base.JwAccessTokenAPI;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.util.SystemUtil;
/**
 * 
 * 微信企业号
 *
 */
@Service
public class AccountService{
	@Autowired
	private QywxAccountDao qywxAccountDao;
	/**
	 * 获取微信企业号 AccessToken
	 * @return
	 */
	public AccessToken getAccessToken(){
		 QywxAccount qywxAccount = qywxAccountDao.get(SystemUtil.QYWX_ACCOUNT_ID);
		 //--update---author:scott-----date:20161217------------for:如果默认企业号ID获取不到企业号，则取数据库中随机一条---
		 if(qywxAccount==null){
			 List<QywxAccount> ls = qywxAccountDao.getAllQywxAccount();
			 if(ls!=null &&ls.size()>0){
				 qywxAccount = ls.get(0);
			 }
		 }
		//--update---author:scott-----date:20161217------------for:如果默认企业号ID获取不到企业号，则取数据库中随机一条---
		 AccessToken accessToken = JwAccessTokenAPI.getAccessToken(qywxAccount.getCorpid(),qywxAccount.getSecret());
		return accessToken;
	}
	/**
	 * 获取企业会话Token
	 * @return
	 */
	public AccessToken getConversationAccessToken(){
		 QywxAccount qywxAccount = qywxAccountDao.get(SystemUtil.QYWX_ACCOUNT_ID);
		//--update---author:scott-----date:20161217------------for:如果默认企业号ID获取不到企业号，则取数据库中随机一条---
		 if(qywxAccount==null){
			 List<QywxAccount> ls = qywxAccountDao.getAllQywxAccount();
			 if(ls!=null &&ls.size()>0){
				 qywxAccount = ls.get(0);
			 }
		 }
		//--update---author:scott-----date:20161217------------for:如果默认企业号ID获取不到企业号，则取数据库中随机一条---
		 if(StringUtil.isEmpty(qywxAccount.getConversationSecret())){
			return null; 
		 }else{
			 AccessToken accessToken = JwAccessTokenAPI.getAccessToken(qywxAccount.getCorpid(),qywxAccount.getConversationSecret());
				return accessToken;
		 }
	}
}
