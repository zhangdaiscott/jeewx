package weixin.guanjia.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.util.WeiXinConstants;

/**
 * @Title: Controller
 * @Description: 微信公众帐号信息
 * @author onlineGenerator
 * @date 2014-05-21 00:53:47
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/weixinAccountController")
public class WeixinAccountController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(WeixinAccountController.class);

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
	 * 微信公众帐号信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinAccount")
	public ModelAndView weixinAccount(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/account/weixinAccountList");
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
	public void datagrid(WeixinAccountEntity weixinAccount,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinAccountEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinAccount, request.getParameterMap());
		cq.eq("userName", ResourceUtil.getSessionUserName().getUserName());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinAccountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微信公众帐号信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinAccountEntity weixinAccount,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinAccount = systemService.getEntity(WeixinAccountEntity.class,
				weixinAccount.getId());
		message = "微信公众帐号信息删除成功";
		try {
			weixinAccountService.delete(weixinAccount);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信公众帐号信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除微信公众帐号信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信公众帐号信息删除成功";
		try {
			for (String id : ids.split(",")) {
				WeixinAccountEntity weixinAccount = systemService.getEntity(
						WeixinAccountEntity.class, id);
				weixinAccountService.delete(weixinAccount);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信公众帐号信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加微信公众帐号信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinAccountEntity weixinAccount,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信公众帐号信息添加成功";
		try {
			// 判断当前帐号是否已经添加微信公众账户
			int f = weixinAccountService.findByUsername(
					ResourceUtil.getSessionUserName().getUserName()).size();
			if (f == 0) {
				weixinAccount.setUserName(ResourceUtil.getSessionUserName()
						.getUserName());
				weixinAccountService.save(weixinAccount);
				systemService.addLog(message, Globals.Log_Type_INSERT,
						Globals.Log_Leavel_INFO);
				//重置session中的微信公众帐号ID
				List<WeixinAccountEntity> acclst = weixinAccountService.findByProperty(WeixinAccountEntity.class, "accountnumber", weixinAccount.getAccountnumber());
				request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, acclst.get(0));
			} else {
				message = "微信公众帐号信息添加失败,每个用户只能添加一个微信公众帐号";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信公众帐号信息添加失败";
			throw new BusinessException(e.getMessage());
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 更新微信公众帐号信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinAccountEntity weixinAccount,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信公众帐号信息更新成功";
		WeixinAccountEntity t = weixinAccountService.get(
				WeixinAccountEntity.class, weixinAccount.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinAccount, t);
			weixinAccountService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信公众帐号信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		WeixinAccountEntity  weixinAccountEntity = ResourceUtil.getWeiXinAccount();
		request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
		return j;
	}

	/**
	 * 微信公众帐号信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinAccountEntity weixinAccount,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinAccount.getId())) {
			weixinAccount = weixinAccountService.getEntity(
					WeixinAccountEntity.class, weixinAccount.getId());
			req.setAttribute("weixinAccountPage", weixinAccount);
		}
		return new ModelAndView("weixin/guanjia/account/weixinAccount-add");
	}

	/**
	 * 微信公众帐号信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinAccountEntity weixinAccount,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinAccount.getId())) {
			weixinAccount = weixinAccountService.getEntity(
					WeixinAccountEntity.class, weixinAccount.getId());
			req.setAttribute("weixinAccountPage", weixinAccount);
		}
		return new ModelAndView("weixin/guanjia/account/weixinAccount-update");
	}
	
	/**
	 * 重置Token
	 * @param request
	 * @param accountid
	 * @return
	 * @throws WexinReqException 
	 */
	@RequestMapping(params = "doResetAccessToken")
	@ResponseBody
	public AjaxJson doResetAccessToken(HttpServletRequest request,String accountid,String relationid) throws Exception{
		return weixinAccountService.resetAccessToken(accountid);
	}
}
