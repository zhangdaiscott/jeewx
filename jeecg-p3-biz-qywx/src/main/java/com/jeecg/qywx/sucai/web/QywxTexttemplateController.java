package com.jeecg.qywx.sucai.web;

import java.util.Date;
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

import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.dao.QywxTexttemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

 /**
 * 描述：</b>QywxTexttemplateController<br>文本模板
 * @author zhoujf
 * @since：2016年03月24日 15时52分18秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxTexttemplate")
public class QywxTexttemplateController extends BaseController{
  @Autowired
  private QywxTexttemplateDao qywxTexttemplateDao;
  @Autowired
  private QywxNewstemplateDao qywxNewstemplateDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxTexttemplate query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxTexttemplate> list =  qywxTexttemplateDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxTexttemplate",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "qywx/sucai/qywxTexttemplate-list.vm";
				ViewVelocity.view(request,response,viewName,velocityContext);
			} catch (Exception e) {
			e.printStackTrace();
			}
}

	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(params="toDetail",method = RequestMethod.GET)
	public void qywxTexttemplateDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/sucai/qywxTexttemplate-detail.vm";
			QywxTexttemplate qywxTexttemplate = qywxTexttemplateDao.get(id);
			velocityContext.put("qywxTexttemplate",qywxTexttemplate);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/sucai/qywxTexttemplate-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxTexttemplate qywxTexttemplate){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxTexttemplate.setId(randomSeed);
		    qywxTexttemplate.setCreateDate(new Date());
			qywxTexttemplateDao.insert(qywxTexttemplate);
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
			 QywxTexttemplate qywxTexttemplate = qywxTexttemplateDao.get(id);
			 velocityContext.put("qywxTexttemplate",qywxTexttemplate);
			 String viewName = "qywx/sucai/qywxTexttemplate-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxTexttemplate qywxTexttemplate){
		AjaxJson j = new AjaxJson();
		try {
			qywxTexttemplateDao.update(qywxTexttemplate);
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
			    QywxTexttemplate qywxTexttemplate = new QywxTexttemplate();
				qywxTexttemplate.setId(id);
				qywxTexttemplateDao.delete(qywxTexttemplate);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}

	
	/**
	 * 查询所有的文本模板
	 * @return
	 */
	@RequestMapping(params="getAllTemplateOption",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getAllQywxTexttemplate(@RequestParam(required = true, value = "type" ) String type){
			AjaxJson j = new AjaxJson();
			try {
				StringBuilder sb = new StringBuilder("");
				if("text".equals(type)){
					List<QywxTexttemplate> list = qywxTexttemplateDao.getAllQywxTexttemplate();
				    if(list!=null&&list.size()>0){
				    	for(QywxTexttemplate po:list){
				    		sb.append("<option value=\"").append(po.getId()).append("\">");
				    		sb.append(po.getTemplateName());
				    		sb.append("</option>");
				    	}
				    }
				}else if("news".equals(type)){
					List<QywxNewstemplate> list = qywxNewstemplateDao.getAllQywxNewstemplate();
				    if(list!=null&&list.size()>0){
				    	for(QywxNewstemplate po:list){
				    		sb.append("<option value=\"").append(po.getId()).append("\">");
				    		sb.append(po.getTemplateName());
				    		sb.append("</option>");
				    	}
				    }
				}
			    j.setObj(sb.toString());
				j.setMsg("成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("失败");
			}
			return j;
	}

}

