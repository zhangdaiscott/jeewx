package org.jeecgframework.core.util;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.util.privilegedactions.GetAnnotationParameter;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.util.WeiXinConstants;


/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	/**
	 * 获取session定义名称
	 * 
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return bundle.getString(sessionName);
	}
	public static final TSUser getSessionUserName() {
		HttpSession session = ContextHolderUtils.getSession();
		if(ClientManager.getInstance().getClient(session.getId())!=null){
			return ClientManager.getInstance().getClient(session.getId()).getUser();
		}
		return null;
	}
	
	/**
	 * 获取登录用户微信账号信息
	 * @return
	 */
	public static final WeixinAccountEntity getWeiXinAccount() {
		HttpSession session = ContextHolderUtils.getSession();
		if(session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT)!=null){
			WeixinAccountEntity WeixinAccountEntity = (weixin.guanjia.account.entity.WeixinAccountEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			return WeixinAccountEntity;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取登录用户微信账号信息
	 * @return
	 */
	public static final String getShangJiaAccountId() {
		HttpSession session = ContextHolderUtils.getSession();
		if(session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT)!=null){
			WeixinAccountEntity weixinAccountEntity = (weixin.guanjia.account.entity.WeixinAccountEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			return weixinAccountEntity.getId();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取登录用户微信账号ID
	 * @return
	 */
	public static final String getWeiXinAccountId() {
		HttpSession session = ContextHolderUtils.getSession();
		if(session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT)!=null){
			WeixinAccountEntity weixinAccountEntity = (weixin.guanjia.account.entity.WeixinAccountEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			return weixinAccountEntity.getId();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取浏览用户的openId
	 * @return
	 */
	public static final String getUserOpenId() {
		HttpSession session = ContextHolderUtils.getSession();
		Object userOpenId = session.getAttribute(WeiXinConstants.USER_OPENID);
		if(userOpenId!=null){
			return userOpenId.toString();
		}else{
			return null;
		}
	} 
	
	@Deprecated
	public static final List<TSRoleFunction> getSessionTSRoleFunction(String roleId) {
		HttpSession session = ContextHolderUtils.getSession();
		if (session.getAttributeNames().hasMoreElements()) {
			List<TSRoleFunction> TSRoleFunctionList = (List<TSRoleFunction>)session.getAttribute(roleId);
			if (TSRoleFunctionList != null) {
				return TSRoleFunctionList;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}
	
	/**
	 * 没有登录，跳转到登陆界面，获得登录前的url
	 * @param request
	 * @return
	 */
	public static String getRedirUrl(HttpServletRequest request){
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
	public static final Map<Object, Object> getConfigMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Set set = bundle.keySet();
		return oConvertUtils.SetToMap(set);
	}

	
	
	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		return request.getParameter(field);
	}

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static final String getJdbcUrl() {
		return DBTypeUtil.getDBType().toLowerCase();
	}

    /**
     * 获取随机码的长度
     *
     * @return 随机码的长度
     */
    public static String getRandCodeLength() {
        return bundle.getString("randCodeLength");
    }

    /**
     * 获取随机码的类型
     *
     * @return 随机码的类型
     */
    public static String getRandCodeType() {
        return bundle.getString("randCodeType");
    }
    
    /**
	 * 获取商家的账号ID
	 * 对应着微信公众账号
	 * @return
	 */
	public static final String getOpenid(HttpServletRequest request) {
		String openid = request.getParameter("openid");
		if(openid!=null){
			return openid;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		org.jeecgframework.core.util.LogUtil.info(getPorjectPath());
		org.jeecgframework.core.util.LogUtil.info(getSysPath());

	}
}
