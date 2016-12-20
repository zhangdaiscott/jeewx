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
import com.jeecg.qywx.base.entity.QywxAutoresponseDefault;
import com.jeecg.qywx.base.dao.QywxAutoresponseDefaultDao;

 /**
 * 描述：</b>QywxAutoresponseDefaultController<br>默认关键字回复; InnoDB free: 130048 kB
 * @author p3.jeecg
 * @since：2016年04月06日 16时33分37秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxAutoresponseDefault")
public class QywxAutoresponseDefaultController extends BaseController{
  @Autowired
  private QywxAutoresponseDefaultDao qywxAutoresponseDefaultDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxAutoresponseDefault query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxAutoresponseDefault> list =  qywxAutoresponseDefaultDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxAutoresponseDefault",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "qywx/base/qywxAutoresponseDefault-list.vm";
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
	public void qywxAutoresponseDefaultDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/base/qywxAutoresponseDefault-detail.vm";
			QywxAutoresponseDefault qywxAutoresponseDefault = qywxAutoresponseDefaultDao.get(id);
			velocityContext.put("qywxAutoresponseDefault",qywxAutoresponseDefault);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/base/qywxAutoresponseDefault-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxAutoresponseDefault qywxAutoresponseDefault){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxAutoresponseDefault.setId(randomSeed);
			qywxAutoresponseDefaultDao.insert(qywxAutoresponseDefault);
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
			 QywxAutoresponseDefault qywxAutoresponseDefault = qywxAutoresponseDefaultDao.get(id);
			 velocityContext.put("qywxAutoresponseDefault",qywxAutoresponseDefault);
			 String viewName = "qywx/base/qywxAutoresponseDefault-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxAutoresponseDefault qywxAutoresponseDefault){
		AjaxJson j = new AjaxJson();
		try {
			qywxAutoresponseDefaultDao.update(qywxAutoresponseDefault);
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
			    QywxAutoresponseDefault qywxAutoresponseDefault = new QywxAutoresponseDefault();
				qywxAutoresponseDefault.setId(id);
				qywxAutoresponseDefaultDao.delete(qywxAutoresponseDefault);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

