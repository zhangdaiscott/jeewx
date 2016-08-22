package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;

import weixin.cms.entity.AdEntity;

@MiniDao
public interface CmsAdDao {

	@Arguments( { "adEntity"})
	@ResultType(AdEntity.class)
	public List<AdEntity> list(AdEntity adEntity);

	@Arguments( { "adEntity", "page", "rows" })
	@ResultType(AdEntity.class)
	public List<AdEntity> list(AdEntity adEntity, int page, int rows);

	@Arguments( { "params"})
	@ResultType(AdEntity.class)
	public List<AdEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType(AdEntity.class)
	public List<AdEntity> listByMap(Map params, int page, int rows);
	
}
