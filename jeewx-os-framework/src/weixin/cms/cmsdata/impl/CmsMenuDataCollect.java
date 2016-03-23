package weixin.cms.cmsdata.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyBeanUtils;

import weixin.cms.cmsdata.CmsDataCollectI;
import weixin.cms.common.CmsConstant;
import weixin.cms.common.CmsDataContent;
import weixin.cms.entity.CmsArticleEntity;
import weixin.cms.entity.CmsMenuEntity;
import weixin.cms.service.CmsArticleServiceI;
import weixin.cms.service.CmsMenuServiceI;

/**
 * CMS 栏目数据收集器
 * @author zhangdaihao
 *
 */
public class CmsMenuDataCollect implements CmsDataCollectI {
	
	@Override
	public void collect(Map<String, String> params) {
		CmsArticleServiceI cmsArticleService = (CmsArticleServiceI) ApplicationContextUtil.getContext().getBean("cmsArticleService");
		CmsMenuServiceI cmsMenuService =  (CmsMenuServiceI) ApplicationContextUtil.getContext().getBean("cmsMenuService");
		
		String menuid = params.get("id") != null ? params.get("id").toString(): "-";
		CmsMenuEntity menuEntity = cmsMenuService.getEntity(CmsMenuEntity.class, menuid);
		CmsArticleEntity cmsArticleEntity = null;
		if (menuEntity != null) {
			//------------------------------------------------------------------
			Map p = new HashMap();
			p.put("columnid", menuEntity.getId());
			List<CmsArticleEntity> list = cmsArticleService.listByMap(p);
			//判断Menu类型
			if("02".equals(menuEntity.getType())){
				//单文章类型，则加载排序第一条文章
				if(list!=null&&list.size()>0){
					 cmsArticleEntity = list.get(0);
				}
			} else {
				CmsDataContent.put(CmsConstant.CMS_DATA_LIST_ARTICLE, list);
			}
			Map valueMap = new HashMap();
			MyBeanUtils.copyBean2Map(valueMap, menuEntity);
			if(cmsArticleEntity==null)cmsArticleEntity = new CmsArticleEntity();
			valueMap.put("article", cmsArticleEntity);
			//------------------------------------------------------------------
			CmsDataContent.put(CmsConstant.CMS_DATA_MAP_MENU, valueMap);
			CmsDataContent.put(CmsConstant.CMS_DATA_STR_TITLE, menuEntity.getName());
		} else {
			CmsDataContent.put(CmsConstant.CMS_DATA_MAP_MENU, new CmsMenuEntity());
			CmsDataContent.put(CmsConstant.CMS_DATA_STR_TITLE, "信息列表");
		}
		String res = CmsConstant.CMS_ROOT_PATH + "/" + params.get(CmsConstant.CMS_STYLE_NAME);
		//资源路径
		CmsDataContent.put(CmsConstant.BASE, res);
	}

}
