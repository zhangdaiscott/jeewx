package weixin.cms.cmsdata.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.cms.cmsdata.CmsDataCollectI;
import weixin.cms.common.CmsConstant;
import weixin.cms.common.CmsDataContent;
import weixin.idea.photo.entity.WeixinPhotoAlbumEntity;
import weixin.idea.photo.entity.WeixinPhotoEntity;
import weixin.idea.photo.service.WeixinPhotoAlbumServiceI;

/**
 * CMS相册相片数据收集器
 * @author liuqiang
 *
 */
public class CmsPhotoDataCollect implements CmsDataCollectI {

	@Override
	public void collect(Map<String, String> params) {
		WeixinPhotoAlbumServiceI weixinPhotoAlbumService = (WeixinPhotoAlbumServiceI) ApplicationContextUtil.getContext().getBean("weixinPhotoAlbumService");
		String id = params.get("id");
		WeixinPhotoAlbumEntity weixinPhotoAlbum = weixinPhotoAlbumService.getEntity(WeixinPhotoAlbumEntity.class, id);
		List<WeixinPhotoEntity> photos = weixinPhotoAlbum.getPhotos();

		//相片列表
		CmsDataContent.put(CmsConstant.CMS_DATA_LIST_PHOTO, photos);
		//资源模板先用default 待以后模板功能做好，统一动态传入
		String res = CmsConstant.CMS_PHOTO_ROOT_PATH + "/" + CmsConstant.CMS_PHOTO_DEFAULT_STYLE;
		//资源路径
		CmsDataContent.put(CmsConstant.BASE, res);
	}

}
