package weixin.guanjia.message.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import net.sf.json.JSONObject;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.model.UploadGraphic;

public interface NewsTemplateServiceI extends CommonService{
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
 	public boolean doAddSql(NewsTemplate t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(NewsTemplate t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(NewsTemplate t);
 	
 	/**
	 * 上传图文消息接口
	 * @param graphic 图文消息内容
	 * @return
	 */
	public JSONObject uploadNewsTemplate(UploadGraphic graphic);
 	
}
