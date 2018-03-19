package weixin.p3.linksucai.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.p3.linksucai.entity.WeixinLinksucaiEntity;
import weixin.p3.linksucai.service.WeixinLinksucaiServiceI;
import weixin.p3.oauth2.rule.RemoteWeixinMethod;
import weixin.p3.oauth2.util.SignatureUtil;



/**   
 * @Title: Controller
 * @Description: 链接素材
 * @author onlineGenerator
 * @date 2015-01-22 21:39:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/weixinLinksucaiController")
public class WeixinLinksucaiController extends BaseController {
	/**
	 * 签名密钥key
	 */
	private static final String SIGN_KEY = "4B6CAED6F7B19126F72780372E839CC47B1912B6CAED753F";
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinLinksucaiController.class);

	@Autowired
	private WeixinLinksucaiServiceI weixinLinksucaiService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RemoteWeixinMethod remoteWeixinMethod;
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
     * 转向私有模板
     * @return
     */
	@RequestMapping(params = "privateList")
	public ModelAndView privateList() {
		return new ModelAndView("weixin/guanjia/linksucai/privateWeixinLinksucaiList");
	}
	
    @RequestMapping(params = "privateDatagrid")
	@ResponseBody
	/**
	 * 私有查询数据
	 * @param newsTemplate
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	public void privateDatagrid(WeixinLinksucaiEntity weixinLinksucai,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    	CriteriaQuery cq = new CriteriaQuery(WeixinLinksucaiEntity.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getShangJiaAccountId());
		cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,weixinLinksucai);
		this.weixinLinksucaiService.getDataGridReturn(cq, true);
		String baseurl = ResourceUtil.getConfigByName("domain");
		
		for(int i=0;i<dataGrid.getResults().size();i++)
		{
			WeixinLinksucaiEntity  t=(WeixinLinksucaiEntity) dataGrid.getResults().get(i);
			
			String inner_link = baseurl+"/weixinLinksucaiController.do?link&id="+t.getId();
			t.setInnerLink(inner_link);
		}

		List<WeixinAccountEntity> accountList = weixinAccountService.loadAll(WeixinAccountEntity.class);

		TagUtil.datagrid(response, dataGrid);
	}
    
	/**
	 * 链接跳转
	 */
	@RequestMapping(params = "link")
	public void link(WeixinLinksucaiEntity weixinLinksucai,HttpServletRequest request, HttpServletResponse response) {
		//获取请求路径
		String backUrl = this.getRequestUrlWithParams(request);
		//URL配置ID
		String id = request.getParameter("id");
		//URL配置信息
		weixinLinksucai = systemService.getEntity(WeixinLinksucaiEntity.class, id);
		//微信公众账号ID
		String accountid = weixinLinksucai.getAccountid();

		//如果带有参数jwid，标示指定公众号，逻辑如下
		String jwid = request.getParameter("jwid");
		if(oConvertUtils.isNotEmpty(jwid)){
			WeixinAccountEntity weixinAccountEntity = weixinAccountService.getWeixinAccountByWeixinOldId(jwid);
			if(weixinAccountEntity!=null){
				accountid = weixinAccountEntity.getId();
			}
		}

		
		
		//微信用户 Openid
		String openid = ResourceUtil.getUserOpenId();
		//跳转出链接
		String outer_link_deal = null;
	    String requestQueryString = (request.getRequestURL() + "?" + request.getQueryString()).replace(weixinLinksucai.getInnerLink(), "");
	    String outUrl = weixinLinksucai.getOuterLink();
	    if(oConvertUtils.isNotEmpty(requestQueryString)){
	    	outUrl = outUrl + requestQueryString;
	    }
		//如果为空则调用author2.0接口
		if(oConvertUtils.isEmpty(openid)){
			 outer_link_deal = remoteWeixinMethod.callWeixinAuthor2ReturnUrl(request, accountid, backUrl);
		}
		if(oConvertUtils.isEmpty(outer_link_deal)){
		    openid = ResourceUtil.getUserOpenId();
		    System.out.println("------------------begin----------begin1-------------------");
			outer_link_deal = weixinLinksucaiService.installOuterLinkWithSysParams(outUrl, openid, accountid,null);
			System.out.println("------------------begin----------begin2-------------------");
		}
		
		try {
			if(outer_link_deal.indexOf("https://open.weixin.qq.com")!=-1){
				//针对调整到auth2.0链接不加签名
				response.sendRedirect(outer_link_deal);
			}else{
				//针对参数加签，防止用户篡改
				String sign = SignatureUtil.sign(SignatureUtil.getSignMap(outer_link_deal), SIGN_KEY);
				response.sendRedirect(outer_link_deal+"&sign="+sign);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除链接素材
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinLinksucaiEntity weixinLinksucai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinLinksucai = systemService.getEntity(WeixinLinksucaiEntity.class, weixinLinksucai.getId());
		message = "链接素材删除成功";
		
		if(!weixinLinksucai.getAccountid().equals(ResourceUtil.getShangJiaAccountId())){
			message="父级共享的数据子公众帐号不能操作";
		}else{
			try{
				weixinLinksucaiService.delete(weixinLinksucai);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "链接素材删除失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除链接素材
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "链接素材删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinLinksucaiEntity weixinLinksucai = systemService.getEntity(WeixinLinksucaiEntity.class, 
				id
				);
				if(!weixinLinksucai.getAccountid().equals(ResourceUtil.getShangJiaAccountId())){
					continue;
				}
				weixinLinksucaiService.delete(weixinLinksucai);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "链接素材删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加链接素材
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinLinksucaiEntity weixinLinksucai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "链接素材添加成功";
		try{
			weixinLinksucaiService.save(weixinLinksucai);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "链接素材添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新链接素材
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinLinksucaiEntity weixinLinksucai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "链接素材更新成功";
		WeixinLinksucaiEntity t = weixinLinksucaiService.get(WeixinLinksucaiEntity.class, weixinLinksucai.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinLinksucai, t);
			weixinLinksucaiService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "链接素材更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 链接素材新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinLinksucaiEntity weixinLinksucai, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinLinksucai.getId())) {
			weixinLinksucai = weixinLinksucaiService.getEntity(WeixinLinksucaiEntity.class, weixinLinksucai.getId());
			req.setAttribute("weixinLinksucaiPage", weixinLinksucai);
			
		}
		req.setAttribute("accountid", ResourceUtil.getShangJiaAccountId());
		return new ModelAndView("weixin/guanjia/linksucai/weixinLinksucai-add");
	}
	/**
	 * 链接素材编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinLinksucaiEntity weixinLinksucai, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinLinksucai.getId())) {
			weixinLinksucai = weixinLinksucaiService.getEntity(WeixinLinksucaiEntity.class, weixinLinksucai.getId());
			req.setAttribute("weixinLinksucaiPage", weixinLinksucai);
			
		}
		req.setAttribute("accountid", ResourceUtil.getShangJiaAccountId());
		return new ModelAndView("weixin/guanjia/linksucai/weixinLinksucai-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/guanjia/linksucai/weixinLinksucaiUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinLinksucaiEntity weixinLinksucai,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "链接素材";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinLinksucaiEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLinksucai, request.getParameterMap());
			
			List<WeixinLinksucaiEntity> weixinLinksucais = this.weixinLinksucaiService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("链接素材列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinLinksucaiEntity.class, weixinLinksucais);
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
	public void exportXlsByT(WeixinLinksucaiEntity weixinLinksucai,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "链接素材";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("链接素材列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinLinksucaiEntity.class, null);
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
				List<WeixinLinksucaiEntity> listWeixinLinksucaiEntitys = 
					(List<WeixinLinksucaiEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinLinksucaiEntity.class,params);
				for (WeixinLinksucaiEntity weixinLinksucai : listWeixinLinksucaiEntitys) {
					weixinLinksucaiService.save(weixinLinksucai);
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
	
	@RequestMapping(params = "poplink")
	public ModelAndView poplink(ModelMap modelMap,
                                    @RequestParam String id) 
	{
		//WeixinLinksucaiEntity weixinLinksucai = weixinLinksucaiService.getEntity(WeixinLinksucaiEntity.class, id);
		
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		String absolutePathUrl =  bundler.getString("domain")  + "/weixinLinksucaiController.do?link&id=" + id;
        modelMap.put("url",absolutePathUrl);
		return new ModelAndView("weixin/guanjia/linksucai/poplinksucai");
	}
	
	
	
	/**
     * 获取Request请求的路径信息 带参数
     * @param request
     * @return
     */
    private static String getRequestUrlWithParams(HttpServletRequest request){
  	  String backurl = request.getScheme()+"://"+request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
  	  return backurl;
    }
}
