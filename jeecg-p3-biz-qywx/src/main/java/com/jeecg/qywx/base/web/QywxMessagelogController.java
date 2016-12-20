package com.jeecg.qywx.base.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.qywx.account.dao.QywxAccountDao;
import com.jeecg.qywx.account.dao.QywxAgentDao;
import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.base.entity.QywxGroup;
import com.jeecg.qywx.base.entity.QywxLocation;
import com.jeecg.qywx.base.entity.QywxMessagelog;
import com.jeecg.qywx.base.dao.QywxGroupDao;
import com.jeecg.qywx.base.dao.QywxMessagelogDao;
import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;

 /**
 * 描述：</b>QywxMessagelogController<br>
 * @author p3.jeecg
 * @since：2016年05月26日 18时54分38秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxMessagelog")
public class QywxMessagelogController extends BaseController{
  @Autowired
  private QywxMessagelogDao qywxMessagelogDao;
  @Autowired
  private QywxAgentDao qywxAgentDao;
  @Autowired
  private QywxNewstemplateDao qywxNewstemplateDao;
  @Autowired
  private QywxGroupDao qywxGroupDao;
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxMessagelog query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<QywxMessagelog> list = qywxMessagelogDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			for (QywxMessagelog messagelogPo : list.getResults()) {
				String agentid = messagelogPo.getWxAgentId();//应用id
				String contentId = messagelogPo.getContentId();//内容id
				String topartysId = messagelogPo.getTopartysId();//部门id
				// 企业应用id字段
				String agentidname = null;
				if (agentid == null || "".equals(agentid)) {
					agentidname = null;
				} else {
					QywxAgent agent = qywxAgentDao.getAgent(agentid);
					if (agent != null) {
						agentidname = agent.getAgentName();
					}
					messagelogPo.setWxAgentId(agentidname);
				}
				// 内容contentId字段对应的名字
				String templateName = null;
				if (contentId == null || "".equals(contentId)) {
					templateName = null;
				} else {

					QywxNewstemplate qywxNewstemplate = qywxNewstemplateDao.get(contentId);
					if (qywxNewstemplate != null) {
						templateName = qywxNewstemplate.getTemplateName();
					}
				}
				messagelogPo.setContentId(templateName);
				// 显示部门id对应的字段
				String topartyname = null;
				if (topartysId == null || "".equals(topartysId)) {
					topartyname = null;
				} else {
					String names[] = topartysId.split(",");
					StringBuffer sbname = new StringBuffer();
					for (int i = 0; i < names.length; i++) {
						QywxGroup qywxGroup = qywxGroupDao.get(names[i]);
						if (qywxGroup != null) {
							String name = qywxGroup.getName();
							sbname.append(name + ",");
						}
					String stringname = sbname.toString();
					if(stringname==null||stringname.equals("")){
						topartyname = null;
					}else{
					topartyname = stringname.substring(0, stringname.length() - 1);
					}
					}
				}
				messagelogPo.setTopartysId(topartyname);
			}
			velocityContext.put("qywxMessagelog", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "qywx/base/qywxMessagelog-list.vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(params="toDetail",method = RequestMethod.GET)
	public void qywxMessagelogDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/base/qywxMessagelog-detail.vm";
			QywxMessagelog qywxMessagelog = qywxMessagelogDao.get(id);
			velocityContext.put("qywxMessagelog",qywxMessagelog);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/base/qywxMessagelog-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxMessagelog qywxMessagelog){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxMessagelog.setId(randomSeed);
			qywxMessagelogDao.insert(qywxMessagelog);
			j.setMsg("保存成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(params="toEdit",method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 QywxMessagelog qywxMessagelog = qywxMessagelogDao.get(id);
			 velocityContext.put("qywxMessagelog",qywxMessagelog);
			 String viewName = "qywx/base/qywxMessagelog-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxMessagelog qywxMessagelog){
		AjaxJson j = new AjaxJson();
		try {
			qywxMessagelogDao.update(qywxMessagelog);
			j.setMsg("编辑成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(params="doDelete",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {
			    QywxMessagelog qywxMessagelog = new QywxMessagelog();
				qywxMessagelog.setId(id);
				qywxMessagelogDao.delete(qywxMessagelog);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

