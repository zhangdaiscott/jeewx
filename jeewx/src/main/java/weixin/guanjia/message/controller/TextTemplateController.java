package weixin.guanjia.message.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.TextTemplateServiceI;
import weixin.util.WeiXinConstants;

/**
 * 文本消息
 * 
 */
@Controller
@RequestMapping("/textTemplateController")
public class TextTemplateController {
	
	@Autowired
	private TextTemplateDao textTemplateDao;
	@Autowired
	private TextTemplateServiceI textTemplateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;
	
    /**
     * 转向消息自动回复模板
     * @return
     */
	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/texttemplate/textTemplateList");
	}

	/**
	 * 查询信息列表
	 * @param textTemplate
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public void datagrid(TextTemplate textTemplate, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TextTemplate.class, dataGrid);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				textTemplate);

		this.textTemplateService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除信息
	 * @param textTemplate
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TextTemplate textTemplate,
			HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		textTemplate = this.textTemplateService.getEntity(TextTemplate.class,
				textTemplate.getId());

		this.textTemplateService.delete(textTemplate);

		message = "删除信息数据成功！";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}
	
	/**
	 * 批量删除文本消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "删除信息数据成功";
		int succeed = 0;
		int error = 0;
		try {
			for (String id : ids.split(",")) {
				TextTemplate textTemplate = this.textTemplateService.getEntity(TextTemplate.class,id);
				this.textTemplateService.delete(textTemplate);
				succeed += 1;
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			error += 1;
			message = "删除信息数据失败";
			throw new BusinessException(e.getMessage());
		}
		message="删除信息数据成功"+succeed+"条，失败"+error+"条";
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加或修改消息页面跳转
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorUpdate")
	public ModelAndView addorUpdate(HttpServletRequest req) {
		WeixinAccountEntity weixinAccount = (WeixinAccountEntity) req.getSession().getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
		
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			TextTemplate textTemplate = this.textTemplateService.getEntity(TextTemplate.class, id);
			String templateName = textTemplate.getTemplateName();
			String content = textTemplate.getContent();
			req.setAttribute("accountId", ResourceUtil.getWeiXinAccountId());
			req.setAttribute("templateName", templateName);
			req.setAttribute("content", content);
		}
		return new ModelAndView("weixin/guanjia/texttemplate/textTemplateInfo");
	}

	/**
	 * 保存文本模板修改
	 * @param textTemplate
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(TextTemplate textTemplate,
			HttpServletRequest req) {

		AjaxJson j = new AjaxJson();
		String id = textTemplate.getId();
		if (StringUtil.isNotEmpty(id)) {
			TextTemplate tempAutoResponse = this.textTemplateService.getEntity(
					TextTemplate.class, textTemplate.getId());
			this.message = "修改关文本模板成功！";
			try {
				MyBeanUtils
						.copyBeanNotNull2Bean(textTemplate, tempAutoResponse);
				this.textTemplateService.saveOrUpdate(tempAutoResponse);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				textTemplate.setAddTime(sdf.format(new Date()));
				this.textTemplateService.save(textTemplate);
			} else {
				j.setSuccess(false);
				j.setMsg("请添加一个公众帐号。");
			}
		}
		return j;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}