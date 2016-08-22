package weixin.idea.photo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.idea.photo.entity.WeixinPhotoAlbumEntity;
import weixin.idea.photo.entity.WeixinPhotoEntity;
import weixin.idea.photo.service.WeixinPhotoAlbumServiceI;



/**   
 * @Title: Controller
 * @Description: 微相册
 * @author zhangdaihao
 * @date 2014-07-29 10:08:25
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinPhotoAlbumController")
public class WeixinPhotoAlbumController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinPhotoAlbumController.class);

	@Autowired
	private WeixinPhotoAlbumServiceI weixinPhotoAlbumService;

	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 微相册列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinPhotoAlbum")
	public ModelAndView weixinPhotoAlbum(HttpServletRequest request,WeixinPhotoAlbumEntity weixinPhotoAlbum) {
		CriteriaQuery cq = new CriteriaQuery(WeixinPhotoAlbumEntity.class);
		//查询条件组装器
		cq.eq(ACCOUNTID, ResourceUtil.getWeiXinAccountId());
		cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinPhotoAlbum, request.getParameterMap());
		List<WeixinPhotoAlbumEntity> albums = weixinPhotoAlbumService.getListByCriteriaQuery(cq, false);
		request.setAttribute("albums", albums);
		return new ModelAndView("weixin/idea/photo/weixinPhotoAlbumList");
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
	public void datagrid(WeixinPhotoAlbumEntity weixinPhotoAlbum,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinPhotoAlbumEntity.class, dataGrid);
		//查询条件组装器
		cq.eq(ACCOUNTID, ResourceUtil.getWeiXinAccountId());
		cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinPhotoAlbum, request.getParameterMap());
		this.weixinPhotoAlbumService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微相册
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WeixinPhotoAlbumEntity weixinPhotoAlbum, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String id = request.getParameter("id");
		weixinPhotoAlbum = systemService.getEntity(WeixinPhotoAlbumEntity.class, id);
		//删除物理文件
		weixinPhotoAlbumService.deleteFiles(weixinPhotoAlbum);
		
		message = "微相册删除成功";
		weixinPhotoAlbumService.delete(weixinPhotoAlbum);

		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	/**
	 * 删除相片
	 */
	@RequestMapping(params = "delPhoto")
	@ResponseBody
	public AjaxJson delPhoto(WeixinPhotoEntity weixinPhoto, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinPhoto = systemService.getEntity(WeixinPhotoEntity.class, weixinPhoto.getId());
		
		//删除物理文件
		weixinPhotoAlbumService.deleteFile(weixinPhoto);
		
		message = "相片删除成功";
		//weixinPhotoAlbumService.delete(weixinPhoto);

		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加微相册
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WeixinPhotoAlbumEntity weixinPhotoAlbum, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String albumId = null;
		if (StringUtil.isNotEmpty(weixinPhotoAlbum.getId())) {
			message = "微相册更新成功";
			albumId = weixinPhotoAlbum.getId();
			WeixinPhotoAlbumEntity t = weixinPhotoAlbumService.get(WeixinPhotoAlbumEntity.class, weixinPhotoAlbum.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(weixinPhotoAlbum, t);
				weixinPhotoAlbumService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "微相册更新失败";
			}
		} else {
			String accountId = ResourceUtil.getWeiXinAccountId();
			weixinPhotoAlbum.setAccountid(accountId);
			message = "微相册添加成功";
			albumId = (String) weixinPhotoAlbumService.save(weixinPhotoAlbum);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("albumId", albumId);
		j.setAttributes(attributes);
		return j;
	}
	
	@RequestMapping(params = "savePhoto")
	@ResponseBody
	public AjaxJson savePhoto(WeixinPhotoEntity weixinPhoto, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(weixinPhoto.getId())) {
			message = "相片更新成功";
			WeixinPhotoEntity t = systemService.get(WeixinPhotoEntity.class, weixinPhoto.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(weixinPhoto, t);
				weixinPhotoAlbumService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "相片更新失败";
			}
		} 
		j.setMsg(message);
		return j;
	}

	/**
	 * 微相册列表页面跳转
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WeixinPhotoAlbumEntity weixinPhotoAlbum, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinPhotoAlbum.getId())) {
			weixinPhotoAlbum = weixinPhotoAlbumService.getEntity(WeixinPhotoAlbumEntity.class, weixinPhotoAlbum.getId());
			req.setAttribute("weixinPhotoAlbumPage", weixinPhotoAlbum);
		}
		return new ModelAndView("weixin/idea/photo/weixinPhotoAlbum");
	}
	/**
	 * 相片编辑页面
	 */
	@RequestMapping(params = "goEditPhoto")
	public ModelAndView goEditPhoto(WeixinPhotoEntity weixinPhoto, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinPhoto.getId())) {
			weixinPhoto = weixinPhotoAlbumService.getEntity(WeixinPhotoEntity.class, weixinPhoto.getId());
			req.setAttribute("weixinPhoto", weixinPhoto);
		}
		return new ModelAndView("weixin/idea/photo/weixinPhoto");
	}
	/**
	* 弹出框，上传照片
	 */
	@RequestMapping(params = "uploadPhotoInit")
	public ModelAndView uploadPhotoInit(HttpServletRequest request) {
		request.setAttribute("albumId", request.getParameter("albumId"));
		return new ModelAndView("weixin/idea/photo/uploadPhoto");
	}
	/**
	 * 查看相册已上传的照片
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "viewPhotos")
	public ModelAndView viewPhotos(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		WeixinPhotoAlbumEntity weixinPhotoAlbum = weixinPhotoAlbumService.getEntity(WeixinPhotoAlbumEntity.class, id);
		List<WeixinPhotoEntity> photos = weixinPhotoAlbum.getPhotos();
		if (weixinPhotoAlbum.getPhoto() != null) {
			request.setAttribute("photoId", weixinPhotoAlbum.getPhoto().getId());
		} else {
			request.setAttribute("photoId", "");
		}
		
		request.setAttribute("photos", photos);
		return new ModelAndView("weixin/idea/photo/viewPhotos");
	}

	
	/**
	 * 保存文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, WeixinPhotoEntity photo) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
		
		String albumId = oConvertUtils.getString(request.getParameter("albumId"));//相册ID
		
		if (StringUtil.isNotEmpty(fileKey)) {
			photo.setId(fileKey);
			photo = systemService.getEntity(WeixinPhotoEntity.class, fileKey);

		}
		WeixinPhotoAlbumEntity album = systemService.getEntity(WeixinPhotoAlbumEntity.class, albumId);
		photo.setAlbum(album);
		photo.setName("未命名");
		UploadFile uploadFile = new UploadFile(request, photo);
		uploadFile.setCusPath("files");
		uploadFile.setSwfpath("swfpath");
		uploadFile.setByteField(null);//不存二进制内容
		photo = systemService.uploadFile(uploadFile);
		attributes.put("fileKey", photo.getId());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + photo.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + photo.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);

		return j;
	}
	/**
	 * 设置某照片为封面
	 * @param weixinPhotoAlbum
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "setAlbumFace")
	@ResponseBody
	public AjaxJson setAlbumFace(WeixinPhotoAlbumEntity weixinPhotoAlbum, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String photoId = request.getParameter("photoId");
		WeixinPhotoEntity photo = systemService.get(WeixinPhotoEntity.class, photoId);
		WeixinPhotoAlbumEntity t = weixinPhotoAlbumService.get(WeixinPhotoAlbumEntity.class, weixinPhotoAlbum.getId());
		t.setPhoto(photo);
		weixinPhotoAlbumService.saveOrUpdate(t);
		message = "成功设置封面";
		j.setMsg(message);
		return j;
	}
	/**
	 * 取消封面
	 */
	@RequestMapping(params = "cancelAlbumFace")
	@ResponseBody
	public AjaxJson cancelAlbumFace(WeixinPhotoAlbumEntity weixinPhotoAlbum, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		WeixinPhotoAlbumEntity t = weixinPhotoAlbumService.get(WeixinPhotoAlbumEntity.class, weixinPhotoAlbum.getId());
		t.setPhoto(null);
		weixinPhotoAlbumService.saveOrUpdate(t);
		message = "成功取消封面";
		j.setMsg(message);
		return j;
	}
}
