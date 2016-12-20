package com.jeecg.alipay.account.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayMobilePublicMessageTotalSendResponse;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.base.JwSendMessageAPI;
import com.jeecg.alipay.api.base.vo.SendMessageImageTextMoreVo.SendMessageImageTextMore;
import com.jeecg.alipay.api.base.vo.SendMessageTextMoreVo.SendMessageTextMore;
import com.jeecg.alipay.api.core.AlipayConfig;
import com.jeecg.alipay.base.dao.AlipayMessagelogDao;
import com.jeecg.alipay.base.entity.AlipayMessageLog;
import com.jeecg.alipay.core.util.AlipayUtil;
import com.jeecg.alipay.sucai.dao.AlipayNewsitemDao;
import com.jeecg.alipay.sucai.dao.AlipayNewstemplateDao;
import com.jeecg.alipay.sucai.entity.AlipayNewsitem;
import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;
import com.jeecg.alipay.util.ConfigUtil;
import com.jeecg.alipay.util.SystemUtil;

/**
 * 描述：</b>AlipayMenuController<br>
 * 自定义菜单表
 * 
 * @author p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/groupMsg")
public class AlipayGroupMsgController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AlipayGroupMsgController.class);
	@Autowired
	private AlipayNewstemplateDao alipayNewstemplateDao;
	@Autowired
	private AlipayNewsitemDao alipayNewsitemDao;
	@Autowired
	private AlipayAccountService alipayAccountService;
	@Autowired
	private AlipayMessagelogDao alipayMessagelogDao;
	/**
	 * 跳转到编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toGroupMsgSend", method = { RequestMethod.GET, RequestMethod.POST })
	public void toGroupMsgSend(HttpServletResponse response, HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		// update-start--Author:chenchunpeng  Date:20160715 for：修改图文属性文件
		String yuming = ConfigUtil.getProperty("domain");
		// update-end--Author:chenchunpeng  Date:20160715 for：修改图文属性文件
		velocityContext.put("yuming", yuming);
		String viewName = "alipay/msg/groupMsgSend.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	// 跳 转到图文信息详细页面
	@RequestMapping(params = "getAllUploadNewsTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public void getAllUploadNewsTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {

		VelocityContext velocityContext = new VelocityContext();
		String viewName = "alipay/msg/showGroupMessageNews.vm";
		String symbol = request.getParameter("symbol");
		// 选择图文素材
		if ("page".equals(symbol)) {
			// 查询所有模版
			List<AlipayNewstemplate> templateList = alipayNewstemplateDao.getAllAlipayNewstemplate(SystemUtil.getOnlieAlipayAccountId());
			// 遍历模版
			// 遍历每条模搬对应的子表记录
			for (AlipayNewstemplate template : templateList) {
				String templateId = template.getId();
				List<AlipayNewsitem> item = alipayNewsitemDao.getALLNews(templateId);
				template.setiNewsitem(item);
			}
			velocityContext.put("templateList", templateList);

		}

		String yuming = ConfigUtil.getProperty("ftp_img_domain");
		velocityContext.put("yuming", yuming);
		try {
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 群发信息
	@RequestMapping(params = "toGroupTextSend", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson toGroupTextSend(HttpServletResponse response, HttpServletRequest request) {
		alipayAccountService.setAlipayConfig();
		AjaxJson j = new AjaxJson();
		try {
			AlipayConfig config = alipayAccountService.getAlipayConfig();
			if(config == null){
				j.setSuccess(false);
				j.setMsg("请先添加账号");
				return j;
			}
			String msgtype = request.getParameter("msgtype");// 类型
			String param = request.getParameter("param");// 文本框的内容
			if ("text".equals(msgtype)) {
			 	SendMessageTextMore text= AlipayUtil.wrapperGroupTextMessage(param);
			 	//update-begin--author:zhangjiaqiang Date:20161108 for:对接最新支付窗API
			 	//发送消息
			 	AlipayMobilePublicMessageTotalSendResponse alipayresponse = JwSendMessageAPI.messageTotalSend(null, JSONObject.toJSONString(text),config);
			 	//update-end--author:zhangjiaqiang Date:20161108 for:对接最新支付窗API
			 	String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			 	AlipayMessageLog log = new AlipayMessageLog(id, "文本消息", param, null, "",new Date() );
			 	if (null != alipayresponse && alipayresponse.getCode().equals("200")) {
					j.setSuccess(true);
					j.setMsg("执行成功！");
					log.setReceiveMessage(alipayresponse.getMsg());
				}else{
					j.setSuccess(false);
					j.setMsg("错误代码"+alipayresponse.getCode()+","+alipayresponse.getMsg());
					log.setReceiveMessage(alipayresponse.getMsg());
				}
			 	alipayMessagelogDao.insert(log);
			}else if("news".equals(msgtype)){
				String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
				String templateId = request.getParameter("templateId");
			 	AlipayMessageLog log = new AlipayMessageLog(id, "图文消息", "", templateId, "",new Date() );
				List<AlipayNewsitem> newsList = alipayNewsitemDao.getALLNews(templateId);
				SendMessageImageTextMore imgtext = AlipayUtil.wrapperGroupNewsMessage(newsList);
				//update-begin--author:zhangjiaqiang Date:20161108 for:对接最新支付窗API
				AlipayMobilePublicMessageTotalSendResponse alipayresponse = JwSendMessageAPI.messageTotalSend(null, JSONObject.toJSONString(imgtext),alipayAccountService.getAlipayConfig());
				//update-end--author:zhangjiaqiang Date:20161108 for:对接最新支付窗API
				//update-begin--author:zhangjiaqiang Date:20161108 for:修订返回响应的是否成功判断
				if (null != alipayresponse && "200".equals(alipayresponse.getCode())) {
				//update-end--author:zhangjiaqiang Date:20161108 for:修订返回响应的是否成功判断
					j.setSuccess(true);
					j.setMsg("执行成功！");
					log.setReceiveMessage(alipayresponse.getMsg());
				}else{
					j.setSuccess(false);
					//update-begin--author:zhangjiaqiang Date:20161108 for:修订失败的显示消息
					j.setMsg("发送失败："+alipayresponse.getMsg());
					//update-end--author:zhangjiaqiang Date:20161108 for:修订失败的显示消息
					log.setReceiveMessage(alipayresponse.getMsg());
				}
				alipayMessagelogDao.insert(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("发送失败");
		}
		return j;

	}

	// 文本框内显示图片的详细信息
	@RequestMapping(params = "toGroupNewsSend", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson toGroupNewsSend(HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String templateId = request.getParameter("templateId");
			// 根据templateId查出item集合。
			List<AlipayNewsitem> item = alipayNewsitemDao.getALLNews(templateId);
			j.setObj(item);

		} catch (Exception e) {
			log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("显示失败!");
		}
		return j;

	}
}
