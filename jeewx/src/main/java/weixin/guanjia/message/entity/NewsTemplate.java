package weixin.guanjia.message.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@Table(name="weixin_newstemplate")
public class NewsTemplate extends IdEntity{
	
	private String templateName;
	private String addTime;
	private String type;
	private List<NewsItem> newsItemList;
	private String accountId;
	//update-begin-author:taoYan date:20180312 for：图文消息上传到微信---
	private String isup;//上传状态：已经上传_1,未上传_0
	private String mediaid;
	//update-end-author:taoYan date:20180312 for：图文消息上传到微信---
	@Column(name = "accountid",length=100)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name="tempatename")
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(name="addtime")
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="templateid")  
	public List<NewsItem> getNewsItemList() {
		return newsItemList;
	}
	public void setNewsItemList(List<NewsItem> newsItemList) {
		this.newsItemList = newsItemList;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="isup")
	public String getIsup() {
		return isup;
	}
	public void setIsup(String isup) {
		this.isup = isup;
	}
	@Column(name="mediaid")
	public String getMediaid() {
		return mediaid;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	
}
