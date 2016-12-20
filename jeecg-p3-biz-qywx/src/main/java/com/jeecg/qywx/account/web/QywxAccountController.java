package com.jeecg.qywx.account.web;

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
import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.account.dao.QywxAccountDao;
import com.jeecg.qywx.util.SystemUtil;

 /**
 * 描述：</b>QywxAccountController<br>账号信息表
 * @author Alex
 * @since：2016年03月24日 14时55分37秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxAccount")
public class QywxAccountController extends BaseController{
  @Autowired
  private QywxAccountDao qywxAccountDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxAccount query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxAccount> list =  qywxAccountDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxAccount",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				velocityContext.put("num", list.getResults().size());
				String viewName = "qywx/account/qywxAccount-list.vm";
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
	public void qywxAccountDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/account/qywxAccount-detail.vm";
			QywxAccount qywxAccount = qywxAccountDao.get(id);
			velocityContext.put("qywxAccount",qywxAccount);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/account/qywxAccount-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxAccount qywxAccount){
		AjaxJson j = new AjaxJson();
		try {
//		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//		    qywxAccount.setId(randomSeed);
			//企业号目前只支持添加一个，默认ID写死
			//TODO
		    qywxAccount.setId(SystemUtil.QYWX_ACCOUNT_ID);
			qywxAccountDao.insert(qywxAccount);
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
			 QywxAccount qywxAccount = qywxAccountDao.get(id);
			 velocityContext.put("qywxAccount",qywxAccount);
			 String viewName = "qywx/account/qywxAccount-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxAccount qywxAccount){
		AjaxJson j = new AjaxJson();
		try {
			qywxAccountDao.update(qywxAccount);
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
			    QywxAccount qywxAccount = new QywxAccount();
				qywxAccount.setId(id);
				qywxAccountDao.delete(qywxAccount);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

