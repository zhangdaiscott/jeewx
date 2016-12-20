package weixin.guanjia.base.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.base.service.WeixinExpandconfigServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import weixin.guanjia.base.entity.WeixinExpandconfigEntity;

import java.util.UUID;
import java.io.Serializable;

@Service("weixinExpandconfigService")
@Transactional
public class WeixinExpandconfigServiceImpl extends CommonServiceImpl implements WeixinExpandconfigServiceI {

	@Override
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinExpandconfigEntity)entity);
 	}
 	@Override
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinExpandconfigEntity)entity);
 		return t;
 	}
 	@Override
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinExpandconfigEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinExpandconfigEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinExpandconfigEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinExpandconfigEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinExpandconfigEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{keyword}",String.valueOf(t.getKeyword()));
 		sql  = sql.replace("#{classname}",String.valueOf(t.getClassname()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}