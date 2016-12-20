package com.jeecg.qywx.conversation.service;

import java.util.Date;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.qywx.account.service.AccountService;
import com.jeecg.qywx.api.conversation.ConversationAPI;
import com.jeecg.qywx.api.conversation.vo.Conversation;
import com.jeecg.qywx.api.conversation.vo.Conversation4Update;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.api.core.common.MsgResponse;
import com.jeecg.qywx.conversation.dao.QywxConversationDao;
import com.jeecg.qywx.conversation.entity.QywxConversation;
import com.jeecg.qywx.util.MyBeanUtils;

@Service
public class QywxConversationService {
	@Autowired
	private QywxConversationDao qywxConversationDao;
	@Autowired
	private AccountService accountService;
	private AjaxJson json = new AjaxJson();
	
	
	public AjaxJson deleteConversation(QywxConversation qywxConversation){
		AccessToken token = accountService.getConversationAccessToken();
		if(token==null){
			json.setSuccess(false);
			json.setMsg("token获取失败，请检查会话secret配置");
			return json;
		}
		//获取旧会话信息
		QywxConversation oldConversation = qywxConversationDao.get(qywxConversation.getId());
		MsgResponse msgResponse = ConversationAPI.quit(oldConversation.getChatid(), oldConversation.getManagerid(), token.getAccesstoken());
		if(msgResponse.getErrcode()==0){
			json.setSuccess(true);
			json.setMsg("执行成功！");
			MyBeanUtils.copyBeanNotNull2Bean(qywxConversation, oldConversation);
			qywxConversationDao.delete(oldConversation);
		}else{
			json.setSuccess(false);
			json.setMsg("执行失败！");
		}
		return json;
	}
	
	
	/**
	 * 创建会话
	 * @param qywxConversation
	 * @return
	 */
	public AjaxJson createConversation(QywxConversation qywxConversation){
		//用时间戳作为会话ID
		String chatid = new Date().getTime()+"";
		qywxConversation.setChatid(chatid);
		//获取会话用户列表，最少3人
		String[] userlist = qywxConversation.getUseridlist().split(",");
		if(userlist.length>=3){
			Conversation conversation = new Conversation(chatid, qywxConversation.getTitle(), qywxConversation.getManagerid(), userlist); 
			//创建会话
			AccessToken token = accountService.getConversationAccessToken();
			if(token==null){
				json.setSuccess(false);
				json.setMsg("token获取失败，请检查会话secret配置");
				return json;
			}
			MsgResponse msgResponse= ConversationAPI.createConversation(conversation, token.getAccesstoken());
			//保存
			if(msgResponse.getErrcode()==0){
				qywxConversation.setStatus(1);
				json.setSuccess(true);
				json.setMsg("会话创建成功！");
				qywxConversationDao.insert(qywxConversation);
			}else{
				qywxConversation.setStatus(0);
				json.setSuccess(false);
				json.setMsg("会话创建失败，微信接口异常，请稍后重试！");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("会话用户数最少3个！");
		}
		return json;
	}
	
	/**
	 * 修改会话信息
	 * @param qywxConversation
	 * @return
	 */
	public  AjaxJson updateConversation(QywxConversation qywxConversation){
		//获取旧会话信息
		QywxConversation oldConversation = qywxConversationDao.get(qywxConversation.getId());
		//获取原来用户列表
		String[] useridlist = oldConversation.getUseridlist().split(",");
		//获取新的用户列表
		String[] newuseridlist = qywxConversation.getUseridlist().split(",");
		//获取删除的用户列表，如果旧用户不在新用户列表中，代表该用户已经被删除。
		String deluser ="";
		for(String uid:useridlist){
			boolean have= false;
			for(String nuid:newuseridlist){
				if(nuid.equals(uid)){
					have = true;
				}
			}
			if(!have){
				deluser =deluser + uid+",";
			}
		}
		//删除最后一个逗号
		if(deluser.length()!=0){
			deluser = deluser.substring(0, deluser.length()-1);
		}
		String[] deluserlist = deluser.split(",");
		AccessToken token = accountService.getConversationAccessToken();
		if(token==null){
			json.setSuccess(false);
			json.setMsg("token获取失败，请检查会话secret配置");
			return json;
		}
		Conversation4Update uc = new Conversation4Update(oldConversation.getChatid(), qywxConversation.getManagerid(), qywxConversation.getTitle(), qywxConversation.getManagerid(), newuseridlist, deluserlist);
		MsgResponse msgResponse = ConversationAPI.updateConversation(uc,token.getAccesstoken());
		if(msgResponse.getErrcode()==0){
			json.setSuccess(true);
			json.setMsg("执行成功！");
			MyBeanUtils.copyBeanNotNull2Bean(qywxConversation, oldConversation);
			qywxConversationDao.update(oldConversation);
		}else{
			json.setSuccess(false);
			json.setMsg("执行失败！");
		}
		return json;
	}
}
