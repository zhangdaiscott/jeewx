package com.jeecg.qywx.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 描述：</b>QywxMenu:自定义菜单表<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一 
 * @version:1.0
 */
public class QywxMenu implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *应用主键ID	 */	private String agentId;	/**	 *菜单标题	 */	private String menuName;	/**	 *菜单类型	 */	private String menuType;	/**	 *菜单KEY	 */	private String menuKey;	/**	 *菜单位置	 */	private String orders;	/**	 *响应消息类型	 */	private String msgType;	/**	 *关联素材ID	 */	private String templateId;	/**	 *网页链接	 */	private String url;	/**	 *父ID	 */	private String fatherId;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getAgentId() {	    return this.agentId;	}	public void setAgentId(String agentId) {	    this.agentId=agentId;	}	public String getMenuName() {	    return this.menuName;	}	public void setMenuName(String menuName) {	    this.menuName=menuName;	}	public String getMenuType() {	    return this.menuType;	}	public void setMenuType(String menuType) {	    this.menuType=menuType;	}	public String getMenuKey() {	    return this.menuKey;	}	public void setMenuKey(String menuKey) {	    this.menuKey=menuKey;	}	public String getOrders() {	    return this.orders;	}	public void setOrders(String orders) {	    this.orders=orders;	}	public String getMsgType() {	    return this.msgType;	}	public void setMsgType(String msgType) {	    this.msgType=msgType;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public String getFatherId() {	    return this.fatherId;	}	public void setFatherId(String fatherId) {	    this.fatherId=fatherId;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
}

