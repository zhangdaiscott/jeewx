package com.jeecg.alipay.sucai.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.alipay.sucai.dao.AlipayNewsitemDao;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.util.AlipayResourceUtil;

 /**
 * 描述：</b>AlipayNewsitemController<br>图文素材新闻; InnoDB free: 130048 kB
 * @author zhoujf
 * @since：2016年03月24日 18时59分46秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayNewsitem")
public class AlipayNewsitemController extends BaseController{
  @Autowired
  private AlipayNewsitemDao alipayNewsitemDao;
  @Autowired
  private AlipayNewstemplateDao alipayNewstemplateDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayNewsitem query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayNewsitem> list =  alipayNewsitemDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayNewsitem",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				String viewName = "alipay/sucai/alipayNewsitem-list.vm";
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
	public void alipayNewsitemDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/sucai/alipayNewsitem-detail.vm";
			AlipayNewsitem alipayNewsitem = alipayNewsitemDao.get(id);
			velocityContext.put("alipayNewsitem",alipayNewsitem);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(@RequestParam(required = true, value = "templateId" ) String templateId,HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/sucai/alipayNewsitem-add.vm";
		 velocityContext.put("templateId", templateId);
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayNewsitem alipayNewsitem){
		AjaxJson j = new AjaxJson();
		try {
		    String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		    alipayNewsitem.setId(randomSeed);
			alipayNewsitemDao.insert(alipayNewsitem);
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
			 AlipayNewsitem alipayNewsitem = alipayNewsitemDao.get(id);
			 velocityContext.put("alipayNewsitem",alipayNewsitem);
			 String viewName = "alipay/sucai/alipayNewsitem-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(params="goMessage",method = RequestMethod.GET)
	public void goMessage(@RequestParam(required = true, value = "templateId" ) String templateId,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 List<AlipayNewsitem> headerList = alipayNewsitemDao.getAlipayNewsitemByTemplateId(templateId);
			 if(headerList.size()>0){
				 velocityContext.put("headerNews", headerList.get(0));
					if(headerList.size()>1){
						ArrayList list = new ArrayList(headerList);
						list.remove(0);
						velocityContext.put("newsList", list);
					}
				} 
			 AlipayNewstemplate alipayNewstemplate = alipayNewstemplateDao.get(templateId);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			 if(alipayNewstemplate.getCreateDate()!=null){
				 velocityContext.put("addtime", sdf.format(alipayNewstemplate.getCreateDate()));
			 }
			 String viewName = "alipay/sucai/alipayNewsitem-showmessage.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayNewsitem alipayNewsitem){
		AjaxJson j = new AjaxJson();
		try {
			alipayNewsitemDao.update(alipayNewsitem);
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
			    AlipayNewsitem alipayNewsitem = new AlipayNewsitem();
				alipayNewsitem.setId(id);
				alipayNewsitemDao.delete(alipayNewsitem);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	
	/**
     * 上传文件信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "doUpload", method = RequestMethod.POST)
    @ResponseBody
    public  AjaxJson doUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		try {
			String basePath = request.getSession().getServletContext().getRealPath("/");
			//获取所有文件名称  
			Iterator<String> it = request.getFileNames();  
			while(it.hasNext()){  
			    //根据文件名称取文件  
			    MultipartFile multifile = request.getFile(it.next());  
			    String fileName = multifile.getOriginalFilename(); 
			    String filePath = "upload/files/"; 
			    File file = new File(basePath+filePath);
				if (!file.exists()) {
					file.mkdir();// 创建文件根目录
				}
				filePath = filePath+fileName;
			    String savePath = basePath+filePath;
			    System.out.println(savePath);
			    File newFile = new File(savePath);  
			    //上传的文件写入到指定的文件中  
			    multifile.transferTo(newFile);  
			    attributes.put("url", filePath);
			    attributes.put("fileKey", fileName);
			}
			j.setMsg("文件上传成功");
			j.setAttributes(attributes);
		}  catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("文件上传失败");
		}  

		return j;
    }
    /**
	 * 转向信息页面
	 * @param request
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(params="goContent")
	public void goContent(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		AlipayNewsitem newsItem = alipayNewsitemDao.get(id);
		velocityContext.put("newsItem", newsItem);
		velocityContext.put("domain", AlipayResourceUtil.getDomain());
		String viewName = "alipay/sucai/content/newsContent.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
   

}

