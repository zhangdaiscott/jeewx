package weixin.cms.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.cms.dao.CmsMenuDao;
import weixin.cms.entity.CmsArticleEntity;
import weixin.cms.entity.CmsMenuEntity;
import weixin.cms.service.CmsMenuServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;

@Service("cmsMenuService")
@Transactional
public class CmsMenuServiceImpl extends CommonServiceImpl implements
		CmsMenuServiceI {
	@Autowired
	private CmsMenuDao cmsArticleDao;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@Override
	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		return t;
	}

	@Override
	public List<CmsMenuEntity> list(Map params, int page, int rows) {
		return cmsArticleDao.listByMap(params, page, rows);
	}

	@Override
	public List<CmsMenuEntity> list(Map params) {
		return cmsArticleDao.listByMap(params);
	}

	@Override
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity) {
		return cmsArticleDao.list(cmsMenuEntity);
	}

	@Override
	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity, int page,
			int rows) {
		// TODO Auto-generated method stub
		return cmsArticleDao.list(cmsMenuEntity);
	}

}