package com.jeecg.qywx.conversation.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 描述：</b>QywxConversation:InnoDB free: 11264 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author p3.jeecg
 * @since：2016年05月12日 21时09分13秒 星期四 
 * @version:1.0
 */
public class QywxConversation implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *TITLE	 */	private String title;	/**	 *USERNAMELIST	 */	private String usernamelist;	/**	 *USERIDLIST	 */	private String useridlist;	/**	 *STATUS	 */	private Integer status;	/**	 *MANAGERID	 */	private String managerid;
	/**
	 * 管理员名称
	 */
	private String managername;
	
	/**会话ID*/
	private String chatid;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getUsernamelist() {	    return this.usernamelist;	}	public void setUsernamelist(String usernamelist) {	    this.usernamelist=usernamelist;	}	public String getUseridlist() {	    return this.useridlist;	}	public void setUseridlist(String useridlist) {	    this.useridlist=useridlist;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public String getManagerid() {	    return this.managerid;	}	public void setManagerid(String managerid) {	    this.managerid=managerid;	}
	
	public String getChatid() {
		return chatid;
	}
	
	public void setChatid(String chatid) {
		this.chatid = chatid;
	}
	
	public String getManagername() {
		return managername;
	}
	
	public void setManagername(String managername) {
		this.managername = managername;
	}
	
}

