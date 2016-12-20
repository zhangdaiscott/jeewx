package com.jeecg.qywx.account.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.qywx.account.dao.QywxAgentDao;
import com.jeecg.qywx.account.dao.QywxMenuDao;
import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.account.entity.QywxMenu;
import com.jeecg.qywx.account.service.AccountService;
import com.jeecg.qywx.api.base.JwAccessTokenAPI;
import com.jeecg.qywx.api.base.JwParamesAPI;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.api.menu.JwMenuAPI;
import com.jeecg.qywx.api.menu.vo.Button;
import com.jeecg.qywx.api.menu.vo.CommonButton;
import com.jeecg.qywx.api.menu.vo.ComplexButton;
import com.jeecg.qywx.api.menu.vo.Menu;
import com.jeecg.qywx.api.menu.vo.ViewButton;
import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.dao.QywxTexttemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

 /**
 * 描述：</b>QywxMenuController<br>自定义菜单表
 * @author p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxMenu")
public class QywxMenuController extends BaseController{
  @Autowired
  private QywxMenuDao qywxMenuDao;
  @Autowired
  private QywxAgentDao qywxAgentDao;
  @Autowired
  private QywxTexttemplateDao qywxTexttemplateDao;
  @Autowired
  private QywxNewstemplateDao qywxNewstemplateDao;
  @Autowired
  private AccountService accountService;
  
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxMenu query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, "qywxMenu back list");
			 	VelocityContext velocityContext = new VelocityContext();
			 	velocityContext.put("qywxMenu",query);
			 	//分页数据
			 	if(!StringUtil.isEmpty(query.getAgentId())){
			 		MiniDaoPage<QywxMenu> list =  qywxMenuDao.getAll(query,pageNo,pageSize);
			 		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
			 	}
			 	
				String viewName = "qywx/account/qywxMenu-list.vm";
				//获取应用列表
				List<QywxAgent> agentList = qywxAgentDao.getAllQywxAgent("");
				velocityContext.put("agentList",agentList);
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
	public void qywxMenuDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/account/qywxMenu-detail.vm";
			QywxMenu qywxMenu = qywxMenuDao.get(id);
			velocityContext.put("qywxMenu",qywxMenu);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,String agentId)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qywx/account/qywxMenu-add.vm";
		 velocityContext.put("agentId", agentId);
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxMenu qywxMenu){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxMenu.setId(randomSeed);
		    //获取父ID
		    String fatherId = qywxMenuDao.getParentId(qywxMenu.getAgentId(), qywxMenu.getOrders());
		    qywxMenu.setFatherId(fatherId);
			qywxMenuDao.insert(qywxMenu);
			j.setMsg("保存成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
		    j.setSuccess(false);
			j.setMsg("保存失败");
		    if(e.getMessage().indexOf("Duplicate")!=-1){
		    	j.setMsg("操作失败：菜单位置、菜单KEY、不允许重复!");
		    }
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
			 QywxMenu qywxMenu = qywxMenuDao.get(id);
			 velocityContext.put("qywxMenu",qywxMenu);
			 String viewName = "qywx/account/qywxMenu-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxMenu qywxMenu){
		AjaxJson j = new AjaxJson();
		try {
			qywxMenuDao.update(qywxMenu);
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
			    QywxMenu qywxMenu = new QywxMenu();
				qywxMenu.setId(id);
				qywxMenuDao.delete(qywxMenu);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	/**
	 * 同步菜单
	 * @return
	 */
	@RequestMapping(params="doSynch",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doSynch(@ModelAttribute QywxMenu query){
			AjaxJson j = new AjaxJson();
			try {
				//分页数据
			 	if(!StringUtil.isEmpty(query.getAgentId())){
			 		//微信菜单
			 		Menu menu = new Menu();
			 		List<QywxMenu> mainMenulist =  qywxMenuDao.getAllMenuByAgentid(query.getAgentId());
			 		if(mainMenulist!=null && mainMenulist.size()!=0){
			 			List<Button> mainList = new ArrayList<Button>();
			 			for(QywxMenu po:mainMenulist){
			 				ComplexButton mainBtn = new ComplexButton();  
						    mainBtn.setName(po.getMenuName());
						    //获取二级菜单
						    List<Button> subList = new ArrayList<Button>();
						    List<QywxMenu> subMenulist = qywxMenuDao.getAllMenuByParentid(po.getId());
						    for(QywxMenu sub:subMenulist){
						    	 CommonButton btn = new CommonButton();  
							     btn.setName(sub.getMenuName());  
							     btn.setType(sub.getMenuType());
							     if("view".equals(sub.getMenuType())){
							    	 btn.setUrl(sub.getUrl());
							     }else{
							    	 btn.setKey(sub.getMenuKey());
							     }
							    
							     subList.add(btn);
						    }
						    mainBtn.setSub_button(subList.toArray(new Button[mainList.size()]));
						    mainList.add(mainBtn);
			 			}
			 			menu.setButton(mainList.toArray(new Button[mainList.size()]));
			 		}
			 		AccessToken accessToken = accountService.getAccessToken();
			 		//通过应用ID，获取应用信息
			 		QywxAgent po = qywxAgentDao.getAgent(query.getAgentId());
			 		int result = JwMenuAPI.createMenu(menu, accessToken.getAccesstoken(), po.getWxAgentid());
			 		if(result == 0){
			 			j.setMsg("同步菜单成功!");
			 		}else{
			 			j.setMsg("同步菜单失败!");
			 		}
			 	}
			} catch (Exception e) {
			    log.error(e.toString());
				j.setSuccess(false);
				j.setMsg("同步菜单失败");
			}
			return j;
	}
	
	/**
	 * 获取素材列表
	 * 
	 * @return
	 */
	@RequestMapping(params="getSucaiList",method = RequestMethod.GET)
	@ResponseBody
	public void getSucaiList(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(required = true, value = "msgType" ) String msgType) {
		 JSONArray json = new JSONArray();
		try {
			if("text".equals(msgType)){
				 //TODO condition.setAccountId(accountId);
				List<QywxTexttemplate> templateList = qywxTexttemplateDao.getAllQywxTexttemplate();
				 json = JSONArray.fromObject(templateList);
			}else{
				 //TODO condition.setAccountId(accountId);
				List<QywxNewstemplate> templateList = qywxNewstemplateDao.getAllQywxNewstemplate();
				 json = JSONArray.fromObject(templateList);
			}
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(json.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			LOG.error("getSucaiList error:{}", e.getMessage());
		}
	}

}

