package com.jeecg.qywx.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.jeecg.qywx.util.SystemUtil;

/**
 * 描述：</b>QywxAgent:应用信息表<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author Alex
 * @since：2016年03月24日 14时55分38秒 星期四 
 * @version:1.0
 */
public class QywxAgent implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *主键Id	 */	private String id;	/**	 *企业号ID	 */	private String accountId;	/**	 *应用ID(微信)	 */	private String wxAgentid;	/**	 *应用名称	 */	private String agentName;
	/**
	 *回调token
	 */
	private String token;
	/**
	 *回调EncodingAESKey
	 */
	private String encodingAESKey;
		/**	 *方形头像	 */	private String squareLogoUrl;	/**	 *圆形头像	 */	private String roundLogoUrl;	/**	 *圆形头像	 */	private String description;	/**	 *是否被禁用	 */	private String closeStatus;	/**	 *可信域名	 */	private String redirectDomain;	/**	 *是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报	 */	private String reportLocationFlag;	/**	 *是否接收用户变更通知。0：不接收；1：接收	 */	private String isreportuser;	/**	 *是否上报用户进入应用事件。0：不接收；1：接收	 */	private String isreportenter;	/**	 *应用类型。1：消息型；2：主页型	 */	private String appType;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getAccountId() {//	    return this.accountId;	    return SystemUtil.QYWX_ACCOUNT_ID;	}	public void setAccountId(String accountId) {	    this.accountId=accountId;	}	public String getWxAgentid() {	    return this.wxAgentid;	}	public void setWxAgentid(String wxAgentid) {	    this.wxAgentid=wxAgentid;	}	public String getAgentName() {	    return this.agentName;	}	public void setAgentName(String agentName) {	    this.agentName=agentName;	}	public String getSquareLogoUrl() {	    return this.squareLogoUrl;	}	public void setSquareLogoUrl(String squareLogoUrl) {	    this.squareLogoUrl=squareLogoUrl;	}	public String getRoundLogoUrl() {	    return this.roundLogoUrl;	}	public void setRoundLogoUrl(String roundLogoUrl) {	    this.roundLogoUrl=roundLogoUrl;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getCloseStatus() {	    return this.closeStatus;	}	public void setCloseStatus(String closeStatus) {	    this.closeStatus=closeStatus;	}	public String getRedirectDomain() {	    return this.redirectDomain;	}	public void setRedirectDomain(String redirectDomain) {	    this.redirectDomain=redirectDomain;	}	public String getReportLocationFlag() {	    return this.reportLocationFlag;	}	public void setReportLocationFlag(String reportLocationFlag) {	    this.reportLocationFlag=reportLocationFlag;	}	public String getIsreportuser() {	    return this.isreportuser;	}	public void setIsreportuser(String isreportuser) {	    this.isreportuser=isreportuser;	}	public String getIsreportenter() {	    return this.isreportenter;	}	public void setIsreportenter(String isreportenter) {	    this.isreportenter=isreportenter;	}	public String getAppType() {	    return this.appType;	}	public void setAppType(String appType) {	    this.appType=appType;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}
	public String getEncodingAESKey() {
		return encodingAESKey;
	}
}

