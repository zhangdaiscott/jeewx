package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.hibernate.MiniDaoSupportHiber;

import weixin.cms.entity.CmsMenuEntity;

@MiniDao
public interface CmsMenuDao extends MiniDaoSupportHiber<CmsMenuEntity> {

	@Arguments( { "cmsMenuEntity" })
	@ResultType("weixin.cms.entity.CmsMenuEntity")
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity);

	@Arguments( { "cmsMenuEntity", "page", "rows" })
	@ResultType("weixin.cms.entity.CmsMenuEntity")
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity, int page,
			int rows);

	@Arguments( { "params" })
	@ResultType("weixin.cms.entity.CmsMenuEntity")
	public List<CmsMenuEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType("weixin.cms.entity.CmsMenuEntity")
	public List<CmsMenuEntity> listByMap(Map params, int page, int rows);

}
