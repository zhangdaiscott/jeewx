package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.hibernate.MiniDaoSupportHiber;

import weixin.cms.entity.AdEntity;

@MiniDao
public interface CmsAdDao extends MiniDaoSupportHiber<AdEntity> {

	@Arguments( { "adEntity"})
	@ResultType("weixin.cms.entity.AdEntity")
	public List<AdEntity> list(AdEntity adEntity);

	@Arguments( { "adEntity", "page", "rows" })
	@ResultType("weixin.cms.entity.AdEntity")
	public List<AdEntity> list(AdEntity adEntity, int page, int rows);

	@Arguments( { "params"})
	@ResultType("weixin.cms.entity.AdEntity")
	public List<AdEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType("weixin.cms.entity.AdEntity")
	public List<AdEntity> listByMap(Map params, int page, int rows);
	
}
