package weixin.cms.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.cms.entity.AdEntity;
import weixin.cms.service.AdServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;

/**   
 * @Title: Controller
 * @Description: 后台管理：首页广告
 * @author zhangdaihao
 * @date 2014-06-10 20:07:00
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/adController")
public class AdController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AdController.class);

	@Autowired
	private AdServiceI adService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 首页广告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ad")
	public ModelAndView ad(HttpServletRequest request) {
		return new ModelAndView("weixin/cms/adList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(AdEntity ad,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AdEntity.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ad, request.getParameterMap());
		this.adService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除首页广告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AdEntity ad, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ad = systemService.getEntity(AdEntity.class, ad.getId());
		message = "首页广告删除成功";
		adService.delete(ad);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加首页广告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AdEntity ad, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String accountId = ResourceUtil.getWeiXinAccountId();
		if(StringUtil.isEmpty(accountId)||"-1".equals(accountId)){
			j.setSuccess(false);
			message = "请添加一个公众帐号。";
		}else{
			if (StringUtil.isNotEmpty(ad.getId())) {
				message = "首页广告更新成功";
				AdEntity t = adService.get(AdEntity.class, ad.getId());
				try {
					MyBeanUtils.copyBeanNotNull2Bean(ad, t);
					adService.saveOrUpdate(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "首页广告更新失败";
				}
			} else {
				message = "首页广告添加成功";
				ad.setAccountid(accountId);
				adService.save(ad);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 首页广告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AdEntity ad, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ad.getId())) {
			ad = adService.getEntity(AdEntity.class, ad.getId());
			req.setAttribute("adPage", ad);
		}
		return new ModelAndView("weixin/cms/ad");
	}
}
