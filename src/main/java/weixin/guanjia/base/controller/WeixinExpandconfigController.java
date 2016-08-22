package weixin.guanjia.base.controller;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.base.entity.WeixinExpandconfigEntity;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;

/**   
 * @Title: Controller
 * @Description: 扩展接口管理
 * @author onlineGenerator
 * @date 2014-06-04 23:21:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/weixinExpandconfigController")
public class WeixinExpandconfigController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinExpandconfigController.class);

	@Autowired
	private WeixinExpandconfigServiceI weixinExpandconfigService;
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
	 * 扩展接口管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinExpandconfig")
	public ModelAndView weixinExpandconfig(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/base/expandconfig/weixinExpandconfigList");
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
	public void datagrid(WeixinExpandconfigEntity weixinExpandconfig,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinExpandconfigEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinExpandconfig, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinExpandconfigService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除扩展接口管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinExpandconfigEntity weixinExpandconfig, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinExpandconfig = systemService.getEntity(WeixinExpandconfigEntity.class, weixinExpandconfig.getId());
		message = "扩展接口管理删除成功";
		try{
			weixinExpandconfigService.delete(weixinExpandconfig);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "扩展接口管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除扩展接口管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "扩展接口管理删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinExpandconfigEntity weixinExpandconfig = systemService.getEntity(WeixinExpandconfigEntity.class, id);
				weixinExpandconfigService.delete(weixinExpandconfig);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "扩展接口管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加扩展接口管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinExpandconfigEntity weixinExpandconfig, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "扩展接口管理添加成功";
		try{
			weixinExpandconfig.setAccountid(ResourceUtil.getWeiXinAccountId());
			weixinExpandconfigService.save(weixinExpandconfig);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "扩展接口管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新扩展接口管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinExpandconfigEntity weixinExpandconfig, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "扩展接口管理更新成功";
		WeixinExpandconfigEntity t = weixinExpandconfigService.get(WeixinExpandconfigEntity.class, weixinExpandconfig.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinExpandconfig, t);
			weixinExpandconfigService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "扩展接口管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 扩展接口管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinExpandconfigEntity weixinExpandconfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinExpandconfig.getId())) {
			weixinExpandconfig = weixinExpandconfigService.getEntity(WeixinExpandconfigEntity.class, weixinExpandconfig.getId());
			req.setAttribute("weixinExpandconfigPage", weixinExpandconfig);
		}
		return new ModelAndView("weixin/guanjia/base/expandconfig/weixinExpandconfig-add");
	}
	/**
	 * 扩展接口管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinExpandconfigEntity weixinExpandconfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinExpandconfig.getId())) {
			weixinExpandconfig = weixinExpandconfigService.getEntity(WeixinExpandconfigEntity.class, weixinExpandconfig.getId());
			req.setAttribute("weixinExpandconfigPage", weixinExpandconfig);
		}
		return new ModelAndView("weixin/guanjia/base/expandconfig/weixinExpandconfig-update");
	}
}
