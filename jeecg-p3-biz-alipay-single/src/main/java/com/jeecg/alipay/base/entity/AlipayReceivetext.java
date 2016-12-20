package com.jeecg.alipay.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecg.alipay.util.SystemUtil;

/**
 * 描述：</b>QywxReceivetext:文本消息; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月25日 10时13分23秒 星期五 
 * @version:1.0
 */
public class AlipayReceivetext implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *开发者微信号	 */	private String tousername;	/**	 *发送方帐号（一个OpenID）	 */	private String fromusername;	/**	 *消息创建时间 （整型）	 */	private Date createtime;	/**	 *消息类型（text/image/location/link）	 */	private String msgtype;	/**	 *消息id，64位整型	 */	private String msgid;	/**	 *消息内容	 */	private String content;	/**	 *是否回复	 */	private String response;	/**	 *回复内容	 */	private String rescontent;	/**	 *用户昵称	 */	private String nickname;
	
	/**
	 * 应用id
	 */
	private String agentId;	/**	 *微信账号Id	 */	private String accountid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTousername() {	    return this.tousername;	}	public void setTousername(String tousername) {	    this.tousername=tousername;	}	public String getFromusername() {	    return this.fromusername;	}	public void setFromusername(String fromusername) {	    this.fromusername=fromusername;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getMsgid() {	    return this.msgid;	}	public void setMsgid(String msgid) {	    this.msgid=msgid;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getResponse() {	    return this.response;	}	public void setResponse(String response) {	    this.response=response;	}	public String getRescontent() {	    return this.rescontent;	}	public void setRescontent(String rescontent) {	    this.rescontent=rescontent;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getAccountid() {//	    return this.accountid;
		return SystemUtil.getOnlieAlipayAccountId();	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	@Override
	public String toString() {
		return "QywxReceivetext [id=" + id + ", tousername=" + tousername
				+ ", fromusername=" + fromusername + ", createtime="
				+ createtime + ", msgtype=" + msgtype + ", msgid=" + msgid
				+ ", content=" + content + ", response=" + response
				+ ", rescontent=" + rescontent + ", nickname=" + nickname
				+ ", agentId=" + agentId + ", accountid=" + accountid
				+ ", createName=" + createName + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateName=" + updateName
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}
	
}

