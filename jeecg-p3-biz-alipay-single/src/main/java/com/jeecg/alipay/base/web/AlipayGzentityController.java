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

import com.jeecg.alipay.base.dao.AlipayGzentityDao;
import com.jeecg.alipay.base.entity.AlipayGzentity;

 /**
 * 描述：</b>AlipayGzentityController<br>关注回复; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 12时04分15秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayGzentity")
public class AlipayGzentityController extends BaseController{
  @Autowired
  private AlipayGzentityDao alipayGzentityDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayGzentity query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayGzentity> list =  alipayGzentityDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayGzentity",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/base/alipayGzentity-list.vm";
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
	public void alipayGzentityDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/base/alipayGzentity-detail.vm";
			AlipayGzentity alipayGzentity = alipayGzentityDao.get(id);
			velocityContext.put("alipayGzentity",alipayGzentity);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/base/alipayGzentity-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayGzentity alipayGzentity){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayGzentity.setId(randomSeed);
		    alipayGzentity.setIsWork("0");
		    alipayGzentity.setCreateDate(new Date());
			alipayGzentityDao.insert(alipayGzentity);
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
			 AlipayGzentity alipayGzentity = alipayGzentityDao.get(id);
			 velocityContext.put("alipayGzentity",alipayGzentity);
			 String viewName = "alipay/base/alipayGzentity-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayGzentity alipayGzentity){
		AjaxJson j = new AjaxJson();
		try {
			alipayGzentityDao.update(alipayGzentity);
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
			    AlipayGzentity alipayGzentity = new AlipayGzentity();
				alipayGzentity.setId(id);
				alipayGzentityDao.delete(alipayGzentity);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	
	/**
	 * 启用
	 * @return
	 */
	@RequestMapping(params="doWorkLock",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doWorkLock(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {
			    AlipayGzentity alipayGzentity = new AlipayGzentity();
				alipayGzentity.setId(id);
				alipayGzentity.setIsWork("1");
				alipayGzentityDao.update(alipayGzentity);
				//同步修改其他为未启动
				alipayGzentityDao.updateOtherisWork(id);
				j.setMsg("启用成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("启用失败");
			}
			return j;
	}

	/**
	 * 停用
	 * @return
	 */
	@RequestMapping(params="doWorkUnLock",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doWorkUnLock(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {
			    AlipayGzentity alipayGzentity = new AlipayGzentity();
				alipayGzentity.setId(id);
				alipayGzentity.setIsWork("0");
				alipayGzentityDao.update(alipayGzentity);
				j.setMsg("停用成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("停用失败");
			}
			return j;
	}

}

