package com.jeecg.qywx.conversation.web;

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
import com.jeecg.qywx.conversation.entity.QywxConversation;
import com.jeecg.qywx.conversation.service.QywxConversationService;
import com.jeecg.qywx.core.weixin.model.GzUserInfo;
import com.jeecg.qywx.base.dao.QywxGzuserinfoDao;
import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.base.service.QywxGzuserinfoService;
import com.jeecg.qywx.conversation.dao.QywxConversationDao;

 /**
 * 描述：</b>QywxConversationController<br>InnoDB free: 11264 kB
 * @author p3.jeecg
 * @since：2016年05月12日 21时09分13秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxConversation")
public class QywxConversationController extends BaseController{
  @Autowired
  private QywxConversationDao qywxConversationDao;
  @Autowired
  private QywxConversationService qywxConversationService;
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxConversation query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxConversation> list =  qywxConversationDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxConversation",query);
//				获取对象数据
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "qywx/conversation/qywxConversation-list.vm";
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
	public void qywxConversationDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/conversation/qywxConversation-detail.vm";
			QywxConversation qywxConversation = qywxConversationDao.get(id);
			velocityContext.put("qywxConversation",qywxConversation);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/conversation/qywxConversation-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxConversation qywxConversation){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxConversation.setId(randomSeed);
		   j =  qywxConversationService.createConversation(qywxConversation);
		} catch (Exception e) {
		    log.info(e.getMessage());
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
			 QywxConversation qywxConversation = qywxConversationDao.get(id);
			 velocityContext.put("qywxConversation",qywxConversation);
			 String viewName = "qywx/conversation/qywxConversation-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxConversation qywxConversation){
		AjaxJson j = new AjaxJson();
		try {
			j = qywxConversationService.updateConversation(qywxConversation);
		} catch (Exception e) {
		    log.info(e.getMessage());
		    j.setSuccess(false);
		    j.setMsg("执行失败!");
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
			    QywxConversation qywxConversation = new QywxConversation();
				qywxConversation.setId(id);
				qywxConversationService.deleteConversation(qywxConversation);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

