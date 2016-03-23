package weixin.guanjia.message.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import weixin.util.DateUtils;

@Controller
@RequestMapping("/actions/ckeditorUpload")
public class CkeUploadController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(CkeUploadController.class);
	//
	@Autowired
	private SystemService systemService;
	//
	private String sep = System.getProperty("file.separator");
    /**
     * 上传附件
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(params = "upload")
	public String upload(HttpServletRequest request,
			HttpServletResponse response) {
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		//
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		//
		String day = DateUtils.date2SStr();
		//
		String path = mRequest.getSession().getServletContext()
				.getRealPath("/");
		String base_save_path = "upload" + sep + day + sep;
		//
		String url_base_path = "upload/" + day + "/";
		//
		String save_path = path + base_save_path;
		File save_folder = new File(save_path);
		if (!save_folder.exists()) {
			save_folder.mkdirs();
		}
		//
		UUID uuid = UUID.randomUUID();
		String callback = request.getParameter("CKEditorFuncNum");
		String save_script = "<script type=\"text/javascript\">";
		//
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			//
			try {
				// 取原文件名
				String file_name = mf.getOriginalFilename().trim();
				//
				file_name = file_name.toLowerCase();
				String save_file_name = uuid.toString().replaceAll("-", "")
						+ file_name.substring(file_name.lastIndexOf("."));
				//
				String savePath = save_path + save_file_name;
				//
				save_script += "window.parent.CKEDITOR.tools.callFunction("
						+ callback + ",'" + url_base_path + save_file_name
						+ "','');";
				//
				File savefile = new File(savePath);
				//
				FileCopyUtils.copy(mf.getBytes(), savefile);
				//
				savefile = null;
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
		}
		save_script += "</script>";
		//
		request.setAttribute("list", save_script);
		//
		return "weixin/guanjia/api/string";
		
	}
}
