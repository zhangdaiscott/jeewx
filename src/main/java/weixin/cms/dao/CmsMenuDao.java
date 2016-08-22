package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;

import weixin.cms.entity.CmsMenuEntity;

@MiniDao
public interface CmsMenuDao{

	@Arguments( { "cmsMenuEntity" })
	@ResultType(CmsMenuEntity.class)
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity);

	@Arguments( { "cmsMenuEntity", "page", "rows" })
	@ResultType(CmsMenuEntity.class)
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity, int page,
			int rows);

	@Arguments( { "params" })
	@ResultType(CmsMenuEntity.class)
	public List<CmsMenuEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType(CmsMenuEntity.class)
	public List<CmsMenuEntity> listByMap(Map params, int page, int rows);

}
