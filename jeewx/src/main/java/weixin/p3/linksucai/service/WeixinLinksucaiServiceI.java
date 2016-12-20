package weixin.p3.linksucai.service;
import weixin.p3.linksucai.entity.WeixinLinksucaiEntity;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface WeixinLinksucaiServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinLinksucaiEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinLinksucaiEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinLinksucaiEntity t);
 	
 	/**
 	 * 按微信账号邮编及共享方式查询
 	 * @param <T>
 	 * @return
 	 */
 	public <T> List<T>  getLinksucaiList();
 	public <T> List<T>  getLinksucaiListForSelect(String likeName);
 	/**
 	 * 获得内部链接
 	 * @return
 	 */
 	//public String installOuterLinkWithSysParams(WeixinLinksucaiEntity t,String openid, String accountid);
 	
	/**
	 * 获取链接素材,对应的访问链接
	 * @param field 
	 * @return
	 */
	public String getInnerLink(String linkSucaiId);
	
	/**
 	 * 获取组装的最新外部链接，追加系统参数
 	 * @param url 外部URL地址
 	 * @wxcontent 微信用户发送消息
 	 * @return
 	 */
 	public String installOuterLinkWithSysParams(String url,String openid, String accountid,String wxcontent);
}
