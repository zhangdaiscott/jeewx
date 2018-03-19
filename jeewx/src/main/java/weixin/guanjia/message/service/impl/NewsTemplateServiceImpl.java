package weixin.guanjia.message.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.model.UploadGraphic;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.util.WeixinUtilOsc;

@Service("newsTemplateService")
@Transactional
public class NewsTemplateServiceImpl extends CommonServiceImpl implements NewsTemplateServiceI {
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Override
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((NewsTemplate)entity);
 	}
 	@Override
 	public <T> Serializable save(T entity) {
 		((NewsTemplate)entity).setAccountId(ResourceUtil.getWeiXinAccountId());
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((NewsTemplate)entity);
 		return t;
 	}
 	@Override
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((NewsTemplate)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(NewsTemplate t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(NewsTemplate t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(NewsTemplate t){
	 	return true;
 	}
	@Override
	public JSONObject uploadNewsTemplate(UploadGraphic graphic) {
		String accessToken =  this.weixinAccountService.getAccessToken();
		//TODO 暂时只做新增 修改后期再加
		return WeixinUtilOsc.uploadNewsTemplate(graphic, accessToken);
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
// 	public String replaceVal(String sql,NewsTemplate t){
// 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
// 		sql  = sql.replace("#{accountname}",String.valueOf(t.getAccountname()));
// 		sql  = sql.replace("#{accounttoken}",String.valueOf(t.getAccounttoken()));
// 		sql  = sql.replace("#{accountnumber}",String.valueOf(t.getAccountnumber()));
// 		sql  = sql.replace("#{accounttype}",String.valueOf(t.getAccounttype()));
// 		sql  = sql.replace("#{accountemail}",String.valueOf(t.getAccountemail()));
// 		sql  = sql.replace("#{accountdesc}",String.valueOf(t.getAccountdesc()));
// 		sql  = sql.replace("#{accountappid}",String.valueOf(t.getAccountappid()));
// 		sql  = sql.replace("#{accountappsecret}",String.valueOf(t.getAccountappsecret()));
// 		sql  = sql.replace("#{accountaccesstoken}",String.valueOf(t.getAccountaccesstoken()));
// 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
// 		return sql;
// 	}
}