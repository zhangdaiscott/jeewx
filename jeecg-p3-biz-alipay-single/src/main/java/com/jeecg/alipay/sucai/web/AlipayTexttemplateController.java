package com.jeecg.alipay.sucai.web;

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

import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.dao.AlipayTexttemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;
import com.jeecg.alipay.util.SystemUtil;

 /**
 * 描述：</b>AlipayTexttemplateController<br>文本模板
 * @author zhoujf
 * @since：2016年03月24日 15时52分18秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayTexttemplate")
public class AlipayTexttemplateController extends BaseController{
  @Autowired
  private AlipayTexttemplateDao alipayTexttemplateDao;
  @Autowired
  private AlipayNewstemplateDao alipayNewstemplateDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayTexttemplate query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayTexttemplate> list =  alipayTexttemplateDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayTexttemplate",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/sucai/alipayTexttemplate-list.vm";
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
	public void alipayTexttemplateDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/sucai/alipayTexttemplate-detail.vm";
			AlipayTexttemplate alipayTexttemplate = alipayTexttemplateDao.get(id);
			velocityContext.put("alipayTexttemplate",alipayTexttemplate);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/sucai/alipayTexttemplate-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayTexttemplate alipayTexttemplate){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayTexttemplate.setId(randomSeed);
		    alipayTexttemplate.setCreateDate(new Date());
			alipayTexttemplateDao.insert(alipayTexttemplate);
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
			 AlipayTexttemplate alipayTexttemplate = alipayTexttemplateDao.get(id);
			 velocityContext.put("alipayTexttemplate",alipayTexttemplate);
			 String viewName = "alipay/sucai/alipayTexttemplate-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayTexttemplate alipayTexttemplate){
		AjaxJson j = new AjaxJson();
		try {
			alipayTexttemplateDao.update(alipayTexttemplate);
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
			    AlipayTexttemplate alipayTexttemplate = new AlipayTexttemplate();
				alipayTexttemplate.setId(id);
				alipayTexttemplateDao.delete(alipayTexttemplate);
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
	public AjaxJson getAllAlipayTexttemplate(@RequestParam(required = true, value = "type" ) String type){
			AjaxJson j = new AjaxJson();
			try {
				StringBuilder sb = new StringBuilder("");
				if("text".equals(type)){
					List<AlipayTexttemplate> list = alipayTexttemplateDao.getAllAlipayTexttemplate(SystemUtil.getOnlieAlipayAccountId());
				    if(list!=null&&list.size()>0){
				    	for(AlipayTexttemplate po:list){
				    		sb.append("<option value=\"").append(po.getId()).append("\">");
				    		sb.append(po.getTemplateName());
				    		sb.append("</option>");
				    	}
				    }
				}else if("news".equals(type)){
					//update-begin--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
					List<AlipayNewstemplate> list = alipayNewstemplateDao.getAllAlipayNewstemplate(SystemUtil.getOnlieAlipayAccountId());
					//update-end--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
					if(list!=null&&list.size()>0){
				    	for(AlipayNewstemplate po:list){
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

