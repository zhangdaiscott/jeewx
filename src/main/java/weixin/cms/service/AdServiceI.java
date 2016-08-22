package weixin.cms.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import weixin.cms.entity.AdEntity;

public interface AdServiceI extends CommonService {

	public List<AdEntity> list(AdEntity adEntity);

	public List<AdEntity> list(AdEntity adEntity, int page, int rows);

	public List<AdEntity> list(Map params, int page, int rows);

	public List<AdEntity> list(Map params);

}
