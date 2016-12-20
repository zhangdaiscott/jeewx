package com.jeecg.qywx.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 描述：</b>QywxAccount:账号信息表<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author Alex
 * @since：2016年03月24日 14时55分37秒 星期四 
 * @version:1.0
 */
public class QywxAccount implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *名称	 */	private String accontName;	/**	 *描述	 */	private String accountDesc;	/**	 *AccessToken	 */	private String accessToken;	/**	 *管理组凭证密钥	 */	private String secret;	/**	 *企业号标识	 */	private String corpid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;
	/*创建企业应用管理
	 * 
	 */
	private List<QywxAgent> qywxAccountList;
	/**企业会话secret*/
	private String conversationSecret;	
	public List<QywxAgent> getQywxAccountList() {
		return qywxAccountList;
	}
	public void setQywxAccountList(List<QywxAgent> qywxAccountList) {
		this.qywxAccountList = qywxAccountList;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getAccontName() {	    return this.accontName;	}	public void setAccontName(String accontName) {	    this.accontName=accontName;	}	public String getAccountDesc() {	    return this.accountDesc;	}	public void setAccountDesc(String accountDesc) {	    this.accountDesc=accountDesc;	}	public String getAccessToken() {	    return this.accessToken;	}	public void setAccessToken(String accessToken) {	    this.accessToken=accessToken;	}	public String getSecret() {	    return this.secret;	}	public void setSecret(String secret) {	    this.secret=secret;	}	public String getCorpid() {	    return this.corpid;	}	public void setCorpid(String corpid) {	    this.corpid=corpid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	
	public String getConversationSecret() {
		return conversationSecret;
	}
	
	public void setConversationSecret(String conversationSecret) {
		this.conversationSecret = conversationSecret;
	}
}

