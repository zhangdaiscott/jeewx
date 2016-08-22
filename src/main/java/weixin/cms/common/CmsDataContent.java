package weixin.cms.common;

import java.util.HashMap;
import java.util.Map;

/**
 * CMS数据容器
 * @author zhangdaihao
 *
 */
public class CmsDataContent {
	public static final String CMS_CONTENT_KEY = "cmsData";
	//在cms数据中，加上一层封装
	private static final Map<String,Object> cmsContent = new HashMap<String,Object>();
	private static final Map<String,Object> cmsData = new HashMap<String,Object>();
	
	/**
	 * 保存数据到CMS容器
	 */
	public static void put(String key,Object object){
		cmsData.put(key, object);
	}
	
	/**
	 * 从CMS容器，取值
	 * @param key
	 */
	public static Object get(String key){
		return cmsData.get(key);
	}
	
	/**
	 * 获取CMS容器
	 * @return
	 */
	public static Map<String,Object> loadContent(){
		cmsContent.put(CMS_CONTENT_KEY, cmsData);
		return cmsContent;
	}
}
