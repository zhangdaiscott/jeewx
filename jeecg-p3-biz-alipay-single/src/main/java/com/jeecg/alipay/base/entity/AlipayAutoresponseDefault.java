package com.jeecg.alipay.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecg.alipay.util.SystemUtil;

/**
 * 描述：</b>QywxAutoresponseDefault:默认关键字回复; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author p3.jeecg
 * @since：2016年04月06日 16时33分37秒 星期三 
 * @version:1.0
 */
public class AlipayAutoresponseDefault implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *主键Id	 */	private String id;	/**	 *模板名称	 */	private String templatename;	/**	 *模板Id	 */	private String templateid;	/**	 *消息类型	 */	private String msgtype;	/**	 *微信账号Id	 */	private String accountid;	/**	 *是否启用	 */	private String iswork;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplatename() {	    return this.templatename;	}	public void setTemplatename(String templatename) {	    this.templatename=templatename;	}	public String getTemplateid() {	    return this.templateid;	}	public void setTemplateid(String templateid) {	    this.templateid=templateid;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getAccountid() {//	    return this.accountid;
		return SystemUtil.getOnlieAlipayAccountId();	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getIswork() {	    return this.iswork;	}	public void setIswork(String iswork) {	    this.iswork=iswork;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
}

