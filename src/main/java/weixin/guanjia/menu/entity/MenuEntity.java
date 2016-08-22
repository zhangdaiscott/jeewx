package weixin.guanjia.menu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@Table(name="weixin_menuentity")
public class MenuEntity extends IdEntity {
	
	private String name;
	private String menuKey;
	private String type;//click or view
	private String url;//如果view url不能为空
	private String msgType;//消息类型，是文本消息还是图文消息
	private String templateId;//模板Id
	private String orders;
	private MenuEntity menuEntity;
	private List<MenuEntity> menuList; 
	
	private String accountId;
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="menukey")
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="orders")
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fatherid")
	public MenuEntity getMenuEntity() {
		return menuEntity;
	}
	
	public void setMenuEntity(MenuEntity menuEntity) {
		this.menuEntity = menuEntity;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menuEntity")
	public List<MenuEntity> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuEntity> menuList) {
		this.menuList = menuList;
	}
	@Column(name="msgtype")
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	@Column(name="templateid")
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	@Column(name="accountid")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
