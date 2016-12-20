package com.jeecg.qywx.base.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.jeecg.qywx.account.entity.QywxMenu;
import com.jeecg.qywx.account.service.AccountService;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.api.department.JwDepartmentAPI;
import com.jeecg.qywx.api.department.vo.Department;
import com.jeecg.qywx.base.dao.QywxGroupDao;
import com.jeecg.qywx.base.entity.QywxGroup;
import com.jeecg.qywx.util.SystemUtil;

 /**
 * 描述：</b>QywxGroupController<br>关注用户组; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月25日 13时41分22秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/qywx/qywxGroup")
public class QywxGroupController extends BaseController{
  @Autowired
  private QywxGroupDao qywxGroupDao;
  @Autowired
  private AccountService accountService;
  
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute QywxGroup query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "1000") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<QywxGroup> list =  new MiniDaoPage<QywxGroup>();
				List<QywxGroup> groupList= qywxGroupDao.getAll(query);
				groupList = sortTreeList(groupList);
				list.setResults(groupList);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("qywxGroup",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				velocityContext.put("num", groupList.size());
				String viewName = "qywx/base/qywxGroup-list.vm";
				ViewVelocity.view(request,response,viewName,velocityContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private List<QywxGroup> sortTreeList(List<QywxGroup> groupList){
		long start = System.currentTimeMillis();
		List<QywxGroup> list = new ArrayList<QywxGroup>();
		List<QywxGroup> levelTopList = getLevelTopList(groupList);
		for(QywxGroup top:levelTopList){
			list.add(top);
			List<QywxGroup> childlist =getChildList(top,groupList);
			if(childlist!=null&&childlist.size()>0){
				list.addAll(childlist);
			}
		}
		System.out.println("-----------sortTreeList耗时-------------"+(System.currentTimeMillis()-start)+"ms");
		return list;
	}
	
	private List<QywxGroup> getLevelTopList(List<QywxGroup> groupList){
		List<QywxGroup> list = new ArrayList<QywxGroup>();
		for(QywxGroup group:groupList){
			if(StringUtil.isEmpty(group.getParentid()) || "0".equals(group.getParentid())){
				list.add(group);
			}
		}
		return list;
	}
	
	private List<QywxGroup> getChildList(QywxGroup prarntGroup,List<QywxGroup> groupList){
		List<QywxGroup> childlist = new ArrayList<QywxGroup>();
		for(QywxGroup group:groupList){
			if(prarntGroup.getId().equals(group.getParentid())){
				childlist.add(group);
				List<QywxGroup> list = getChildList(group,groupList);
				if(list!=null&&list.size()>0){
					childlist.addAll(list);
				}
			}
		}
		return childlist;
	}

	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(params="toDetail",method = RequestMethod.GET)
	public void qywxGroupDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qywx/base/qywxGroup-detail.vm";
			QywxGroup qywxGroup = qywxGroupDao.get(id);
			velocityContext.put("qywxGroup",qywxGroup);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(@RequestParam(required = false, value = "pid" ) String pid,HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 QywxGroup qywxGroup = null;
		 if(!StringUtil.isEmpty(pid)){
			 qywxGroup = qywxGroupDao.get(pid);
		 }
		 velocityContext.put("qywxGroup",qywxGroup);
		 String viewName = "qywx/base/qywxGroup-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute QywxGroup qywxGroup){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    qywxGroup.setId(randomSeed);
		    qywxGroup.setCreateDate(new Date());
			qywxGroupDao.insert(qywxGroup);
			j.setMsg("保存成功");
			
			//----[微信接口调用]------------------------------------
			AccessToken accessToken = accountService.getAccessToken();
			Department dp = new Department();
			dp.setId(qywxGroup.getId());
			dp.setName(qywxGroup.getName());
			dp.setOrder(qywxGroup.getOrders());
			dp.setParentid(qywxGroup.getParentid());
			JwDepartmentAPI.createDepartment(dp, accessToken.getAccesstoken());
			//----[微信接口调用]------------------------------------
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
			 QywxGroup qywxGroup = qywxGroupDao.get(id);
			 velocityContext.put("qywxGroup",qywxGroup);
			 String viewName = "qywx/base/qywxGroup-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute QywxGroup qywxGroup){
		AjaxJson j = new AjaxJson();
		try {
			qywxGroupDao.update(qywxGroup);
			j.setMsg("编辑成功");
			
			//----[微信接口调用]------------------------------------
			AccessToken accessToken = accountService.getAccessToken();
			Department dp = new Department();
			dp.setId(qywxGroup.getId());
			dp.setName(qywxGroup.getName());
			dp.setOrder(qywxGroup.getOrders());
			dp.setParentid(qywxGroup.getParentid());
			JwDepartmentAPI.updateDepart(dp, accessToken.getAccesstoken());
			//----[微信接口调用]------------------------------------
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
				List<QywxGroup> groupList= qywxGroupDao.getQywxGroupByParentid(id);
				if(groupList!=null&&groupList.size()>0){
					j.setSuccess(false);
					j.setMsg("存在下级不能删除");
					return j;
				}
				qywxGroupDao.delete(id);
				j.setMsg("删除成功");
				
				//----[微信接口调用]------------------------------------
				AccessToken accessToken = accountService.getAccessToken();
				JwDepartmentAPI.deleteDepart(id, accessToken.getAccesstoken());
				//----[微信接口调用]------------------------------------
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	
	/**
	 * 同步组织机构（从微信服务器同步下来）
	 * @return
	 */
	@RequestMapping(params="doSynch",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doSynch(@ModelAttribute QywxMenu query){
			AjaxJson j = new AjaxJson();
			try {
				AccessToken accessToken = accountService.getAccessToken();
				List<Department> ls = JwDepartmentAPI.getAllDepartment(accessToken.getAccesstoken());
				for(Department po:ls){
					//判断数据库是否存在，无则插入
					QywxGroup c = qywxGroupDao.get(po.getId());
					if(c==null){
						c = new QywxGroup();
						c.setId(po.getId());
						c.setName(po.getName());
						c.setParentid(po.getParentid());
						c.setOrders(po.getOrder()+"");
						qywxGroupDao.insert(c);
					}else{
						c.setId(po.getId());
						c.setName(po.getName());
						c.setParentid(po.getParentid());
						c.setOrders(po.getOrder()+"");
						qywxGroupDao.update(c);
					}
				}
				
				j.setSuccess(true);
				j.setMsg("同步部门成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
			    e.getStackTrace();
				j.setSuccess(false);
				j.setMsg("同步部门失败");
			}
			return j;
	}
	
	/**
	 * 得到组织机构树
	 * 
	 * @return
	 */
	@RequestMapping(params = "getGroupTree", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getGroupTree(HttpServletRequest request,
			HttpServletResponse response) {
		String tree = "";
		try {
			List<QywxGroup> groupList= qywxGroupDao.getAll(new QywxGroup());
			tree = SystemUtil.listGroupToTree(groupList);
			log.info("组织机构树: " + tree);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return tree;
	}
	
	


}

