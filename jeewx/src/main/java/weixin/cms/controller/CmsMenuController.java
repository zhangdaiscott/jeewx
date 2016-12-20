package weixin.cms.controller;

import java.util.List;

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
import weixin.cms.entity.CmsMenuEntity;
import weixin.cms.service.CmsMenuServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;

/**
 * @Title: Controller
 * @Description: 后台管理：文章标题栏目
 * @author zhangdaihao
 * @date 2014-06-10 20:07:00
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/cmsMenuController")
public class CmsMenuController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CmsMenuController.class);

	@Autowired
	private CmsMenuServiceI cmsMenuService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * cms首页
	 * 
	 * @return
	 */
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request, String accountid,
			String userid) {
		List<CmsMenuEntity> columnList = cmsMenuService.findByProperty(
				CmsMenuEntity.class, "accountid", accountid);
		List<AdEntity> adList = systemService.findByProperty(AdEntity.class,
				"accountid", accountid);
		request.setAttribute("columnList", columnList);
		request.setAttribute("adList", adList);
		request.setAttribute("accountid", accountid);
		request.setAttribute("userid", userid);
		return new ModelAndView("weixin/cms/index");
	}

	/**
	 * 栏目管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "menu")
	public ModelAndView menu(HttpServletRequest request) {
		return new ModelAndView("weixin/cms/cmsMenuList");
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
	public void datagrid(CmsMenuEntity menu, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CmsMenuEntity.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				menu, request.getParameterMap());
		this.cmsMenuService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除栏目管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CmsMenuEntity menu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		menu = systemService.getEntity(CmsMenuEntity.class, menu.getId());
		message = "栏目管理删除成功";
		cmsMenuService.delete(menu);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加栏目管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CmsMenuEntity menu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String accountId = ResourceUtil.getWeiXinAccountId();
		if (StringUtil.isEmpty(accountId) || "-1".equals(accountId)) {
			j.setSuccess(false);
			message = "请添加一个公众帐号。";
		} else {
			if (StringUtil.isNotEmpty(menu.getId())) {
				message = "栏目管理更新成功";
				CmsMenuEntity t = cmsMenuService.get(CmsMenuEntity.class, menu
						.getId());
				try {
					MyBeanUtils.copyBeanNotNull2Bean(menu, t);
					cmsMenuService.saveOrUpdate(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE,
							Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "栏目管理更新失败";
				}
			} else {
				message = "栏目管理添加成功";
				menu.setAccountid(accountId);
				cmsMenuService.save(menu);
				systemService.addLog(message, Globals.Log_Type_INSERT,
						Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 栏目管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CmsMenuEntity menu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(menu.getId())) {
			menu = cmsMenuService
					.getEntity(CmsMenuEntity.class, menu.getId());
			req.setAttribute("columnPage", menu);
		}
		return new ModelAndView("weixin/cms/cmsMenu");
	}
}
