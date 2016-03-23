package weixin.guanjia.base.controller;

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
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.base.service.SubscribeServiceI;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;

/**
 * 关注管理
 * 
 */
@Controller
@RequestMapping("/subscribeController")
public class SubscribeController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private SubscribeServiceI subscribeService;
	private String message;

	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/gz/gzList");
	}

	@RequestMapping(params = "datagrid")
	@ResponseBody
	public void datagrid(Subscribe subscribe, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Subscribe.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				subscribe);
		this.subscribeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson deleteSmsGroup(Subscribe subscribe, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		subscribe = this.subscribeService.getEntity(Subscribe.class, subscribe
				.getId());

		this.subscribeService.delete(subscribe);

		message = "删除信息数据成功！";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}

	@RequestMapping(params = "jumpSuView")
	public ModelAndView jumpSuView(HttpServletRequest req) {
		String id = req.getParameter("id");
		org.jeecgframework.core.util.LogUtil.info("...id..." + id);
		req.setAttribute("id", id);
		List<TextTemplate> textList = this.subscribeService
				.findByQueryString("from TextTemplate t where t.accountId = '"
						+ ResourceUtil.getWeiXinAccountId() + "'");
		List<NewsTemplate> newsList = this.subscribeService
				.findByQueryString("from NewsTemplate t where t.accountId = '"
						+ ResourceUtil.getWeiXinAccountId() + "'");
		req.setAttribute("textList", textList);
		req.setAttribute("newsList", newsList);
		if (StringUtil.isNotEmpty(id)) {
			Subscribe subscribe = this.subscribeService.getEntity(
					Subscribe.class, id);
			String lx = subscribe.getMsgType();
			String templateId = subscribe.getTemplateId();
			req.setAttribute("lx", lx);
			req.setAttribute("templateId", templateId);
			req.setAttribute("subscribe", subscribe);
		}
		return new ModelAndView("weixin/guanjia/gz/gz");
	}

	@RequestMapping(params = "su")
	@ResponseBody
	public AjaxJson su(Subscribe subscribe, HttpServletRequest req) {
		String accountId = ResourceUtil.getWeiXinAccountId();
		AjaxJson j = new AjaxJson();
		String id = subscribe.getId();
		if (StringUtil.isNotEmpty(id)) {

			Subscribe tempAutoResponse = this.subscribeService.getEntity(
					Subscribe.class, subscribe.getId());
			this.message = "修改关文本模板成功！";
			try {
				MyBeanUtils.copyBeanNotNull2Bean(subscribe, tempAutoResponse);
				this.subscribeService.saveOrUpdate(tempAutoResponse);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//判断当前公众帐号是否已经配置欢迎语
			int size = subscribeService.findByProperty(Subscribe.class, "accountid", accountId).size();				
			//如果集合不为0
			if(size!=0){
				j.setSuccess(false);
				j.setMsg("每个公众帐号只能配置一个欢迎语。");
				return j;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			subscribe.setAddTime(sdf.format(new Date()));
			String templateId = subscribe.getTemplateId();
			String msgType = subscribe.getMsgType();
			String templateName = "";
			if ("text".equals(msgType)) {
				TextTemplate textTemplate = this.subscribeService.getEntity(TextTemplate.class, templateId);
				if (textTemplate != null) {
					templateName = textTemplate.getTemplateName();
				}
			} else if ("news".equals(msgType)) {
				NewsTemplate newsTemplate = this.subscribeService.getEntity(NewsTemplate.class, templateId);
				if (newsTemplate != null) {
					templateName = newsTemplate.getTemplateName();
				}
			}
			org.jeecgframework.core.util.LogUtil.info(".....templateName......"+templateName);
			subscribe.setTemplateName(templateName);
			subscribe.setAccountid(ResourceUtil.getWeiXinAccountId());
			if (!"-1".equals(accountId)) {
				this.subscribeService.save(subscribe);
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