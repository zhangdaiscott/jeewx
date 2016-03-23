package weixin.cms.common;
/**
 * 
 * @Title:CgReportConstant
 * @description:CMS常用变量
 * @author zhangdaihao
 * @date Jul 30, 2013 9:16:18 AM
 * @version V1.0
 */
public class CmsConstant {
	//******************************************************************************
	/**CMS功能代码使用参数*/
	/**CMS模板根路径 web层面*/
	public static final String CMS_ROOT_URL = "/template/cms";
	/**CMS模板根路径 物理文件层面*/
	public static final String CMS_ROOT_PATH = "template/cms";
	/**站点默认样式 */
	public static final String CMS_DEFAULT_STYLE = "/default/html/";
	/**模板包 */
	public static final String CMS_TEMPL_PACKAGE = "/html/";
	/** 模板文件后缀 */
	public static final String CMS_TEMPL_INDEX = ".html";
	/**站点默认模板 */
	public static final String CMS_DEFAULT_TEMPLATE = "default";
	/**站点模板名称 */
	public static final String CMS_STYLE_NAME = "styleName";
	//******************************************************************************
	/**数据容器,数据的KEY*/
	/**数据容器-广告位列表*/
	public static final String CMS_DATA_LIST_AD = "adList";
	/**数据容器-栏目列表*/
	public static final String CMS_DATA_LIST_MENU = "menuList";
	/**数据容器-文章列表*/
	public static final String CMS_DATA_LIST_ARTICLE = "articleList";
	/**数据容器-栏目*/
	public static final String CMS_DATA_MAP_MENU = "menu";
	/**数据容器-描述*/
	public static final String CMS_DATA_STR_TITLE = "title";
	/**数据容器-文章详细*/
	public static final String CMS_DATA_MAP_ARTICLE = "article";
	
	
	
	//******************************************************************************
	/*CMS入口, 访问页面名字*/
	public static final String CMS_PAGE_INDEX = "index";
	public static final String CMS_PAGE_MENU = "menu";
	public static final String CMS_PAGE_ARTICLE = "article";
	
	
	/**微相册功能代码使用参数*/
	/**微相册模板根路径 add by liuqiang 2014-08-04 FOR photo*/
	public static final String CMS_PHOTO_ROOT_URL = "/template/photo";
	/**微相册入口, 访问页面名字*/
	public static final String CMS_PHOTO_ALBUM = "photoAlbum";
	public static final String CMS_PHOTO = "photo";
	/**数据容器-相册列表*/
	public static final String CMS_DATA_LIST_PHOTO_ALBUM = "photoAlbumList";
	/**数据容器-相片列表*/
	public static final String CMS_DATA_LIST_PHOTO = "photoList";
	/**资源相对路径*/
	public static final String BASE = "base";
	/**微相册默认样式 */
	public static final String CMS_PHOTO_DEFAULT_STYLE = "default";
	/**微相微模板根路径*/
	public static final String CMS_PHOTO_ROOT_PATH = "template/photo";
}
