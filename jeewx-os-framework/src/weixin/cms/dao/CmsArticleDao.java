package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.hibernate.MiniDaoSupportHiber;

import weixin.cms.entity.AdEntity;
import weixin.cms.entity.CmsArticleEntity;

@MiniDao
public interface CmsArticleDao extends MiniDaoSupportHiber<CmsArticleEntity> {

	@Arguments( { "cmsArticleEntity", "page", "rows" })
	@ResultType("weixin.cms.entity.CmsArticleEntity")
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity);

	@Arguments( { "cmsArticleEntity", "page", "rows" })
	@ResultType("weixin.cms.entity.CmsArticleEntity")
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity,
			int page, int rows);

	@Arguments( { "params" })
	@ResultType("weixin.cms.entity.CmsArticleEntity")
	public List<CmsArticleEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType("weixin.cms.entity.CmsArticleEntity")
	public List<CmsArticleEntity> listByMap(Map params, int page, int rows);

	@Arguments( { "params" })
	@ResultType("java.lang.Integer")
	public Integer getCount(Map params);

}
