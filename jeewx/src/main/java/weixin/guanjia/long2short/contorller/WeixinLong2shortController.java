package weixin.guanjia.long2short.contorller;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.util.TokenUtils;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.long2short.entity.WeixinLong2shortEntity;
import weixin.guanjia.long2short.service.WeixinLong2shortServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.core.req.WeiXinReqService;
import org.jeewx.api.wxaccount.JwAccountAPI;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.ResourceBundle;

import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 长连接转短链接
 * @author onlineGenerator
 * @date 2018-03-12 16:01:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinLong2shortController")
public class WeixinLong2shortController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinLong2shortController.class);
	@Autowired
	private WeixinLong2shortServiceI weixinLong2shortService;
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
	 * 长连接转短链接列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinLong2short")
	public ModelAndView weixinLong2short(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/long2short/weixinLong2shortList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinLong2shortEntity weixinLong2short,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinLong2shortEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLong2short, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinLong2shortService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除长连接转短链接
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinLong2shortEntity weixinLong2short, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinLong2short = systemService.getEntity(WeixinLong2shortEntity.class, weixinLong2short.getId());
		message = "长连接转短链接删除成功";
		try{
			weixinLong2shortService.delete(weixinLong2short);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "长连接转短链接删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除长连接转短链接
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "长连接转短链接删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinLong2shortEntity weixinLong2short = systemService.getEntity(WeixinLong2shortEntity.class, 
				id
				);
				weixinLong2shortService.delete(weixinLong2short);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "长连接转短链接删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加长连接转短链接
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinLong2shortEntity weixinLong2short, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "长连接转短链接添加成功";
		try{
			weixinLong2shortService.save(weixinLong2short);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "长连接转短链接添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新长连接转短链接
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinLong2shortEntity weixinLong2short, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "长连接转短链接更新成功";
		WeixinLong2shortEntity t = weixinLong2shortService.get(WeixinLong2shortEntity.class, weixinLong2short.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinLong2short, t);
			weixinLong2shortService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "长连接转短链接更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 长连接转短链接新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinLong2shortEntity weixinLong2short, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinLong2short.getId())) {
			weixinLong2short = weixinLong2shortService.getEntity(WeixinLong2shortEntity.class, weixinLong2short.getId());
			req.setAttribute("weixinLong2shortPage", weixinLong2short);
		}
		return new ModelAndView("weixin/guanjia/long2short/weixinLong2short-add");
	}
	/**
	 * 长连接转短链接编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinLong2shortEntity weixinLong2short, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinLong2short.getId())) {
			weixinLong2short = weixinLong2shortService.getEntity(WeixinLong2shortEntity.class, weixinLong2short.getId());
			req.setAttribute("weixinLong2shortPage", weixinLong2short);
		}
		return new ModelAndView("weixin/guanjia/long2short/weixinLong2short-edit");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/guanjia/long2short/weixinLong2shortUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinLong2shortEntity weixinLong2short,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "长连接转短链接";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(WeixinLong2shortEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLong2short, request.getParameterMap());
			
			List<WeixinLong2shortEntity> weixinLong2shorts = this.weixinLong2shortService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("长连接转短链接列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinLong2shortEntity.class, weixinLong2shorts);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(WeixinLong2shortEntity weixinLong2short,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "长连接转短链接";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("长连接转短链接列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinLong2shortEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(1);
			params.setNeedSave(true);
			try {
				List<WeixinLong2shortEntity> listWeixinLong2shortEntitys = 
					(List<WeixinLong2shortEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinLong2shortEntity.class,params);
				for (WeixinLong2shortEntity weixinLong2short : listWeixinLong2shortEntitys) {
					weixinLong2shortService.save(weixinLong2short);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	/**
	 * 长链接转短链接
	 * @param weixinLong2short
	 * @param request
	 * @return
	 */
	@RequestMapping( params="long2short")
	@ResponseBody
	public AjaxJson long2short(WeixinLong2shortEntity weixinLong2short, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		 
		try {
			if (request.getParameter("id") != null && request.getParameter("id") != ""
					&& request.getParameter("longUrl") != null && request.getParameter("longUrl") != "") {
				// 获取id
				String id = request.getParameter("id");
				// 获取当前的选中的长链接
				String longUrl = request.getParameter("longUrl");
				// 根据id 获取对象实体
				WeixinLong2shortEntity entity = weixinLong2shortService.getEntity(WeixinLong2shortEntity.class, id);
				// 调用微信接口转换短链接
				String accessToken = weixinAccountService.getAccessToken();
				if (accessToken != null && accessToken != "") {
					String shortUrl = JwAccountAPI.getShortUrl(accessToken, longUrl);
					entity.setId(id);
					entity.setShortUrl(shortUrl);
					weixinLong2shortService.doUpdateSql(entity);
					message="链接转换成功";
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					j.setSuccess(true);
				} else {
					j.setSuccess(false);
					j.setMsg("获取用户accessToken失败");
				}
			} else {
				j.setSuccess(false);
				j.setMsg("链接转换失败！");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("长连接转短链接失败");
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 弹出短链接
	 * @param modelMap
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "poplink")
	public ModelAndView poplink(ModelMap modelMap, @RequestParam String id,HttpServletRequest request,HttpServletResponse response) 
	{
		WeixinLong2shortEntity entity = weixinLong2shortService.getEntity(WeixinLong2shortEntity.class, id);
        modelMap.put("url",entity.getShortUrl());
		return new ModelAndView("weixin/guanjia/long2short/poplinkurl");
	}
}
