package weixin.guanjia.menu.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.service.WeixinMenuServiceI;

@Service("weixinMenuService")
@Transactional
public class WeixinMenuServiceImpl extends CommonServiceImpl implements WeixinMenuServiceI {
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((MenuEntity)entity);
 	}
 	public <T> Serializable save(T entity) {
 		MenuEntity menuEntity = (MenuEntity) entity;
 		menuEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((MenuEntity)entity);
 		return t;
 	}
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((MenuEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(MenuEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(MenuEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(MenuEntity t){
	 	return true;
 	}
 	
}