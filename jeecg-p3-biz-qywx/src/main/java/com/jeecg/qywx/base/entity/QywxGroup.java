package com.jeecg.qywx.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecg.qywx.util.SystemUtil;

/**
 * 描述：</b>QywxGroup:关注用户组; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月25日 13时41分22秒 星期五 
 * @version:1.0
 */
public class QywxGroup implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *部门Id	 */	private String id;	/**	 *部门名称	 */	private String name;	/**	 *上级部门Id	 */	private String parentid;
	
	/**
	 * 排序
	 */	private String orders;	/**	 *微信账号ID	 */	private String accountid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getParentid() {	    return this.parentid;	}	public void setParentid(String parentid) {	    this.parentid=parentid;	}	public String getAccountid() {//	    return this.accountid;
		return SystemUtil.QYWX_ACCOUNT_ID;	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "QywxGroup [id=" + id + ", name=" + name + ", parentid="
				+ parentid + ", accountid=" + accountid+", orders=" + orders + ", createName="
				+ createName + ", createBy=" + createBy + ", createDate="
				+ createDate + ", updateName=" + updateName + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + "]";
	}
}

