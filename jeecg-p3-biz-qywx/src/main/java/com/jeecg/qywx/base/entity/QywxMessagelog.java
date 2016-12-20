package com.jeecg.qywx.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 描述：</b>QywxMessagelog:<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author p3.jeecg
 * @since：2016年05月26日 18时54分38秒 星期四 
 * @version:1.0
 */
public class QywxMessagelog implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *应用id	 */	private String wxAgentId;	/**	 *部门id	 */	private String topartysId;	/**	 *消息类型	 */	private String messageType;	/**	 *文本内容	 */	private String messageContent;	/**	 *内容id	 */	private String contentId;	/**	 *消息状态	 */	private String receiveMessage;	/**	 *创建时间	 */	private Date createDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getWxAgentId() {	    return this.wxAgentId;	}	public void setWxAgentId(String wxAgentId) {	    this.wxAgentId=wxAgentId;	}	public String getTopartysId() {	    return this.topartysId;	}	public void setTopartysId(String topartysId) {	    this.topartysId=topartysId;	}	public String getMessageType() {	    return this.messageType;	}	public void setMessageType(String messageType) {	    this.messageType=messageType;	}	public String getMessageContent() {	    return this.messageContent;	}	public void setMessageContent(String messageContent) {	    this.messageContent=messageContent;	}	public String getContentId() {	    return this.contentId;	}	public void setContentId(String contentId) {	    this.contentId=contentId;	}	public String getReceiveMessage() {	    return this.receiveMessage;	}	public void setReceiveMessage(String receiveMessage) {	    this.receiveMessage=receiveMessage;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}
}

