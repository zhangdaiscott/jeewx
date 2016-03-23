package weixin.guanjia.menu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.common.Button;
import weixin.guanjia.core.entity.common.CommonButton;
import weixin.guanjia.core.entity.common.ComplexButton;
import weixin.guanjia.core.entity.common.Menu;
import weixin.guanjia.core.entity.common.ViewButton;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.menu.service.WeixinMenuServiceI;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.TextTemplate;

/**
 * 微信自定义菜单
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/menuManagerController")
public class MenuManagerController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private WeixinMenuServiceI weixinMenuService;
	@Autowired
	private WeixinExpandconfigServiceI weixinExpandconfigService;
	private String message;

	@RequestMapping(params = "list")
	public ModelAndView list() {
		return new ModelAndView("weixin/guanjia/menu/menulist");
	}

	@RequestMapping(params = "getSubMenu")
	public void getSubMenu(HttpServletRequest request,
			HttpServletResponse response) {
		String accountid = ResourceUtil.getWeiXinAccountId();
		String msgType = request.getParameter("msgType");
		String resMsg = "";
		 JsonConfig config = new JsonConfig();
		 config.setJsonPropertyFilter(new PropertyFilter(){  
			    public boolean apply(Object source, String name, Object value) {  
			        if(name.equals("menuList")) { //要过滤的areas ，Map对象中的  
			            return true;  
			        } else {  
			            return false;  
			        }  
			    }  
			});
			List<MenuEntity> textList = this.weixinMenuService
					.findByQueryString("from MenuEntity t  where t.accountId = '"
							+  accountid+ "'");
			JSONArray json = JSONArray.fromObject(textList,config);
			resMsg = json.toString();
	
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resMsg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@RequestMapping(params = "gettemplate")
	public void gettemplate(HttpServletRequest request,
			HttpServletResponse response) {
		String accountid = ResourceUtil.getWeiXinAccountId();
		String msgType = request.getParameter("msgType");
		String resMsg = "";
		if ("text".equals(msgType)) {
			List<TextTemplate> textList = this.weixinMenuService
					.findByQueryString("from TextTemplate t where t.accountId = '"
							+  accountid+ "'");
			JSONArray json = JSONArray.fromObject(textList);
			resMsg = json.toString();
		} else if ("news".equals(msgType)) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "newsItemList" });
			List<NewsTemplate> newsList = this.weixinMenuService
					.findByQueryString("from NewsTemplate t where t.accountId = '"
							+ accountid + "'");
			JSONArray json = JSONArray.fromObject(newsList, jsonConfig);
			resMsg = json.toString();
		}else if("expand".equals(msgType)){

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "newsItemList" });
			List<NewsTemplate> newsList = this.weixinMenuService
					.findByQueryString("from WeixinExpandconfigEntity t where t.accountid = '"
							+ accountid + "'");
			JSONArray json = JSONArray.fromObject(newsList, jsonConfig);
			resMsg = json.toString();
		
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resMsg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public List<TreeGrid> datagrid(TreeGrid treegrid,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(MenuEntity.class);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		if (treegrid.getId() != null) {
			cq.eq("menuEntity.id", treegrid.getId());
		} else {

			cq.isNull("menuEntity");
		}

		cq.addOrder("orders", SortDirection.asc);
		cq.add();

		List<MenuEntity> menuList = systemService.getListByCriteriaQuery(cq,
				false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		// treeGridModel.setIcon("orders");
		treeGridModel.setTextField("name");
		treeGridModel.setParentText("url");
		treeGridModel.setOrder("orders");
		treeGridModel.setSrc("type");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("menuList");
		// 添加排序字段
		treeGrids = systemService.treegrid(menuList, treeGridModel);
		return treeGrids;
	}

	@RequestMapping(params = "jumpSuView")
	public ModelAndView jumpSuView(MenuEntity menuEntity, HttpServletRequest req) {

		org.jeecgframework.core.util.LogUtil.info("...menuEntity.getId()..." + menuEntity.getId());
		if (StringUtil.isNotEmpty(menuEntity.getId())) {
			menuEntity = this.systemService.getEntity(MenuEntity.class,
					menuEntity.getId());
			if (menuEntity.getMenuEntity() != null
					&& menuEntity.getMenuEntity().getId() != null) {
				req
						.setAttribute("fatherId", menuEntity.getMenuEntity()
								.getId());
				req.setAttribute("fatherName", menuEntity.getMenuEntity()
						.getName());
			}
			req.setAttribute("name", menuEntity.getName());
			req.setAttribute("type", menuEntity.getType());
			req.setAttribute("menuKey", menuEntity.getMenuKey());
			req.setAttribute("url", menuEntity.getUrl());
			req.setAttribute("orders", menuEntity.getOrders());
			req.setAttribute("templateId", menuEntity.getTemplateId());
			req.setAttribute("msgType", menuEntity.getMsgType());
		}
		String fatherId = req.getParameter("fatherId");
		if (StringUtil.isNotEmpty(fatherId)) {
			MenuEntity fatherMenuEntity = this.systemService.getEntity(
					MenuEntity.class, fatherId);
			req.setAttribute("fatherId", fatherId);
			req.setAttribute("fatherName", fatherMenuEntity.getName());
			org.jeecgframework.core.util.LogUtil.info(".....fatherName...."
					+ fatherMenuEntity.getName());
		}
		return new ModelAndView("weixin/guanjia/menu/menuinfo");
	}

	@RequestMapping(params = "su")
	@ResponseBody
	public AjaxJson su(MenuEntity menuEntity, HttpServletRequest req,String fatherName) {
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		
		if (StringUtil.isNotEmpty(menuEntity.getId())) {
			
			MenuEntity tempMenu = this.systemService.getEntity(
					MenuEntity.class, menuEntity.getId());
		    MenuEntity menuTemp=new MenuEntity();
		    if(oConvertUtils.isNotEmpty(fatherName)){
		    	menuTemp.setId(fatherName);
		    }
			tempMenu.setMenuEntity(menuTemp);
			tempMenu.getMenuEntity().getId();
			this.message = "更新" + tempMenu.getName() + "的菜单信息信息成功！";
			try {
				MyBeanUtils.copyBeanNotNull2Bean(menuEntity, tempMenu);
				this.weixinMenuService.saveOrUpdate(tempMenu);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				this.message = "更新" + tempMenu.getName() + "的菜单信息信息成功！";
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
				e.printStackTrace();
			}

		} else {
			this.message = "添加" + menuEntity.getName() + "的信息成功！";
			String fatherId = req.getParameter("fatherId");
			if (StringUtil.isNotEmpty(fatherName)) {
				MenuEntity tempMenu = this.systemService.getEntity(
						MenuEntity.class, fatherName);
				menuEntity.setMenuEntity(tempMenu);
			}
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				this.weixinMenuService.save(menuEntity);
			} else {
				j.setSuccess(false);
				j.setMsg("请添加一个公众帐号。");
			}
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		return j;
	}

	@RequestMapping(params = "jumpselect")
	public ModelAndView jumpselect() {
		return new ModelAndView("");
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MenuEntity menuEntity, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		menuEntity = this.systemService.getEntity(MenuEntity.class, menuEntity
				.getId());

		this.systemService.delete(menuEntity);

		message = "删除" + menuEntity.getName() + "菜单信息数据";
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}

	@RequestMapping(params = "sameMenu")
	@ResponseBody
	public AjaxJson sameMenu(MenuEntity menuEntity, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String hql = "from MenuEntity where fatherid is null and accountId = '"
				+ ResourceUtil.getWeiXinAccountId() + "'  order by  orders asc";
		List<MenuEntity> menuList = this.systemService.findByQueryString(hql);
		org.jeecgframework.core.util.LogUtil.info(".....一级菜单的个数是....." + menuList.size());
		Menu menu = new Menu();
		Button firstArr[] = new Button[menuList.size()];
		for (int a = 0; a < menuList.size(); a++) {
			MenuEntity entity = menuList.get(a);
			String hqls = "from MenuEntity where fatherid = '" + entity.getId()
					+ "' and accountId = '" + ResourceUtil.getWeiXinAccountId()
					+ "'  order by  orders asc";
			List<MenuEntity> childList = this.systemService
					.findByQueryString(hqls);
			// org.jeecgframework.core.util.LogUtil.info("....二级菜单的大小....." + childList.size());
			if (childList.size() == 0) {
				if("view".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				}else if("click".equals(entity.getType())){
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getMenuKey());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
				}
			
			} else {
				ComplexButton complexButton = new ComplexButton();
				complexButton.setName(entity.getName());

				Button[] secondARR = new Button[childList.size()];
				for (int i = 0; i < childList.size(); i++) {
					MenuEntity children = childList.get(i);
					String type = children.getType();
					if ("view".equals(type)) {
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType(children.getType());
						viewButton.setUrl(children.getUrl());
						secondARR[i] = viewButton;

					} else if ("click".equals(type)) {

						CommonButton cb1 = new CommonButton();
						cb1.setName(children.getName());
						cb1.setType(children.getType());
						cb1.setKey(children.getMenuKey());
						secondARR[i] = cb1;

					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		String accessToken = weixinAccountService.getAccessToken();
		String url = WeixinUtil.menu_create_url.replace("ACCESS_TOKEN",
				accessToken);
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
			LogUtil.info(jsonObject);
			if(jsonObject!=null){
				if (0 == jsonObject.getInt("errcode")) {
						message = "同步菜单信息数据成功！";
				}
				else {
					message = "同步菜单信息数据失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg");
				}
			}else{
				message = "同步菜单信息数据失败！同步自定义菜单URL地址不正确。";
			}
		} catch (Exception e) {
			message = "同步菜单信息数据失败！";
		}finally{
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
			j.setMsg(this.message);
		}
		return j;
	}
	
	@RequestMapping(params = "treeMenu")
	@ResponseBody
	public List<TreeGrid> treeMenu(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(MenuEntity.class);
		if (StringUtil.isNotEmpty(comboTree.getId())) {
			cq.eq("menuEntity.id", comboTree.getId());
		}
		if (StringUtil.isEmpty(comboTree.getId())) {
			cq.isNull("menuEntity.id");
		}
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		cq.add();
		List<MenuEntity> menuList = weixinMenuService.getListByCriteriaQuery(cq, false);
		
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("name");
		treeGridModel.setParentText("menuEntity_name");
		treeGridModel.setParentId("menuEntity_id");
		treeGridModel.setSrc("url");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("menuList");
		treeGrids = systemService.treegrid(menuList, treeGridModel);
		return treeGrids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}