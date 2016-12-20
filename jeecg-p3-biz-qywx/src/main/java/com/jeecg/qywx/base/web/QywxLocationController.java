package com.jeecg.qywx.base.web;

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
import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.base.entity.QywxLocation;
import com.jeecg.qywx.base.dao.QywxGzuserinfoDao;
import com.jeecg.qywx.base.dao.QywxLocationDao;

 /**
 * 描述：</b>QywxLocationController<br>地理位置表
 * @author p3.jeecg
 * @since：2016年05月25日 13时27分26秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxLocation")
public class QywxLocationController extends BaseController{
  @Autowired
  private QywxLocationDao qywxLocationDao;
  @Autowired
  private QywxAccountDao qywxAccountDao;
  @Autowired
  private QywxGzuserinfoDao qywxGzuserinfoDao;
  @Autowired
  private QywxAgentDao qywxAgentDao;
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxLocation query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<QywxLocation> list = qywxLocationDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			// 显示字段的名字
			for (QywxLocation locationPo : list.getResults()) {
				String corpid = locationPo.getCorpid();// 企业号id
				String userid = locationPo.getUserid();// 用户id
				String agentid = locationPo.getAgentid();
				// 显示企业号Id对应的名字
				String accontName = null;
				if (corpid == null || "".equals(corpid)) {// 企业id为空的时候
					accontName = null;
				} else {// 企业id不为空的时候
					QywxAccount qywxAccount = qywxAccountDao.get(corpid);
					if (qywxAccount != null) {// 企业id不为空的时候
						accontName = qywxAccount.getAccontName();
					}
				}
				locationPo.setCorpid(accontName);
				// 显示userid对应的名字
				String username = null;
				if (userid == null || "".equals(username)) {// userid为空的时候
					username = null;
				} else {// userid为不为空的时候
					QywxGzuserinfo user = qywxGzuserinfoDao.getByUserid(userid);
					if (user != null) {
						username = user.getName();// 查出userid对应的name
					}
				}
				locationPo.setUserid(username);
				// 显示应用id对应的名字
				String agentName = null;
				if (agentid == null || "".equals(agentid)) {// agentid为空时候
					agentName = null;
				} else {
					QywxAgent agent = qywxAgentDao.getAgent(agentid);
					if (agent != null) {
						agentName = agent.getAgentName();// 查出应用Id对应的名字
					}
				}
				locationPo.setAgentid(agentName);
			}
			velocityContext.put("qywxLocation", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "qywx/base/qywxLocation-list.vm";
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
	public void qywxLocationDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/base/qywxLocation-detail.vm";
			QywxLocation qywxLocation = qywxLocationDao.get(id);
			velocityContext.put("qywxLocation",qywxLocation);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/base/qywxLocation-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxLocation qywxLocation){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxLocation.setId(randomSeed);
			qywxLocationDao.insert(qywxLocation);
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
			 QywxLocation qywxLocation = qywxLocationDao.get(id);
			 velocityContext.put("qywxLocation",qywxLocation);
			 String viewName = "qywx/base/qywxLocation-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxLocation qywxLocation){
		AjaxJson j = new AjaxJson();
		try {
			qywxLocationDao.update(qywxLocation);
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
			    QywxLocation qywxLocation = new QywxLocation();
				qywxLocation.setId(id);
				qywxLocationDao.delete(qywxLocation);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

