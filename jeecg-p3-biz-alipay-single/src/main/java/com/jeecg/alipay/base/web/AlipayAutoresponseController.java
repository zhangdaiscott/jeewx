package com.jeecg.alipay.base.web;

import java.util.Date;
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

import com.jeecg.alipay.base.dao.AlipayAutoresponseDao;
import com.jeecg.alipay.base.entity.AlipayAutoresponse;
import com.jeecg.alipay.util.SystemUtil;

 /**
 * 描述：</b>AlipayAutoresponseController<br>关键字管理; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 11时33分23秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayAutoresponse")
public class AlipayAutoresponseController extends BaseController{
  @Autowired
  private AlipayAutoresponseDao alipayAutoresponseDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayAutoresponse query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//update-begin--author:zhangjiaqiang Date:20161108 for:增加账号过滤
			 	query.setAccountid(SystemUtil.getOnlieAlipayAccountId());
			 	//update-end--author:zhangjiaqiang Date:20161108 for:增加账号过滤
			 	//分页数据
				MiniDaoPage<AlipayAutoresponse> list =  alipayAutoresponseDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayAutoresponse",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/base/alipayAutoresponse-list.vm";
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
	public void alipayAutoresponseDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/base/alipayAutoresponse-detail.vm";
			AlipayAutoresponse alipayAutoresponse = alipayAutoresponseDao.get(id);
			velocityContext.put("alipayAutoresponse",alipayAutoresponse);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/base/alipayAutoresponse-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayAutoresponse alipayAutoresponse){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayAutoresponse.setId(randomSeed);
		    alipayAutoresponse.setCreateDate(new Date());
			alipayAutoresponseDao.insert(alipayAutoresponse);
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
			 AlipayAutoresponse alipayAutoresponse = alipayAutoresponseDao.get(id);
			 velocityContext.put("alipayAutoresponse",alipayAutoresponse);
			 String viewName = "alipay/base/alipayAutoresponse-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayAutoresponse alipayAutoresponse){
		AjaxJson j = new AjaxJson();
		try {
			alipayAutoresponseDao.update(alipayAutoresponse);
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
			    AlipayAutoresponse alipayAutoresponse = new AlipayAutoresponse();
				alipayAutoresponse.setId(id);
				alipayAutoresponseDao.delete(alipayAutoresponse);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

