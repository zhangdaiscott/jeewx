package com.jeecg.qywx.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecg.qywx.util.SystemUtil;

/**
 * 描述：</b>QywxGzuserinfo:关注用户; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月25日 15时24分11秒 星期五 
 * @version:1.0
 */
public class QywxGzuserinfo implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *账号	 */	private String userid;	/**	 *姓名	 */	private String name;	/**	 *部门	 */	private String department;	/**	 *职位	 */	private String position;	/**	 *电话	 */	private String mobile;	/**	 *省份	 */	private String province;	/**	 *性别gender=1表示男，=0表示女	 */	private String gender;	/**	 *邮箱	 */	private String email;	/**	 *微信号	 */	private String weixinid;	/**	 *头像url	 */	private String avatar;	/**	 *关注状态: 1=已关注，2=已冻结，4=未关注	 */	private String subscribeStatus;	/**	 *关注时间	 */	private Date subscribeTime;	/**	 *微信账号ID	 */	private String accountid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getUserid() {	    return this.userid;	}	public void setUserid(String userid) {	    this.userid=userid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getDepartment() {	    return this.department;	}	public void setDepartment(String department) {	    this.department=department;	}	public String getPosition() {	    return this.position;	}	public void setPosition(String position) {	    this.position=position;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getProvince() {	    return this.province;	}	public void setProvince(String province) {	    this.province=province;	}	public String getGender() {	    return this.gender;	}	public void setGender(String gender) {	    this.gender=gender;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getWeixinid() {	    return this.weixinid;	}	public void setWeixinid(String weixinid) {	    this.weixinid=weixinid;	}	public String getAvatar() {	    return this.avatar;	}	public void setAvatar(String avatar) {	    this.avatar=avatar;	}	public String getSubscribeStatus() {	    return this.subscribeStatus;	}	public void setSubscribeStatus(String subscribeStatus) {	    this.subscribeStatus=subscribeStatus;	}	public Date getSubscribeTime() {	    return this.subscribeTime;	}	public void setSubscribeTime(Date subscribeTime) {	    this.subscribeTime=subscribeTime;	}	public String getAccountid() {//	    return this.accountid;
		return SystemUtil.QYWX_ACCOUNT_ID;	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	@Override
	public String toString() {
		return "QywxGzuserinfo [id=" + id + ", userid=" + userid + ", name="
				+ name + ", department=" + department + ", position="
				+ position + ", mobile=" + mobile + ", province=" + province
				+ ", gender=" + gender + ", email=" + email + ", weixinid="
				+ weixinid + ", avatar=" + avatar + ", subscribeStatus="
				+ subscribeStatus + ", subscribeTime=" + subscribeTime
				+ ", accountid=" + accountid + ", createName=" + createName
				+ ", createBy=" + createBy + ", createDate=" + createDate
				+ ", updateName=" + updateName + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + "]";
	}
}

