package weixin.guanjia.message.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.ReceiveText;
import weixin.guanjia.message.model.TextItem;
import weixin.guanjia.message.model.TextMessageKf;
import weixin.guanjia.message.service.CustomerMessageService;


/**
 * 文本消息收件箱
* 
 */
@Controller
@RequestMapping("/receiveTextController")
public class ReceiveTextController {
	
	private SystemService systemService;
	private String message;
	private CustomerMessageService customerMessageService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	public void setCustomerMessageService(
			CustomerMessageService customerMessageService) {
		this.customerMessageService = customerMessageService;
	}

	@Resource(name="systemService")
    public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
    @RequestMapping(params="list")
	public ModelAndView jumpList(){
		return new ModelAndView("weixin/guanjia/receivetext/receivetextlist");
	}
    
    @RequestMapping(params = "datagrid")
	@ResponseBody
	public void datagrid(ReceiveText receiveText,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		CriteriaQuery cq = new CriteriaQuery(ReceiveText.class, dataGrid);
		
		//cq.eq("toUserName", ResourceUtil.getSessionAccountId());
		WeixinAccountEntity weixinAccountEntity = this.weixinAccountService.findLoginWeixinAccount();
		if(weixinAccountEntity!=null){
			String accountId = weixinAccountEntity.getWeixin_accountid();
			cq.eq("accountId", accountId);
		}
		//排序 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("createTime", "desc");
		cq.setOrder(map);
		//cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receiveText);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
    
  
	@RequestMapping(params="del")
	@ResponseBody
	public AjaxJson deleteSmsGroup(ReceiveText receiveText,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		receiveText = this.systemService.getEntity(ReceiveText.class, receiveText.getId());
		
		this.systemService.delete(receiveText);
	
		message = "删除信息数据成功！";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(this.message);
		return j;
	}
	
	
	@RequestMapping(params="jumpsendmessage")
	public ModelAndView responseMessage(HttpServletRequest req){
		String id = req.getParameter("id");
		req.setAttribute("id", id);
		return new ModelAndView("weixin/guanjia/receivetext/messageinfo");
	}

	@RequestMapping(params="update")
	@ResponseBody
	public AjaxJson updateAndSave(ReceiveText receiveText,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		ReceiveText  tempTextMessage= this.systemService.getEntity(ReceiveText.class, receiveText.getId());
		this.message =  "回复信息成功！";
		try {
			MyBeanUtils.copyBeanNotNull2Bean(receiveText, tempTextMessage);
			tempTextMessage.setResponse("1");
			this.systemService.saveOrUpdate(tempTextMessage);
			
			String resContent = tempTextMessage.getRescontent();
			String openId = tempTextMessage.getFromUserName();
			
			TextMessageKf customMessage = new TextMessageKf();
			customMessage.setMsgtype("text");
			TextItem textItem = new TextItem();
			textItem.setContent(resContent);
			customMessage.setText(textItem);
			customMessage.setTouser(openId);
			
			JSONObject jsonObj = JSONObject.fromObject(customMessage);
			System.out.println("......jsonObj..."+jsonObj.toString());
			customerMessageService.sendMessage(jsonObj.toString());
			
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}