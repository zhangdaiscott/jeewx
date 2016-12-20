package com.jeecg.qywx.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecg.qywx.util.SystemUtil;

/**
 * 描述：</b>QywxAutoresponse:关键字管理; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月25日 11时33分23秒 星期五 
 * @version:1.0
 */
public class QywxAutoresponse implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *关键字	 */	private String keyWord;	/**	 *回复内容	 */	private String resContent;	/**	 *消息类型	 */	private String msgType;	/**	 *模板名称	 */	private String templateName;	/**	 *微信账号id	 */	private String accountid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getKeyWord() {	    return this.keyWord;	}	public void setKeyWord(String keyWord) {	    this.keyWord=keyWord;	}	public String getResContent() {	    return this.resContent;	}	public void setResContent(String resContent) {	    this.resContent=resContent;	}	public String getMsgType() {	    return this.msgType;	}	public void setMsgType(String msgType) {	    this.msgType=msgType;	}	public String getTemplateName() {	    return this.templateName;	}	public void setTemplateName(String templateName) {	    this.templateName=templateName;	}	public String getAccountid() {//	    return this.accountid;
	    return SystemUtil.QYWX_ACCOUNT_ID;	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	@Override
	public String toString() {
		return "QywxAutoresponse [id=" + id + ", keyWord=" + keyWord
				+ ", resContent=" + resContent + ", msgType=" + msgType
				+ ", templateName=" + templateName + ", accountid=" + accountid
				+ ", createName=" + createName + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateName=" + updateName
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}
}

