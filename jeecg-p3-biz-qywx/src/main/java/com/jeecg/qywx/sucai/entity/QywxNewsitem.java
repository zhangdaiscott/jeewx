package com.jeecg.qywx.sucai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：</b>QywxNewsitem:图文素材新闻; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月24日 18时59分46秒 星期四 
 * @version:1.0
 */
public class QywxNewsitem implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *标题	 */	private String title;	/**	 *作者	 */	private String author;	/**	 *图片路径	 */	private String imagePath;	/**	 *内容	 */	private String content;	/**	 *图文模板id	 */	private String templateid;	/**	 *摘要	 */	private String description;	/**	 *新闻顺序	 */	private String orderNo;	/**	 *消息内容的url	 */	private String url;	/**	 *活动id	 */	private String hdid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getAuthor() {	    return this.author;	}	public void setAuthor(String author) {	    this.author=author;	}	public String getImagePath() {	    return this.imagePath;	}	public void setImagePath(String imagePath) {	    this.imagePath=imagePath;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getTemplateid() {	    return this.templateid;	}	public void setTemplateid(String templateid) {	    this.templateid=templateid;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getOrderNo() {	    return this.orderNo;	}	public void setOrderNo(String orderNo) {	    this.orderNo=orderNo;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public String getHdid() {	    return this.hdid;	}	public void setHdid(String hdid) {	    this.hdid=hdid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	@Override
	public String toString() {
		return "QywxNewsitem [id=" + id + ", title=" + title + ", author="
				+ author + ", imagePath=" + imagePath + ", content=" + content
				+ ", templateid=" + templateid + ", description=" + description
				+ ", orderNo=" + orderNo + ", url=" + url + ", hdid=" + hdid
				+ ", createName=" + createName + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateName=" + updateName
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}
	
}

