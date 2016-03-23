package weixin.guanjia.message.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.util.DateUtils;


/**   
 * @Title: Controller
 * @Description: 微信图文-明细页面
 * @author onlineGenerator
 * @date 2014-01-09 21:55:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/weixinArticleController")
public class WeixinArticleController extends BaseController {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinArticleController.class);

	@Autowired
	private NewsItemServiceI newsItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 微信单图消息列表 页面跳转
	 * @return
	 */
	@RequestMapping(params = "goMessage")
	public ModelAndView goMessage(HttpServletRequest request) {
		String templateId = request.getParameter("templateId");
		//request.setAttribute("templateId", templateId);
		if(StringUtil.isNotEmpty(templateId)){
			String hql = "from NewsItem where newsTemplate.id='"+templateId+"' order by orders asc";
			org.jeecgframework.core.util.LogUtil.info("...hql..."+hql);
			List<NewsItem> headerList = this.systemService.findByQueryString(hql);
			if(headerList.size()>0){
				request.setAttribute("headerNews", headerList.get(0));
				if(headerList.size()>1){
					ArrayList list = new ArrayList(headerList);
					list.remove(0);
					request.setAttribute("newsList", list);
				}
			} 
			NewsTemplate newsTemplate = this.systemService.getEntity(NewsTemplate.class, templateId);
			String temp = newsTemplate.getAddTime().replace("-", "/");
			Date addTime = new Date(temp);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			request.setAttribute("addtime", sdf.format(addTime));
		}
		return new ModelAndView("weixin/guanjia/newstemplate/showmessage");
	}

	/**
	 * easyui AJAX请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(NewsItem weixinArticle,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NewsItem.class, dataGrid);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinArticle, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.newsItemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微信单图消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NewsItem weixinArticle, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinArticle = systemService.getEntity(NewsItem.class, weixinArticle.getId());
		message = "微信单图消息删除成功";
		try{
			newsItemService.delete(weixinArticle);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "微信单图消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除微信单图消息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "微信单图消息删除成功";
		try{
			for(String id:ids.split(",")){
				NewsItem weixinArticle = systemService.getEntity(NewsItem.class, id);
				newsItemService.delete(weixinArticle);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "微信单图消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加微信单图消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(NewsItem weixinArticle, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信单图消息添加成功";
		try{
			String templateId = request.getParameter("templateId");
			NewsTemplate temp1 = this.systemService.getEntity(NewsTemplate.class, templateId);
			weixinArticle.setNewsTemplate(temp1);
			String accountId = ResourceUtil.getWeiXinAccountId();
			if (!"-1".equals(accountId)) {
				newsItemService.save(weixinArticle);
				}else{
					j.setSuccess(false);
					j.setMsg("请添加一个公众帐号。");
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "微信单图消息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新微信单图消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(NewsItem weixinArticle, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信单图消息更新成功";
		NewsItem t = newsItemService.get(NewsItem.class, weixinArticle.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinArticle, t);
			newsItemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信单图消息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 微信单图消息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(NewsItem weixinArticle, HttpServletRequest req) {
		String templateId = req.getParameter("templateId");
		req.setAttribute("templateId", templateId);
		if (StringUtil.isNotEmpty(weixinArticle.getId())) {
			weixinArticle = newsItemService.getEntity(NewsItem.class, weixinArticle.getId());
			req.setAttribute("weixinArticlePage", weixinArticle);
		}
		return new ModelAndView("weixin/guanjia/newstemplate/weixinArticle-add");
	}
	/**
	 * 微信单图消息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(NewsItem weixinArticle, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinArticle.getId())) {
			weixinArticle = newsItemService.getEntity(NewsItem.class, weixinArticle.getId());
			req.setAttribute("weixinArticle", weixinArticle);
		}
		return new ModelAndView("weixin/guanjia/newstemplate/weixinArticle-update");
	}
	
    /**
     * 保存文件信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "upload", method = RequestMethod.POST)
    @ResponseBody
    public  AjaxJson upload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("files","附件", tsTypegroup);
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
		TSDocument document = new TSDocument();
		if (StringUtil.isNotEmpty(fileKey)) {
			document.setId(fileKey);
			document = systemService.getEntity(TSDocument.class, fileKey);
			document.setDocumentTitle(documentTitle);

		}
		document.setSubclassname(MyClassLoader.getPackPath(document));
		document.setCreatedate(DateUtils.gettimestamp());
		document.setTSType(tsType);
		UploadFile uploadFile = new UploadFile(request, document);
		uploadFile.setCusPath("files");
		uploadFile.setSwfpath("swfpath");
		document = systemService.uploadFile(uploadFile);
		attributes.put("url", document.getRealpath());
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?openViewFile&fileid=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);

		return j;
    }
}
