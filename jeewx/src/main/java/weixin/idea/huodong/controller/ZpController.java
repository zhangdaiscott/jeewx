package weixin.idea.huodong.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.idea.huodong.entity.HdRecordEntity;
import weixin.idea.huodong.entity.HuodongEntity;
import weixin.idea.huodong.entity.PrizeRecordEntity;
import weixin.idea.huodong.utils.HdUtils;


/**
 * 微信大转盘
* 
 */
@Controller
@RequestMapping("/zpController")
public class ZpController {
	
	private SystemService systemService;
	private String message;
	
	@Resource(name="systemService")
    public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
    /**
     * 刮刮乐
     * @param request
     * @return
     */
    @RequestMapping(params="goGglNew")
	public ModelAndView goGglNew(HttpServletRequest request){
//    	String id = request.getParameter("hdId");
    	String opendId = request.getParameter("openId");
    	String accountid = findAccountId(request);
//    	org.jeecgframework.core.util.LogUtil.info("...opendId...."+opendId+"....hdId..."+id);
    	List<HuodongEntity>  hdlst= this.systemService.findByQueryString(" FROM HuodongEntity h WHERE h.accountid='"+accountid+"' AND type=1");
		HuodongEntity huodongEntity = new HuodongEntity();
		if(hdlst.size()!=0){
			huodongEntity = hdlst.get(0);
		}
    	if(huodongEntity!=null){
    		String gl = huodongEntity.getGl();
    		if(oConvertUtils.isNotEmpty(gl)){
    			String glArr[] = gl.split("/");
        		int randomNum = HdUtils.createPrice(Integer.parseInt(glArr[0]),Integer.parseInt(glArr[1]));
        		//randomNum = 1;
        		request.setAttribute("prize", randomNum);
        		HttpSession session  = request.getSession();
        		session.setAttribute("hdId", huodongEntity.getId());
        		session.setAttribute("accountid", accountid);
        		session.setAttribute("opendId", opendId);
        		session.setAttribute("prize", randomNum);
    		}
    		request.setAttribute("huodongEntity", huodongEntity);
    	}
    	
    	return new ModelAndView("weixin/idea/huodong/ggl/ggl");
	}
    
    /**
     * 刮刮乐
     * @param request
     * @return
     */
    @RequestMapping(params="doGgl")
    @ResponseBody
    public AjaxJson doGgl(HttpServletRequest request){
    	Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
    	String message = "";
    	AjaxJson j = new AjaxJson();
    	HttpSession session = request.getSession();
		String hdId = session.getAttribute("hdId").toString();
		String openId = session.getAttribute("opendId").toString();
		String accountid = findAccountId(request);
    	String hql = "from HdRecordEntity where hdid='"+hdId+"' and opendid='"+openId+"'";
    	if(StringUtil.isNotEmpty(accountid)){
			hql+=" and accountid='"+accountid+"'";
		}else{
			hql+=" and accountid='-'";
		}
		List<HdRecordEntity> hdRecrdList = this.systemService.findHql(hql, null);
		if(hdRecrdList.size()>0){
			HdRecordEntity hdRecord = hdRecrdList.get(0);
			int total = hdRecord.getTotal();
			org.jeecgframework.core.util.LogUtil.info("....total...."+total);
			HuodongEntity huodongEntitiy = this.systemService.getEntity(HuodongEntity.class, hdId);
			if(total<Integer.parseInt(huodongEntitiy.getCount())){
				String hql1 = "from PrizeRecordEntity where hdid='"+hdId+"' and openId='"+openId+"'";
				if(StringUtil.isNotEmpty(accountid)){
					hql1+=" and accountid='"+accountid+"'";
				}else{
					hql1+=" and accountid='-'";
				}
				List<PrizeRecordEntity> prizeList = this.systemService.findByQueryString(hql1);
				if(prizeList.size()>0){
					j.setSuccess(false);
					message = "对不起本次活动你已经中奖，不能在抽奖！";
				}else{
					j.setSuccess(true);
					hdRecord.setTotal(total+1);
					this.systemService.updateEntitie(hdRecord);
				}
			}else{
				j.setSuccess(false);
				message = "对不起您已经抽奖"+total+"次，不能在抽奖！";
			}
		}else{
			HdRecordEntity hdRecord = new HdRecordEntity();
			hdRecord.setAddtime(nowTime);
			hdRecord.setHdid(hdId);
			hdRecord.setOpendid(openId);
			hdRecord.setTotal(1);
			hdRecord.setNickname("");
			hdRecord.setAccountid(accountid);
			this.systemService.save(hdRecord);
		}
		org.jeecgframework.core.util.LogUtil.info(message);
		j.setMsg(message);
	    return j;
    }
    
    /**
	 * 保存刮刮乐抽奖记录和中奖记录
	 * @return
	 */
	@RequestMapping(params = "saveRecord")
	@ResponseBody
	public AjaxJson saveRecord(HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		HttpSession session = request.getSession();
		String mobile = request.getParameter("mobile");
		Object hdIdObj = session.getAttribute("hdId");
		Object opendIdObj = session.getAttribute("opendId");
		Object prizeObj =  session.getAttribute("prize");
		String accountid = findAccountId(request);
		String prize = "";
		String hdId = "";
		String opendId = "";
		if(prizeObj!=null){
			prize = prizeObj.toString();
		}
		
		if(hdIdObj!=null){
			hdId = hdIdObj.toString();
		}
		
		if(opendIdObj!=null){
			opendId = opendIdObj.toString();
		}
		Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
		org.jeecgframework.core.util.LogUtil.info("....prize...."+prize);
		if("1".equals(prize)||"2".equals(prize)||"3".equals(prize)){
			PrizeRecordEntity prizeEntity = new PrizeRecordEntity();
			prizeEntity.setHdid(hdId);
			prizeEntity.setPrize(prize);
			prizeEntity.setMobile(mobile);
			prizeEntity.setAddtime(nowTime);
			prizeEntity.setOpenId(opendId);
			prizeEntity.setAccountid(accountid);
			this.systemService.save(prizeEntity);
		}
		
		return j;
	}
	
	
	/**
	 * 大转盘
	 * @param request
	 * @return
	 */
	@RequestMapping(params="goZhuanpan")
	public ModelAndView goZhuanpan(HttpServletRequest request){
		String openId = request.getParameter("openId");
		String accountid = findAccountId(request);
		
		//获取当前公众帐号的大转盘规则
		List<HuodongEntity>  hdlst= this.systemService.findByQueryString(" FROM HuodongEntity h WHERE h.accountid='"+accountid+"' AND type=2");
		HuodongEntity hdEntity = new HuodongEntity();
		if(hdlst.size()!=0){
			hdEntity = hdlst.get(0);
			//显示前台的活动规则
			request.setAttribute("hdEntity", hdEntity);
			request.setAttribute("hdId", hdEntity.getId());
			request.setAttribute("openId", openId);
			request.setAttribute("accountid", accountid);
		}
		org.jeecgframework.core.util.LogUtil.info("....hdid...."+hdEntity.getId()+"...openId.."+openId);
		return new ModelAndView("weixin/idea/huodong/zp/zhuanpan");
	}
	
    /**
	 * 大转盘抽奖记录
	 * @return
	 */
	@RequestMapping(params = "getZpPize")
	@ResponseBody
	public AjaxJson doZpPize(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String hdId = request.getParameter("hdId");
		String openId = request.getParameter("openId");
		String accountid = findAccountId(request);
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(hdId)&&StringUtil.isNotEmpty(openId)){
			HuodongEntity hdEntity = this.systemService.getEntity(HuodongEntity.class, hdId);
			
			String hql = "from HdRecordEntity where hdid='"+hdId+"' and opendid='"+openId+"'";
			if(StringUtil.isNotEmpty(accountid)){
				hql+=" and accountid='"+accountid+"'";
			}else{
				hql+=" and accountid='-'";
			}
			List<HdRecordEntity> hdRecrdList = this.systemService.findHql(hql, null);
			Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
			if(hdEntity!=null){
				String gl = hdEntity.getGl();
	    		String glArr[] = gl.split("/");
	    		int randomNum = HdUtils.createPrice(Integer.parseInt(glArr[0]),Integer.parseInt(glArr[1]));
	    		if(randomNum==1||randomNum==2||randomNum==3){
	    			params.put("prizetype", randomNum);
	    			HttpSession session  = request.getSession();
	    			//保存到session中奖后可以保存
	    			session.setAttribute("hdId", hdId);
	        		session.setAttribute("openId", openId);
	        		session.setAttribute("prize", randomNum);
	        		session.setAttribute("accountid", accountid);
	    		}
				if(hdRecrdList.size()>0){
					HdRecordEntity hdRecord = hdRecrdList.get(0);
					int total = hdRecord.getTotal();
					if(total<Integer.parseInt(hdEntity.getCount())){
						String hql1 = "from PrizeRecordEntity where hdid='"+hdId+"' and openId='"+openId+"'";
						if(StringUtil.isNotEmpty(accountid)){
							hql1+=" and accountid='"+accountid+"'";
						}else{
							hql1+=" and accountid='-'";
						}
						List<PrizeRecordEntity> prizeList = this.systemService.findByQueryString(hql1);
						if(prizeList.size()>0){
							j.setSuccess(false);
							params.put("error", "getsn");
							params.put("prizetype", prizeList.get(0).getPrize());
						}else{
							hdRecord.setTotal(total+1);
							this.systemService.updateEntitie(hdRecord);
						}
						
					}else{
						j.setSuccess(false);
						params.put("error", "invalid");
						params.put("total", total);
					}
				}else{
					
					HdRecordEntity hdRecord = new HdRecordEntity();
					hdRecord.setAddtime(nowTime);
					hdRecord.setHdid(hdId);
					hdRecord.setOpendid(openId);
					hdRecord.setTotal(1);
					hdRecord.setNickname("");
					hdRecord.setAccountid(accountid);
					this.systemService.save(hdRecord);
				}
			}
		}
		j.setAttributes(params);
		return j;
	}
	
	/**
	 * 保存大转盘的中奖记录
	 * @return
	 */
	@RequestMapping(params = "saveZpPrize")
	@ResponseBody
	public AjaxJson saveZpPrize(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String mobile = request.getParameter("mobile");
		HttpSession session = request.getSession();
		Object hdIdObj = session.getAttribute("hdId");
		Object openIdObj = session.getAttribute("openId");
		Object prizeObj = session.getAttribute("prize");
		String hdId = "",openId="",prize="",accountid="";
		accountid = findAccountId(request);
		if(hdIdObj!=null){
			hdId = hdIdObj.toString();
		}
		if(openIdObj!=null){
			openId = openIdObj.toString();
		}
		if(prizeObj!=null){
			prize = prizeObj.toString();
		}
		Timestamp nowTime = new Timestamp(new java.util.Date().getTime());
		PrizeRecordEntity prizeEntity = new PrizeRecordEntity();
		prizeEntity.setHdid(hdId);
		prizeEntity.setPrize(prize);
		prizeEntity.setMobile(mobile);
		prizeEntity.setAddtime(nowTime);
		prizeEntity.setOpenId(openId);
		prizeEntity.setAccountid(accountid);
		this.systemService.save(prizeEntity);
		return j;
	}
	
	@SuppressWarnings("unused")
	private String findAccountId(HttpServletRequest request){
		//非法请求，直接返回
		if(request == null){
			return "";
		}
		//request请求中拿到accountid,直接返返回，如果拿不到。则从上下文中获取
		String accountid = request.getParameter("accountid");
		if(accountid != null && !"".equals(accountid)){
			return accountid;
		}else{
			return ResourceUtil.getWeiXinAccountId();
		}
	}
    
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}