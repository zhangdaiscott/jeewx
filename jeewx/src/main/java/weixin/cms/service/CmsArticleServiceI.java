package weixin.cms.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.ResultType;

import weixin.cms.entity.CmsArticleEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;

public interface CmsArticleServiceI extends CommonService {
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity);

	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity,
			int page, int rows);

	@SuppressWarnings("unchecked")
	public List<CmsArticleEntity> listByMap(Map params);

	public List<CmsArticleEntity> listByMap(Map params, int page, int rows);

	public int getCount(Map params);
	
	public CmsArticleEntity getCmsArticleEntity(String id);
}
