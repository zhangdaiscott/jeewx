package org.jeecgframework.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import weixin.guanjia.account.entity.WeixinAccountEntity;

/**
 * 系统用户表
 *  @author  张代浩
 */
@Entity
@Table(name = "t_s_user")
@PrimaryKeyJoinColumn(name = "id")
public class TSUser extends TSBaseUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String signatureFile;// 签名文件
	private String mobilePhone;// 手机
	private String officePhone;// 办公电话
	private String email;// 邮箱
	private String type; //用户类型  1：普通用户，2：商家用户，3:系统管理员
	

	private WeixinAccountEntity account;//商铺ID 
	
	
	

	@Column(name = "signatureFile", length = 100)
	public String getSignatureFile() {
		return this.signatureFile;
	}

	public void setSignatureFile(String signatureFile) {
		this.signatureFile = signatureFile;
	}

	@Column(name = "mobilePhone", length = 30)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "officePhone", length = 20)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountid")
	public WeixinAccountEntity getAccount() {
		return account;
	}

	public void setAccount(WeixinAccountEntity account) {
		this.account = account;
	}
	
	@Column(name = "type", length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}