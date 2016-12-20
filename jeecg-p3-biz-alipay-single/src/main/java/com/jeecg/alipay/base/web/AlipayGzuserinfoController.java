package com.jeecg.alipay.base.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.alipay.api.internal.util.WebUtils;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayClientFactory;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.base.dao.AlipayGzuserinfoDao;
import com.jeecg.alipay.base.entity.AlipayGzuserinfo;
import com.jeecg.alipay.base.service.AlipayGzuserinfoService;

 /**
 * 描述：</b>AlipayGzuserinfoController<br>关注用户; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 15时24分11秒 星期五 
 * @version:1.0
 * 序号		修改人	日期			备注
 * 1		yangqj	2016-04-19	与微信接口同步，修改的方法有
 */
@Controller
@RequestMapping("/alipay/alipayGzuserinfo")
public class AlipayGzuserinfoController extends BaseController{
  @Autowired
  private AlipayGzuserinfoDao alipayGzuserinfoDao;
  @Autowired
  private AlipayGzuserinfoService alipayGzuserinfoService;
  @Autowired
  private AlipayAccountService alipayAccountService;
  
  
  	/**
  	 * author2.0 授权回调测试方法
  	 * @param request
  	 * @param response
  	 * @throws Exception
  	 */
  	@RequestMapping(params = "userinfo",method ={RequestMethod.GET, RequestMethod.POST})
	public void userinfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String authcode = request.getParameter("auth_code");
		 String scope = request.getParameter("scope");
		 AlipaySystemOauthTokenRequest alipayrequest = new AlipaySystemOauthTokenRequest();
		 alipayrequest.setCode(authcode);
		 alipayrequest.setGrantType("authorization_code");
		//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		 AlipaySystemOauthTokenResponse alipayresponse = AlipayClientFactory.getAlipayClientInstance(alipayAccountService.getAlipayConfig()).execute(alipayrequest);
		//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		 if(null != alipayresponse && alipayresponse.isSuccess()){
			 AlipayGzuserinfo userinfo = new AlipayGzuserinfo();
			 if("auth_userinfo".equals(scope)){
				 String token =  alipayresponse.getAccessToken();
				 AlipayUserUserinfoShareRequest sharerequest = new AlipayUserUserinfoShareRequest();
				 sharerequest.putOtherTextParam("auth_token", token);
				//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
				 AlipayUserUserinfoShareResponse shareresponse = AlipayClientFactory.getAlipayClientInstance(alipayAccountService.getAlipayConfig()).execute(sharerequest);
				//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
				 userinfo.setName(shareresponse.getNickName());
				 userinfo.setProvince(shareresponse.getProvince());
				 userinfo.setAvatar(shareresponse.getAvatar());
				 userinfo.setEmail(shareresponse.getEmail());
				 userinfo.setPosition(shareresponse.getCity());
				 userinfo.setGender(shareresponse.getGender());
			 }
			 userinfo.setUserid(alipayresponse.getUserId());
			 velocityContext.put("info", userinfo);
		 }
		 String viewName = "alipay/oauth/userinfo.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}
  
  	@RequestMapping(params = "combobox",method = {RequestMethod.GET})
  	@ResponseBody
  	public List<AlipayGzuserinfo> combobox(@ModelAttribute AlipayGzuserinfo query,HttpServletRequest request,HttpServletResponse response){
  		List<AlipayGzuserinfo> userlist = this.alipayGzuserinfoDao.getAllUser(query);
  		return userlist;
  	}
  
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayGzuserinfo query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayGzuserinfo> list =  alipayGzuserinfoDao.getAll(query,pageNo,pageSize);
				//TODO
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayGzuserinfo",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/base/alipayGzuserinfo-list.vm";
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
	public void alipayGzuserinfoDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/base/alipayGzuserinfo-detail.vm";
			AlipayGzuserinfo alipayGzuserinfo = alipayGzuserinfoDao.get(id);
			velocityContext.put("alipayGzuserinfo",alipayGzuserinfo);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/base/alipayGzuserinfo-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request , @ModelAttribute AlipayGzuserinfo alipayGzuserinfo){
		AjaxJson j = new AjaxJson();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    MultipartFile uploadfile = multipartRequest.getFile("avatar_file");
			//对数据进行校验
			//1.1身份认证信息（微信号/手机/邮箱）不能同时为空
			if(StringUtils.isBlank(alipayGzuserinfo.getWeixinid()) && StringUtils.isBlank(alipayGzuserinfo.getMobile()) && StringUtils.isBlank(alipayGzuserinfo.getEmail())){
				throw new Exception("微信号/手机/邮箱 不能同时为空");
			}
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayGzuserinfo.setId(randomSeed);
		    alipayGzuserinfo.setCreateDate(new Date());
		    
		  
		    
		    alipayGzuserinfoService.saveGzuserinfo(alipayGzuserinfo,uploadfile, request);
//			alipayGzuserinfoDao.insert(alipayGzuserinfo);
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
			 AlipayGzuserinfo alipayGzuserinfo = alipayGzuserinfoDao.get(id);
			 velocityContext.put("alipayGzuserinfo",alipayGzuserinfo);
			 String viewName = "alipay/base/alipayGzuserinfo-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(HttpServletRequest request ,@ModelAttribute AlipayGzuserinfo alipayGzuserinfo){
		AjaxJson j = new AjaxJson();
		try {
			//1.1身份认证信息（微信号/手机/邮箱）不能同时为空
			if(StringUtils.isBlank(alipayGzuserinfo.getWeixinid()) && StringUtils.isBlank(alipayGzuserinfo.getMobile()) && StringUtils.isBlank(alipayGzuserinfo.getEmail())){
				throw new Exception("微信号/手机/邮箱 不能同时为空");
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    MultipartFile uploadfile = multipartRequest.getFile("avatar_file");
//			alipayGzuserinfoDao.update(alipayGzuserinfo);
			alipayGzuserinfoService.updateGzuserinfo(alipayGzuserinfo,uploadfile,request);
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
			    AlipayGzuserinfo alipayGzuserinfo = new AlipayGzuserinfo();
				alipayGzuserinfo.setId(id);
//				alipayGzuserinfoDao.delete(alipayGzuserinfo);
				alipayGzuserinfoService.deleteGzuserinfo(alipayGzuserinfo);
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
	//@ModelAttribute AlipayGzuserinfo query,HttpServletRequest request,HttpServletResponse response
	@RequestMapping(params="doSync",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doSync(){
		AjaxJson j = new AjaxJson();
		try {
			AlipayConfig config = alipayAccountService.getAlipayConfig();
			if(config == null){
				j.setSuccess(false);
				j.setMsg("请先添加账号");
				return j;
			}
			alipayGzuserinfoService.syncGzuserinfos();
//			LOG.info(request, " doSync list");
		 	//分页数据
//			MiniDaoPage<AlipayGzuserinfo> list =  alipayGzuserinfoDao.getAll(query,1,10);
//			VelocityContext velocityContext = new VelocityContext();
//			velocityContext.put("alipayGzuserinfo",query);
//			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
//			String viewName = "alipay/base/alipayGzuserinfo-list.vm";
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

