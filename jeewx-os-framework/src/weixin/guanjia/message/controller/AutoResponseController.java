package weixin.guanjia.message.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.AutoResponse;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseServiceI;

/**
 * 自动回复关键字
 * 
 */
@Controller
@RequestMapping("/autoResponseController")
public class AutoResponseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private AutoResponseServiceI autoResponseService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;
    
	/*
	 * 转向列表
	 */
	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/autoresponse/autoresponselist");
	}
 
	/**
	 * 消息自动回复
	 * @param autoResponse
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public void datagrid(AutoResponse autoResponse, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(AutoResponse.class, dataGrid);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				autoResponse);
		this.autoResponseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
	}
    
	/**
	 * 删除信息
	 * @param autoResponse
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AutoResponse autoResponse,
			HttpServletRequest req) {
		
		AjaxJson j = new AjaxJson();
		autoResponse = this.autoResponseService.getEntity(AutoResponse.class,
				autoResponse.getId());
		this.autoResponseService.delete(autoResponse);
		message = "删除信息数据成功！";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
		
	}
    
	/**
	 * 自动回复消息
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addOrUpdate")
	public ModelAndView addOrUpdate(HttpServletRequest req) {
		
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		List<TextTemplate> textList = this.autoResponseService
				.findByQueryString("from TextTemplate t where t.accountId = '"
						+ ResourceUtil.getWeiXinAccountId() + "'");
		List<NewsTemplate> newsList = this.autoResponseService
				.findByQueryString("from NewsTemplate t where t.accountId = '"
						+ ResourceUtil.getWeiXinAccountId() + "'");
		req.setAttribute("textList", textList);
		req.setAttribute("newsList", newsList);
		if (StringUtil.isNotEmpty(id)) {
			AutoResponse autoResponse = this.autoResponseService.getEntity(
					AutoResponse.class, id);
			String msgType = autoResponse.getMsgType();
			String resContent = autoResponse.getResContent();
			String keyWord = autoResponse.getKeyWord();
			String templateName = autoResponse.getTemplateName();
			req.setAttribute("msgType", msgType);
			req.setAttribute("resContent", resContent);
			req.setAttribute("keyWord", keyWord);
			req.setAttribute("templateName", templateName);
		}
		return new ModelAndView("weixin/guanjia/autoresponse/autoresponseinfo");
	
	}
	
    /**
     * 保存关键字修改
     * @param autoResponse
     * @param req
     * @return
     */
	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(AutoResponse autoResponse,
			HttpServletRequest req) {
		
		String templateName = "";
		AjaxJson j = new AjaxJson();
		String id = autoResponse.getId();
		if (StringUtil.isNotEmpty(id)) {
			AutoResponse tempAutoResponse = this.autoResponseService.getEntity(
					AutoResponse.class, autoResponse.getId());
			//获取模板文名字
			templateName = getTempName(autoResponse.getMsgType(), autoResponse.getResContent());
			autoResponse.setTemplateName(templateName);
			this.message = "修改关键字回复成功！";
			try {
				MyBeanUtils.copyBeanNotNull2Bean(autoResponse, tempAutoResponse);
				this.autoResponseService.saveOrUpdate(tempAutoResponse);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			autoResponse.setAddTime(sdf.format(new Date()));
			String templateId = autoResponse.getResContent();
			String msgType = autoResponse.getMsgType();
			templateName = getTempName(msgType, templateId);
			autoResponse.setTemplateName(templateName);
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				autoResponse.setAccountId(ResourceUtil.getWeiXinAccountId());
				this.autoResponseService.save(autoResponse);
			} else {
				j.setSuccess(false);
				j.setMsg("请添加一个公众帐号。");
			}
		}
		return j;
		
	}
	
	/**
	 * 获取模板文件名字
	 * @param msgType
	 * @param templateId
	 * @return
	 */
	private String getTempName(String msgType,String templateId){
		
		String templateName  = "";
		if ("text".equals(msgType)) {
			TextTemplate textTemplate = this.autoResponseService.getEntity(
					TextTemplate.class, templateId);
			if (textTemplate != null) {
				templateName = textTemplate.getTemplateName();
			}

		} else if ("news".equals(msgType)) {
			NewsTemplate newsTemplate = this.autoResponseService.getEntity(
					NewsTemplate.class, templateId);
			if (newsTemplate != null) {
				templateName = newsTemplate.getTemplateName();
			}
		}
		return templateName;
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}