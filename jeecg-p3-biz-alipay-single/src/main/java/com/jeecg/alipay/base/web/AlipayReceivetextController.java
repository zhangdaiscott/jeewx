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

import com.alibaba.fastjson.JSONObject;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.base.dao.AlipayReceivetextContentDao;
import com.jeecg.alipay.base.dao.AlipayReceivetextDao;
import com.jeecg.alipay.base.entity.AlipayReceivetext;
import com.jeecg.alipay.base.entity.AlipayReceivetextContent;
import com.jeecg.alipay.core.service.AlipayCoreService;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;

 /**
 * 描述：</b>AlipayReceivetextController<br>文本消息; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 10时13分23秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayReceivetext")
public class AlipayReceivetextController extends BaseController{
  @Autowired
  private AlipayReceivetextDao alipayReceivetextDao;
  @Autowired
  private AlipayCoreService alipayCoreService;
  // update-start--Author:chenchunpeng  Date:20160715 for：新增用户回复消息列表 
  @Autowired
  private  AlipayReceivetextContentDao alipayReceivetextContentDao; 
  // update-end--Author:chenchunpeng  Date:20160715 for：新增用户回复消息列表 
  @Autowired
  private AlipayAccountService alipayAccountService;
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayReceivetext query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayReceivetext> list =  alipayReceivetextDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayReceivetext",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/base/alipayReceivetext-list.vm";
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
	public void alipayReceivetextDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/base/alipayReceivetext-detail.vm";
			AlipayReceivetext alipayReceivetext = alipayReceivetextDao.get(id);
			velocityContext.put("alipayReceivetext",alipayReceivetext);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/base/alipayReceivetext-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayReceivetext alipayReceivetext){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayReceivetext.setId(randomSeed);
		    alipayReceivetext.setResponse("0");
		    alipayReceivetext.setCreateDate(new Date());
			alipayReceivetextDao.insert(alipayReceivetext);
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
			 AlipayReceivetext alipayReceivetext = alipayReceivetextDao.get(id);
			 velocityContext.put("alipayReceivetext",alipayReceivetext);
			 String viewName = "alipay/base/alipayReceivetext-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayReceivetext alipayReceivetext){
		AjaxJson j = new AjaxJson();
		try {
			alipayReceivetextDao.update(alipayReceivetext);
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
			    AlipayReceivetext alipayReceivetext = new AlipayReceivetext();
				alipayReceivetext.setId(id);
				alipayReceivetextDao.delete(alipayReceivetext);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}

	/**
	 * 跳转到快捷回复页面
	 * @return
	 */
	@RequestMapping(params="toSendmessage",method = RequestMethod.GET)
	public void toSendmessage(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 AlipayReceivetext alipayReceivetext = alipayReceivetextDao.get(id);
			 velocityContext.put("alipayReceivetext",alipayReceivetext);
			 String viewName = "alipay/base/alipayReceivetext-sendmessage.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 快捷回复
	 * @return
	 */
	@RequestMapping(params = "doResponseMessage",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doResponseMessage(@ModelAttribute AlipayReceivetext alipayReceivetext){
		AjaxJson j = new AjaxJson();
		try {
			AlipayConfig config = alipayAccountService.getAlipayConfig();
			if(config == null){
				j.setSuccess(false);
				j.setMsg("请先添加账号");
				return j;
			}
			AlipayTexttemplate textTemplate = new AlipayTexttemplate();
			textTemplate.setTemplateContent(alipayReceivetext.getRescontent());
			String responseMsg = JSONObject.toJSONString(AlipayUtil.wrapperTextMessage(textTemplate,alipayReceivetext.getFromusername()));
			alipayCoreService.sendMsg(responseMsg, alipayReceivetext.getFromusername());
			// update-start--Author:chenchunpeng  Date:20160715 for：对用户回复信息单独进行保存
			//获取回复的信息
			AlipayReceivetextContent reContent=new AlipayReceivetextContent();
			String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			reContent.setId(id);
			reContent.setContent(alipayReceivetext.getRescontent());
			reContent.setReceivetextId(alipayReceivetext.getId());
			alipayReceivetextContentDao.insert(reContent);
			//在用户信息表中对回复信息不保存
			alipayReceivetext.setRescontent("");
			//alipayReceivetext.setResponse("1");
			// update-end--Author:chenchunpeng  Date:20160715 for：对用户回复信息单独进行保存
			alipayReceivetextDao.update(alipayReceivetext);
			j.setMsg("编辑成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}
	// update-start--Author:chenchunpeng  Date:20160715 for：对用户回复信息列表页面数据查询及数据的删除
	/**
	 * 跳转到回复列表页面
	 * @return
	 */
	@RequestMapping(params = "toGetMesage",method = {RequestMethod.GET,RequestMethod.POST})
	public void toGetMesage(@ModelAttribute AlipayReceivetextContent query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		try {
		 	LOG.info(request, " back list");
		 	//分页数据
			MiniDaoPage<AlipayReceivetextContent> list =  alipayReceivetextContentDao.getAll(query,pageNo,pageSize);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("alipayReceivetextContent",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
			String viewName = "alipay/base/alipayReceivetextContent-list.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(params="doDeleteMesage",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDeleteMesage(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {
			    AlipayReceivetextContent alipayReceivetextContent = new AlipayReceivetextContent();
				alipayReceivetextContent.setId(id);
				alipayReceivetextContentDao.delete(alipayReceivetextContent);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	// update-end--Author:chenchunpeng  Date:20160715 for：对用户回复信息列表页面数据查询及数据的删除
}

