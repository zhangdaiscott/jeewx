package weixin.guanjia.account.service;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;
import org.jeewx.api.core.exception.WexinReqException;

import weixin.guanjia.account.entity.WeixinAccountEntity;

public interface WeixinAccountServiceI extends CommonService{
	@Override
 	public <T> void delete(T entity);
 	@Override
 	public <T> Serializable save(T entity);
 	@Override
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAccountEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAccountEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAccountEntity t);
 	
 	public String getAccessToken();
 	
 	public String getAccessToken(String accountId);
 	
 	@Deprecated
 	public WeixinAccountEntity findLoginWeixinAccount();
 	public List<WeixinAccountEntity> findByUsername(String username);
 	/**
 	 * 按微信的toUsername获取对象
 	 * @param toUserName
 	 * @return
 	 */
 	public WeixinAccountEntity findByToUsername(String toUserName);
 	/**
	 * 通过微信原始ID，获取系统微信公众账号配置信息
	 * @param weixinId
	 * @return
	 */
	public WeixinAccountEntity getWeixinAccountByWeixinOldId(String weixinId);
	
	public AjaxJson resetAccessToken(String accountid) throws WexinReqException;
}
