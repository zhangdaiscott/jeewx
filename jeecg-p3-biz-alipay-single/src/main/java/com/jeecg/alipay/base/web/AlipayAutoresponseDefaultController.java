package com.jeecg.alipay.base.web;

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
import com.jeecg.alipay.base.entity.AlipayAutoresponseDefault;
import com.jeecg.alipay.base.dao.AlipayAutoresponseDefaultDao;

 /**
 * 描述：</b>AlipayAutoresponseDefaultController<br>默认关键字回复; InnoDB free: 130048 kB
 * @author p3.jeecg
 * @since：2016年04月06日 16时33分37秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayAutoresponseDefault")
public class AlipayAutoresponseDefaultController extends BaseController{
  @Autowired
  private AlipayAutoresponseDefaultDao alipayAutoresponseDefaultDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayAutoresponseDefault query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayAutoresponseDefault> list =  alipayAutoresponseDefaultDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayAutoresponseDefault",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/base/alipayAutoresponseDefault-list.vm";
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
	public void alipayAutoresponseDefaultDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/base/alipayAutoresponseDefault-detail.vm";
			AlipayAutoresponseDefault alipayAutoresponseDefault = alipayAutoresponseDefaultDao.get(id);
			velocityContext.put("alipayAutoresponseDefault",alipayAutoresponseDefault);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/base/alipayAutoresponseDefault-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayAutoresponseDefault alipayAutoresponseDefault){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayAutoresponseDefault.setId(randomSeed);
			alipayAutoresponseDefaultDao.insert(alipayAutoresponseDefault);
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
			 AlipayAutoresponseDefault alipayAutoresponseDefault = alipayAutoresponseDefaultDao.get(id);
			 velocityContext.put("alipayAutoresponseDefault",alipayAutoresponseDefault);
			 String viewName = "alipay/base/alipayAutoresponseDefault-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayAutoresponseDefault alipayAutoresponseDefault){
		AjaxJson j = new AjaxJson();
		try {
			alipayAutoresponseDefaultDao.update(alipayAutoresponseDefault);
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
			    AlipayAutoresponseDefault alipayAutoresponseDefault = new AlipayAutoresponseDefault();
				alipayAutoresponseDefault.setId(id);
				alipayAutoresponseDefaultDao.delete(alipayAutoresponseDefault);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

