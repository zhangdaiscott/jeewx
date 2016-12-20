package com.jeecg.alipay.account.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
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
import com.alipay.api.response.AlipayMobilePublicMenuUpdateResponse;
import com.jeecg.alipay.account.dao.AlipayMenuDao;
import com.jeecg.alipay.account.entity.AlipayMenu;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.account.service.AlipayMenuService;
import com.jeecg.alipay.api.base.JwMenuAPI;
import com.jeecg.alipay.api.base.vo.menuVo.BizContent;
import com.jeecg.alipay.api.base.vo.menuVo.Button;
import com.jeecg.alipay.api.base.vo.menuVo.SubButton;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.dao.AlipayTexttemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;
import com.jeecg.alipay.util.SystemUtil;

import net.sf.json.JSONArray;

 /**
 * 描述：</b>QywxMenuController<br>自定义菜单表
 * @author p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayMenu")
public class AlipayMenuController extends BaseController{
  @Autowired
  private AlipayMenuDao alipayMenuDao;
  @Autowired
  private AlipayAccountService accountService;
  @Autowired
  private AlipayTexttemplateDao alipayTexttemplateDao;
  @Autowired
  private AlipayNewstemplateDao alipayNewstemplateDao;
  @Autowired
  private AlipayAccountService alipayAccountService;
  @Autowired
  private AlipayMenuService menuService;
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayMenu query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, "alipayMenu back list");
			 	VelocityContext velocityContext = new VelocityContext();
			 	velocityContext.put("alipayMenu",query);
//			 	---update---begin---author:chhenchuenpeng----date:20161114---for:给菜单数据重新排序------
			 	//分页数据
		 		//MiniDaoPage<AlipayMenu> list =  alipayMenuDao.getAll(query,pageNo,pageSize);
		 		//velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
			 	velocityContext.put("pageInfos",menuService.queryAll());
//			 	---update---end---author:chhenchuenpeng----date:20161114---for:给菜单数据重新排序------
				String viewName = "alipay/account/alipayMenu-list.vm";
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
	public void alipayMenuDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/account/alipayMenu-detail.vm";
			AlipayMenu alipayMenu = alipayMenuDao.get(id);
			velocityContext.put("alipayMenu",alipayMenu);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,String agentId)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/account/alipayMenu-add.vm";
		 velocityContext.put("agentId", agentId);
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayMenu alipayMenu){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayMenu.setId(randomSeed);
		    //获取父ID
		    String fatherId = alipayMenuDao.getParentId(alipayMenu.getOrders());
		    if(alipayMenu.getOrders().length()==1&&alipayMenu.getMenuName().length()>4){
		    	j.setSuccess(false);
		    	j.setMsg("一级菜单标题长度不能大于4！");
		    	return j;
		    }
		    if(!StringUtil.isEmpty(fatherId)&&alipayMenu.getMenuName().length()>12){
		    	j.setSuccess(false);
		    	j.setMsg("二级菜单标题长度不能大于12！");
		    	return j;
		    }
		   // update-start--Author:chenchunpeng  Date:20160715 for：添加新菜单时的条件判断
		    if(alipayMenu.getOrders().length()==1){
		    	//一级菜单修改位置时需判断新位置上是否有菜单存在
			    AlipayMenu fristMenu= alipayMenuDao.getMenuByMenuKey(alipayMenu.getOrders());
			    if(fristMenu!=null){
			    	j.setSuccess(false);
			    	j.setMsg("此一级菜单上已有菜单存在，不能修改 ！");
			    	return j;
			    }
		    }else if(alipayMenu.getOrders().length()==2){
			    //二级菜单修改位置时需判断此位置是否有菜单存在
			    AlipayMenu SecondMenu= alipayMenuDao.getMenuByMenuKey(alipayMenu.getOrders());
			    if(SecondMenu!=null){
			    	j.setSuccess(false);
			    	j.setMsg("此二级菜单上已有菜单存在，不能修改 ！");
			    	return j;
			    }
			    if(StringUtil.isEmpty(fatherId)){
			    	j.setSuccess(false);
			    	j.setMsg("二级菜单不能没有没有一级菜单！");
			    	return j;
			    }
		    }
		    // update-end--Author:chenchunpeng  Date:20160715 for：添加新菜单时的条件判断
		    alipayMenu.setMenuKey(alipayMenu.getOrders());
		    alipayMenu.setFatherId(fatherId);
			alipayMenuDao.insert(alipayMenu);
			j.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
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
			 AlipayMenu alipayMenu = alipayMenuDao.get(id);
			 velocityContext.put("alipayMenu",alipayMenu);
			 String viewName = "alipay/account/alipayMenu-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayMenu alipayMenu){
		AjaxJson j = new AjaxJson();
		try {
			String fatherId = alipayMenuDao.getParentId( alipayMenu.getOrders());
			if(alipayMenu.getOrders().length()==1&&alipayMenu.getMenuName().length()>4){
		    	j.setSuccess(false);
		    	j.setMsg("一级菜单标题长度不能大于4！");
		    	return j;
		    }
		    if(!StringUtil.isEmpty(fatherId)&&alipayMenu.getMenuName().length()>12){
		    	j.setSuccess(false);
		    	j.setMsg("二级菜单标题长度不能大于12！");
		    	return j;
		    }
		  // update-start--Author:chenchunpeng  Date:20160715 for：修改菜单时的条件判断
		   List<AlipayMenu> menuList=alipayMenuDao.getAllMenuByParentid(alipayMenu.getId());
		   //int size=menuList.size();
		   AlipayMenu oldMenu= alipayMenuDao.get(alipayMenu.getId());
		   String oldOrders=oldMenu.getOrders();
		   String newOrders=alipayMenu.getOrders(); 
		    if(alipayMenu.getOrders().length()==1){
		    	AlipayMenu fristMenu= alipayMenuDao.getMenuByMenuKey(alipayMenu.getOrders());
		    	//一级菜单修改位置时需判断新位置上是否有菜单存在
			    if(!oldOrders.equals(newOrders)&&fristMenu!=null){
			    	j.setSuccess(false);
			    	j.setMsg("此一级菜单上已有菜单存在，不能修改 ！");
			    	return j;
			    }
		    }else if(alipayMenu.getOrders().length()==2){
			    //二级菜单修改位置时需判断此位置是否有菜单存在
			    AlipayMenu SecondMenu= alipayMenuDao.getMenuByMenuKey(alipayMenu.getOrders());
			    if(!oldOrders.equals(newOrders)&&SecondMenu!=null){
			    	j.setSuccess(false);
			    	j.setMsg("此二级菜单上已有菜单存在，不能修改 ！");
			    	return j;
			    }
			    //查询父菜单是否存在
			    if(StringUtil.isEmpty(fatherId)){
			    	AlipayMenu fatherMenu=alipayMenuDao.get(fatherId);
			    	if(fatherMenu==null){
			    		j.setSuccess(false);
			    		j.setMsg("二级菜单不能没有没有一级菜单！");
			    		return j;
			    	}
			    }
			    //如果将一级菜单改为二级菜单时需要判断此一级菜单下面是否有菜单存在
			    if(!oldOrders.equals(newOrders)){
				    if(menuList.size()>0&&menuList!=null){
				    	j.setSuccess(false);
				    	j.setMsg("一级菜单下有二级菜单存在，不能修改 ！");
				    	return j;
				    }
			    }
		    }
		    if(alipayMenu.getOrders().length()==2){
		    	alipayMenu.setFatherId(fatherId);
		    }else {
		    	fatherId="";
		    	alipayMenu.setFatherId("");
		    }
		   // update-end--Author:chenchunpeng  Date:20160715 for：修改菜单时的条件判断
			alipayMenu.setMenuKey(alipayMenu.getOrders());
			alipayMenuDao.update(alipayMenu);
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
				//update-begin--author:zhangjiaqiang date:20161110 for:如果存在子菜单不允许删除
				List<AlipayMenu> allChildMenu = alipayMenuDao.getAllMenuByParentid(id);
				if(allChildMenu!=null && allChildMenu.size()>0){
					j.setSuccess(false);
					j.setMsg("请先删除子菜单");
				}else{
					AlipayMenu alipayMenu = new AlipayMenu();
					alipayMenu.setId(id);
					alipayMenuDao.delete(alipayMenu);
					j.setMsg("删除成功");
				}
				//update-end--author:zhangjiaqiang date:20161110 for:如果存在子菜单不允许删除
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
	public AjaxJson doSynch(@ModelAttribute AlipayMenu query){
			AjaxJson j = new AjaxJson();
			try {
				AlipayConfig config = alipayAccountService.getAlipayConfig();
				if(config == null){
					j.setMsg("请先添加支付窗账号");
					j.setSuccess(false);
					return j;
				}
			 		//微信菜单
			 		//获取第一级菜单
			 		List<AlipayMenu> mainMenulist =  alipayMenuDao.getAllFirstMenu(SystemUtil.getOnlieAlipayAccountId());
			 		BizContent biz = new BizContent();
			 		if(mainMenulist!=null && mainMenulist.size()!=0){
			 			//存储一级菜单
			 			List<Button> mainList = new ArrayList<Button>();
			 			//遍历一级菜单，存储到VO中
			 			for(AlipayMenu po:mainMenulist){
			 				if(po.getMenuName().length()>4){
			 			    	j.setSuccess(false);
			 			    	j.setMsg("同步失败，一级菜单标题长度不能大于4！");
			 			    	return j;
			 			    }
			 				Button mainbutton = new Button();
			 				mainbutton.setName(po.getMenuName());
			 				if("link".equals(po.getMenuType())){
			 					 mainbutton.setActionType("link");
						    	 mainbutton.setActionParam(po.getUrl());
						     }else{
						    	 mainbutton.setActionType("out");
				 				mainbutton.setActionParam(po.getMenuKey());
						     }
						    //获取二级菜单
						    List<SubButton> subList = new ArrayList<SubButton>();
						    //通过父ID获取子菜单列表
						    List<AlipayMenu> subMenulist = alipayMenuDao.getAllMenuByParentid(po.getId());
						    //遍历子菜单
						    for(AlipayMenu sub:subMenulist){
						    	if(sub.getMenuName().length()>12){
				 			    	j.setSuccess(false);
				 			    	j.setMsg("同步失败，二级菜单标题长度不能大于12！");
				 			    	return j;
				 			    }
						    	SubButton btn = new SubButton();
							     btn.setName(sub.getMenuName());  
							     btn.setActionType(sub.getMenuType());
							     if("link".equals(sub.getMenuType())){
							    	 btn.setActionType("link");
							    	 btn.setActionParam(sub.getUrl());
							     }else{
							    	 btn.setActionType("out");
							    	 btn.setActionParam(sub.getMenuKey());
							     }
							     subList.add(btn);
						    }
						    mainbutton.setSubButton(subList.toArray(new SubButton[subList.size()]));
						    mainList.add(mainbutton);
			 			}
			 			biz.setButton(mainList);
			 		}
			 		System.out.println("同步菜单发送文本："+JSONObject.toJSONString(biz));
			 		//加载本项目帐号配置
			 		//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			 		//执行，返回响应
			 		AlipayMobilePublicMenuUpdateResponse response = JwMenuAPI.menuUpdate(null, biz,config);
			 		//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			 		System.out.println("同步菜单："+response.getBody());
			 		if("200".equals(response.getCode())){
			 			j.setMsg("同步菜单成功!");
			 		}else{
			 			j.setMsg("错误代码:"+response.getCode()+","+response.getMsg());
			 		}
			} catch (Exception e) {
				e.printStackTrace();
			    log.info(e.getMessage());
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
				List<AlipayTexttemplate> templateList = alipayTexttemplateDao.getAllAlipayTexttemplate(SystemUtil.getOnlieAlipayAccountId());
				 json = JSONArray.fromObject(templateList);
			}else{
				 //TODO condition.setAccountId(accountId);
				//update-begin--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
				List<AlipayNewstemplate> templateList = alipayNewstemplateDao.getAllAlipayNewstemplate(SystemUtil.getOnlieAlipayAccountId());
				//update-end--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
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

