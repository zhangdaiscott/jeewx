package com.jeecg.qywx.base.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.qywx.base.dao.QywxGroupDao;
import com.jeecg.qywx.base.dao.QywxGzuserinfoDao;
import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.base.service.QywxGzuserinfoService;

 /**
 * 描述：</b>QywxGzuserinfoController<br>关注用户; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 15时24分11秒 星期五 
 * @version:1.0
 * 序号		修改人	日期			备注
 * 1		yangqj	2016-04-19	与微信接口同步，修改的方法有
 */
@Controller
@RequestMapping("/qywx/qywxGzuserinfo")
public class QywxGzuserinfoController extends BaseController{
  @Autowired
  private QywxGzuserinfoDao qywxGzuserinfoDao;
  
  @Autowired
  private QywxGzuserinfoService qywxGzuserinfoService;
  
  @Autowired
  private QywxGroupDao qywxGroupDao;
  
  	@RequestMapping(params = "combobox",method = {RequestMethod.GET})
  	@ResponseBody
  	public List<QywxGzuserinfo> combobox(@ModelAttribute QywxGzuserinfo query,HttpServletRequest request,HttpServletResponse response){
  		List<QywxGzuserinfo> userlist = this.qywxGzuserinfoDao.getAllUser(query);
  		return userlist;
  	}
  
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxGzuserinfo query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxGzuserinfo> list =  qywxGzuserinfoDao.getAll(query,pageNo,pageSize);
				
				//TODO
				for(QywxGzuserinfo t:list.getResults()){
					//循环获取对应的部门名称
					if(!StringUtils.isBlank(t.getDepartment())){
						t.setDepartment(qywxGroupDao.get(t.getDepartment()).getName());
					}
				}
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxGzuserinfo",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "qywx/base/qywxGzuserinfo-list.vm";
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
	public void qywxGzuserinfoDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/base/qywxGzuserinfo-detail.vm";
			QywxGzuserinfo qywxGzuserinfo = qywxGzuserinfoDao.get(id);
			velocityContext.put("qywxGzuserinfo",qywxGzuserinfo);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/base/qywxGzuserinfo-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request , @ModelAttribute QywxGzuserinfo qywxGzuserinfo){
		AjaxJson j = new AjaxJson();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    MultipartFile uploadfile = multipartRequest.getFile("avatar_file");
			//对数据进行校验
			//1.1身份认证信息（微信号/手机/邮箱）不能同时为空
			if(StringUtils.isBlank(qywxGzuserinfo.getWeixinid()) && StringUtils.isBlank(qywxGzuserinfo.getMobile()) && StringUtils.isBlank(qywxGzuserinfo.getEmail())){
				throw new Exception("微信号/手机/邮箱 不能同时为空");
			}
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxGzuserinfo.setId(randomSeed);
		    qywxGzuserinfo.setCreateDate(new Date());
		    
		  
		    
		    qywxGzuserinfoService.saveGzuserinfo(qywxGzuserinfo,uploadfile, request);
//			qywxGzuserinfoDao.insert(qywxGzuserinfo);
			//还需要对微信接口进行调整
			j.setMsg("保存成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败，详细原因："+e.getMessage());
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
			 QywxGzuserinfo qywxGzuserinfo = qywxGzuserinfoDao.get(id);
			 velocityContext.put("qywxGzuserinfo",qywxGzuserinfo);
			 String viewName = "qywx/base/qywxGzuserinfo-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(HttpServletRequest request ,@ModelAttribute QywxGzuserinfo qywxGzuserinfo){
		AjaxJson j = new AjaxJson();
		try {
			//1.1身份认证信息（微信号/手机/邮箱）不能同时为空
			if(StringUtils.isBlank(qywxGzuserinfo.getWeixinid()) && StringUtils.isBlank(qywxGzuserinfo.getMobile()) && StringUtils.isBlank(qywxGzuserinfo.getEmail())){
				throw new Exception("微信号/手机/邮箱 不能同时为空");
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    MultipartFile uploadfile = multipartRequest.getFile("avatar_file");
//			qywxGzuserinfoDao.update(qywxGzuserinfo);
			qywxGzuserinfoService.updateGzuserinfo(qywxGzuserinfo,uploadfile,request);
			j.setMsg("编辑成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("编辑失败，详细原因："+e.getMessage());
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
			    QywxGzuserinfo qywxGzuserinfo = new QywxGzuserinfo();
				qywxGzuserinfo.setId(id);
//				qywxGzuserinfoDao.delete(qywxGzuserinfo);
				qywxGzuserinfoService.deleteGzuserinfo(qywxGzuserinfo);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败，详细原因："+e.getMessage());
			}
			return j;
	}
	
	/**
	 * 同步
	 */
	//@ModelAttribute QywxGzuserinfo query,HttpServletRequest request,HttpServletResponse response
	@RequestMapping(params="doSync",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doSync(){
		AjaxJson j = new AjaxJson();
		try {
			qywxGzuserinfoService.syncGzuserinfos();
//			LOG.info(request, " doSync list");
		 	//分页数据
//			MiniDaoPage<QywxGzuserinfo> list =  qywxGzuserinfoDao.getAll(query,1,10);
//			VelocityContext velocityContext = new VelocityContext();
//			velocityContext.put("qywxGzuserinfo",query);
//			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
//			String viewName = "qywx/base/qywxGzuserinfo-list.vm";
//			ViewVelocity.view(request,response,viewName,velocityContext);
			j.setMsg("同步成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
		    e.printStackTrace();
		    j.setSuccess(false);
			j.setMsg("同步失败，详细原因："+e.getMessage());
		}
		return j;
	}


}

