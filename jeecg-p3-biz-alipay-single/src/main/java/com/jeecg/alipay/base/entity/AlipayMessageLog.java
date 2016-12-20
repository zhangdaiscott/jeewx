package com.jeecg.alipay.base.entity;

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
public class AlipayMessageLog implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *消息类型	 */	private String messageType;	/**	 *文本内容	 */	private String messageContent;	/**	 *内容id	 */	private String contentId;		private String receiveMessage;
	/**	 *创建时间	 */
	// update-start--Author:chenchunpeng  Date:20160715 for：添加根据时间段查询的查询条件	private Date createDate;
	//查询开始时间
	private Date startDate;
	//查询结束时间
	private Date endDate;
	public AlipayMessageLog() {
		// TODO Auto-generated constructor stub
	}
	// update-end--Author:chenchunpeng  Date:20160715 for：添加根据时间段查询的查询条件
	
		public AlipayMessageLog(String id, String messageType, String messageContent, String contentId,
			String receiveMessage, Date createDate) {
		super();
		this.id = id;
		this.messageType = messageType;
		this.messageContent = messageContent;
		this.contentId = contentId;
		this.receiveMessage = receiveMessage;
		this.createDate = createDate;
	}

	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getMessageType() {	    return this.messageType;	}	public void setMessageType(String messageType) {	    this.messageType=messageType;	}	public String getMessageContent() {	    return this.messageContent;	}	public void setMessageContent(String messageContent) {	    this.messageContent=messageContent;	}	public String getContentId() {	    return this.contentId;	}	public void setContentId(String contentId) {	    this.contentId=contentId;	}	public String getReceiveMessage() {	    return this.receiveMessage;	}	public void setReceiveMessage(String receiveMessage) {	    this.receiveMessage=receiveMessage;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}
	// update-start--Author:chenchunpeng  Date:20160715 for：添加根据时间段查询的查询条件
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	// update-end--Author:chenchunpeng  Date:20160715 for：添加根据时间段查询的查询条件

}

