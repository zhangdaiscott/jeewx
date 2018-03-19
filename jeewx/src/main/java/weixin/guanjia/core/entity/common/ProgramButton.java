package weixin.guanjia.core.entity.common;

import weixin.guanjia.menu.entity.MenuEntity;

/**
 * 小程序button
 * @author taoYan
 * @since 2018年3月12日
 */
public class ProgramButton extends Button{
	private String type;
	private String url;
	private String appid;
	private String pagepath;
	 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	
	public ProgramButton() {
	}
	/**
	 *  构造器
	 * @param entity
	 */
	public ProgramButton(MenuEntity entity) {
		this.type = entity.getType();
		this.url = entity.getUrl();
		this.appid = entity.getAppid();
		this.pagepath = entity.getPagepath();
		setName(entity.getName());
	}
	
	
}
