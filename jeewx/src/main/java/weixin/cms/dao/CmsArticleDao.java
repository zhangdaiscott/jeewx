package weixin.cms.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;

import weixin.cms.entity.CmsArticleEntity;

@MiniDao
public interface CmsArticleDao{

	@Arguments( { "cmsArticleEntity", "page", "rows" })
	@ResultType(CmsArticleEntity.class)
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity);

	@Arguments( { "cmsArticleEntity", "page", "rows" })
	@ResultType(CmsArticleEntity.class)
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity,
			int page, int rows);

	@Arguments( { "params" })
	@ResultType(CmsArticleEntity.class)
	public List<CmsArticleEntity> listByMap(Map params);

	@Arguments( { "params", "page", "rows" })
	@ResultType(CmsArticleEntity.class)
	public List<CmsArticleEntity> listByMap(Map params, int page, int rows);

	@Arguments( { "params" })
	public Integer getCount(Map params);

}
