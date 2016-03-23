package weixin.idea.huodong.controller;
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

import weixin.idea.huodong.entity.HuodongEntity;
import weixin.idea.huodong.service.HuodongServiceI;

/**   
 * @Title: Controller
 * @Description: 活动
 * @author zhangdaihao
 * @date 2014-06-06 11:05:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/huodongController")
public class HuodongController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HuodongController.class);

	@Autowired
	private HuodongServiceI huodongService;
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
	 * 活动列表 页面跳转
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		//2：大转盘 1：刮刮乐
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		return new ModelAndView("weixin/idea/huodong/huodong/huodongList");
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
	public void datagrid(HuodongEntity huodong,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String type = request.getParameter("type");
		CriteriaQuery cq = new CriteriaQuery(HuodongEntity.class, dataGrid);
		cq.eq("type", type);
		cq.eq(ACCOUNTID, ResourceUtil.getWeiXinAccountId());
		cq.add();
		org.jeecgframework.core.util.LogUtil.info(".....type....."+type);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, huodong, request.getParameterMap());
		this.huodongService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HuodongEntity huodong, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		huodong = systemService.getEntity(HuodongEntity.class, huodong.getId());
		message = "活动删除成功";
		huodongService.delete(huodong);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加活动
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HuodongEntity huodong, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(huodong.getId())) {
			message = "活动更新成功";
			HuodongEntity t = huodongService.get(HuodongEntity.class, huodong.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(huodong, t);
				huodongService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "活动更新失败";
			}
		} else {
			message = "活动添加成功";
			huodongService.save(huodong);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addOrUpdate")
	public ModelAndView addOrUpdate(HuodongEntity huodong, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(huodong.getId())) {
			huodong = huodongService.getEntity(HuodongEntity.class, huodong.getId());
			req.setAttribute("huodongPage", huodong);
		}
		return new ModelAndView("weixin/idea/huodong/huodong/huodong");
	}
}
