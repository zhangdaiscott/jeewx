package com.jeecg.qywx.core.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.qywx.account.dao.QywxAccountDao;
import com.jeecg.qywx.account.dao.QywxMenuDao;
import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.account.entity.QywxMenu;
import com.jeecg.qywx.account.service.AccountService;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.api.user.JwUserAPI;
import com.jeecg.qywx.api.user.vo.User;
import com.jeecg.qywx.base.dao.QywxGzentityDao;
import com.jeecg.qywx.base.dao.QywxGzuserinfoDao;
import com.jeecg.qywx.base.dao.QywxLocationDao;
import com.jeecg.qywx.base.entity.QywxGzentity;
import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.base.entity.QywxLocation;
import com.jeecg.qywx.base.entity.QywxReceivetext;
import com.jeecg.qywx.core.service.AutoResponseDefaultServiceI;
import com.jeecg.qywx.core.service.KeyWordDealInterfaceService;
import com.jeecg.qywx.core.service.TextDealInterfaceService;
import com.jeecg.qywx.core.service.WeixinCoreService;
import com.jeecg.qywx.core.util.MessageUtil;
import com.jeecg.qywx.core.util.WeixinUtil;
import com.jeecg.qywx.sucai.dao.QywxNewsitemDao;
import com.jeecg.qywx.sucai.dao.QywxNewstemplateDao;
import com.jeecg.qywx.sucai.dao.QywxTexttemplateDao;
import com.jeecg.qywx.sucai.entity.QywxNewsitem;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;
import com.jeecg.qywx.sucai.entity.QywxTexttemplate;


@Service("weixinCoreService")
public class WeixinCoreServiceImpl implements WeixinCoreService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeixinCoreServiceImpl.class);
	
	@Autowired
	private TextDealInterfaceService textDealInterfaceService;//文本消息接口实现类
	@Autowired
	private KeyWordDealInterfaceService keyWordDealInterfaceServcie;//关键字接口实现类
//	@Autowired
//	private ImageDealInterfaceService imageDealInterfaceService;//图片消息处理接口实现类
//	@Autowired
//	private LocationDealInterfaceService locationDealInterfaceService;//地理位置消息实现接口
//	@Autowired
//	private GzDealInterfaceService gzDealIntefaceService;//关注事件处理接口
//	@Autowired
//	private QxDealInterfaceService qxDealInterfaceService;//取消事件处理接口
//	@Autowired
//	private MenuClickDealInterfaceService menuClickDealInterfaceService;//微信菜单点击事件接口
	@Autowired
	private AutoResponseDefaultServiceI autoResponseDefaultService;//默认回复接口
//	@Autowired
//	private SystemService systemService;
	@Autowired
	private QywxAccountDao qywxAccountDao;
	@Autowired
	private QywxMenuDao qywxMenuDao;
	@Autowired
	private QywxTexttemplateDao qywxTexttemplateDao;
	@Autowired
	private QywxNewstemplateDao qywxNewstemplateDao;
	@Autowired
	private QywxNewsitemDao qywxNewsitemDao;
	@Autowired
	private QywxGzuserinfoDao qywxGzuserinfoDao;
	@Autowired
	private QywxGzentityDao qywxGzentityDao;
	@Autowired
	private QywxLocationDao qywxLocationDao;
	@Autowired 
	private AccountService accountService;
    
	/** 接收普通消息
     * ToUserName	企业号CorpID
     * FromUserName	成员UserID
     * CreateTime	消息创建时间（整型）
     * MsgType	消息类型，此时固定为：text
     * Content	文本消息内容
     * MsgId	消息id，64位整型
     * AgentID	企业应用的id，整型。可在应用的设置页面查看
     */
	
	/*
	 * 接收事件的参数
	 * ToUserName   企业号CorpID
	 * FromUserName 成员UserID
	 * CreateTime   消息创建时间 （整型） 
	 * MsgType      消息类型，此时固定为：event 
	 * Event        事件类型 subscribe(订阅)、unsubscribe(取消订阅) 
	 * AgentID      企业应用的id，整型。可在应用的设置页面获取；如果id为0，则表示是整个企业号的关注/取消关注事件
	 */
	
	/*
	 *  ToUserName 	企业号CorpID
     *  FromUserName 	成员UserID
     *  CreateTime 	消息创建时间（整型）
     *  MsgType 	消息类型，此时固定为：event
     *  Event 	事件类型，此时固定为：LOCATION
     *  Latitude 	地理位置纬度
     *  Longitude 	地理位置经度
     *  Precision 	地理位置精度
     *  AgentID 	企业应用的id，整型。可在应用的设置页面查看
	 */
    public  String processRequest(String resultXml) {
    	String respMessage="";
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(resultXml);
            // 企业号CorpID
            String toUserName = requestMap.get("ToUserName");
            // 发送方帐号（UserID）
            String fromUserName = requestMap.get("FromUserName");
            //消息创建时间 注：微信消息接口中的CreateTime表示距离1970年的秒数而System.currentTimeMillis()表示距离1970年的毫秒数
            Long CreateTimeLong = Long.valueOf(requestMap.get("CreateTime"))*1000L;
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(CreateTimeLong));
            // 消息类型
            String msgType = requestMap.get("MsgType");
            //消息ID
        	String msgId = requestMap.get("MsgId");
        	//应用Id
        	String agentId = requestMap.get("AgentID");
        	//日志
        	logger.info("[WECHAT]","发送方账号:{},接收方账号:{},消息类型:{}", new Object[]{fromUserName,toUserName,msgType});
            //企业号ID
            String accountId = null;
            
            //添加关注人记录,并把账号信息保存到session中，后面保存用户信息使用
        	HttpSession session = ContextHolderUtils.getSession();
        	QywxAccount condition = new QywxAccount();
    		condition.setCorpid(toUserName);
    		List<QywxAccount> accountList = qywxAccountDao.getListByProperty(condition);
    		
        	if(accountList!=null && accountList.size()>0){
        		accountId = accountList.get(0).getId();
        	}else{
        		//TODO
        		return null;
        	}
    		
        	logger.info("----accountId-----"+accountId);
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	String content = requestMap.get("Content");
            	respMessage = this.dealTextMsg(toUserName, fromUserName, createTime, msgId, agentId, accountId, content);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
        		
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
            	logger.info("---------------------------用户发送地理位置消息----------------------------");
            	String latitude = requestMap.get("Latitude");//地理纬度
            	String longitude = requestMap.get("Longitude");//地理径度
               	String precision =  requestMap.get("Precision");//地理位置的精度
               	respMessage=dealLocation(fromUserName, accountId,toUserName,agentId,latitude,longitude,precision);//下面方法重写用到报文里面解析出来的数据
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
//            	//接收语音消息
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
            	//接收连接消息
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
//            	//接收视频消息
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                    // 事件类型
            	    String eventType = requestMap.get("Event");
                    logger.info("--------------------------eventType----------------------------：" + eventType);
                    // 订阅
                    if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    	respMessage = dealUserFocus(fromUserName, accountId,toUserName,agentId,eventType);//下面方法重写用到报文里面解析出来的数据
                    }
                    // 取消订阅
                    else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    	respMessage = dealUserFocus(fromUserName, accountId,toUserName,agentId,eventType);//下面方法重写用到报文里面解析出来的数据
                    }
                    //接收地理位置信息
                    else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
                    	 logger.info("--------------------------接收地理位置事件----------------------------：" + eventType);
    				 	String latitude = requestMap.get("Latitude");//地理纬度
                    	String longitude = requestMap.get("Longitude");//地理径度
                       	String precision =  requestMap.get("Precision");//地理位置的精度
                       	respMessage = dealLocation(fromUserName, accountId,toUserName,agentId,latitude,longitude,precision);//下面方法重写用到报文里面解析出来的数据
                     }
                    // 自定义菜单点击事件
                    else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    	
                    	String menuKey = requestMap.get("EventKey");
                    	System.out.println("....menuKey...."+menuKey);
                       //自定义菜单CLICK类型  \
                    	QywxMenu qywxMenu = qywxMenuDao.getMenuByMenuKey(menuKey);
                    	respMessage = dealMenuMessage(qywxMenu, toUserName, fromUserName, accountId, agentId);
                   }
                    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("....respMessage....."+respMessage);
        return respMessage;
    }
    /**
     * 处理text消息
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgId
     * @param agentId
     * @param accountId
     * @param content
     */
    private String dealTextMsg(String toUserName,String fromUserName,String createTime,String msgId,String agentId,String accountId,String content){
    	String respMessage = "";
    	//保存接收到的信息
    	QywxReceivetext receiveText = new QywxReceivetext();
    	receiveText.setContent(content);
    	Timestamp temp = Timestamp.valueOf(createTime);
    	String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    	receiveText.setId(randomSeed);
    	receiveText.setAccountid(accountId);
    	receiveText.setCreatetime(temp);
    	receiveText.setCreateDate(new Date());
    	receiveText.setFromusername(fromUserName);
    	receiveText.setTousername(toUserName);
    	receiveText.setMsgid(msgId);
    	receiveText.setMsgtype(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
    	receiveText.setResponse("0");
    	receiveText.setNickname("");
    	receiveText.setAgentId(agentId);
    	//保存消息
    	this.textDealInterfaceService.dealTextMessage(receiveText);
    	
    	//处理关键字
    	respMessage = this.keyWordDealInterfaceServcie.dealKeyMessage(content, toUserName, fromUserName,accountId,agentId);
    	
    	if(respMessage==""){
    		//判断是否转接多客服系统
    		respMessage = this.autoResponseDefaultService.getWorkDefaultResponse(fromUserName, toUserName, accountId,agentId);
    	}
    	return respMessage;
    }
    
    /**
     * 处理image消息
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgId
     * @param agentId
     * @param accountId
     * @param picUrl
     * @param mediaId
     */
    private String dealImageMsg(String toUserName,String fromUserName,String createTime,String msgId,String agentId,String accountId,String picUrl,String mediaId){
    	String respMessage = "";
    	System.out.println(".....图片信息....");
    	//图片信息接口实现类未进行任何操作，为后来提供扩展提供方便
//    	this.imageDealInterfaceService.dealImageMessage(null, toUserName);
//    	//判断是否转接多客服系统
//    	//AccountInfoEntity accountInfo = this.systemService.findUniqueByProperty(AccountInfoEntity.class, "wxbs", toUserName);
//    	respMessage = this.autoResponseDefaultService.getWorkDefaultResponse(fromUserName, toUserName, accountInfo.getId(),agentId);
    	return respMessage;
    }
    /**
     * 处理voice消息
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgId
     * @param agentId
     * @param accountId
     * @param format
     * @param mediaId
     */
    private String dealVoiceMsg(String toUserName,String fromUserName,String createTime,String msgId,String agentId,String accountId,String format,String mediaId){
    	String respMessage = "";
    	return respMessage;
    }
    
    private String dealVideoMsg(String toUserName,String fromUserName,String createTime,String msgId,String agentId,String accountId,String format,String mediaId){
    	String respMessage = "";
    	return respMessage;
    }
    
    /*
     * 处理菜单消息
     * @return
     */
    public String dealMenuMessage(QywxMenu qywxMenu, String toUserName,String fromUser,String accountid,String agentid) {
    	String responseMessage = "";
    	String lx = qywxMenu.getMsgType();
		String tempalteId = qywxMenu.getTemplateId();
		
		System.out.println(" dealMenuMessage [1] --lx-- "+lx + " -- tempalteId -- " + tempalteId);
		if("text".equals(lx)){
			QywxTexttemplate textTemplate = qywxTexttemplateDao.get(tempalteId);
			if(textTemplate!=null){
				System.out.println(" dealMenuMessage [2] --responseMessage--" + responseMessage);
				responseMessage = WeixinUtil.wrapperTextMessage(textTemplate, fromUser, toUserName);
			}
		}else if("news".equals(lx)){
			QywxNewstemplate newsTemplate = qywxNewstemplateDao.get(tempalteId);
			if(newsTemplate!=null){
				List<QywxNewsitem> newsList = qywxNewsitemDao.getQywxNewsitemByTemplateId(newsTemplate.getId());
				if(newsList!=null&&newsList.size()>0){
					responseMessage = WeixinUtil.wrapperNewsMessage(newsList, fromUser, toUserName,accountid,agentid);
					System.out.println("dealMenuMessage [3] --responseMessage--" + responseMessage);
				}
			}
		}
    	return responseMessage;
	}
    
    /*接收事件
     * 成员关注事件与成员取消事件
     */ 
	private String dealUserFocus(String fromUserName, String accountid,String toUserName, String agentId, String eventType) {
		//--update---author:scott-----date:20161217------------for:获取企业号TOKERN方式改成活的--
		//AccessToken accessToken = JwAccessTokenAPI.getAccessToken(JwParamesAPI.corpId, JwParamesAPI.secret);
		AccessToken accessToken = accountService.getAccessToken();
		//--update---author:scott-----date:20161217------------for:获取企业号TOKERN方式改成活的--
		
		String responseMessage = "";
		QywxGzuserinfo qywxGzuserinfo = qywxGzuserinfoDao.getByUserid(fromUserName);
		// 1.判断订阅类型是订阅，则插入用户信息。
		if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
			if (qywxGzuserinfo == null) {
			     qywxGzuserinfo=new QywxGzuserinfo();
				User userid = JwUserAPI.getUserByUserid(fromUserName,accessToken.getAccesstoken());// 根据微信端的id查询粉丝信息
				qywxGzuserinfo.setId(fromUserName);//成员id
				qywxGzuserinfo.setName(userid.getName());
				qywxGzuserinfo.setDepartment(userid.getDepartment().toString());
				qywxGzuserinfo.setPosition(userid.getPosition());
				qywxGzuserinfo.setMobile(userid.getMobile());
				qywxGzuserinfo.setGender(userid.getGender());
				qywxGzuserinfo.setEmail(userid.getEmail());
				qywxGzuserinfo.setWeixinid(userid.getWeixinid());
				qywxGzuserinfo.setAvatar(userid.getAvatar());
				qywxGzuserinfo.setAccountid(accountid);
				qywxGzuserinfo.setSubscribeTime(new Date());// 设置为当前时间
				qywxGzuserinfo.setAccountid(accountid);// 微信账号id
				qywxGzuserinfo.setSubscribeStatus("1");// 设置关注状态为已经关注
				qywxGzuserinfoDao.insert(qywxGzuserinfo);// 插入关注人记录
			} else {// 判断订阅类型是订阅,变更信息为关注
				qywxGzuserinfo.setSubscribeStatus("1");// 设置关注状态为已经关注
				qywxGzuserinfoDao.update(qywxGzuserinfo);// 更新用户状态
			}
			// 2.读取关注回复表，取出启动状态信息
			QywxGzentity qywxGzentity = qywxGzentityDao.getQywxGzentityIsWork();
			if (qywxGzentity != null) {
				String templateId = qywxGzentity.getTemplateId();// 模板id
				String msgType = qywxGzentity.getTemplateType();// 模板类型
				Date createDate=qywxGzentity.getCreateDate();// 创建时间
				// 3.根据回复语是否是text还是news类型分组装被响应报文，然后返回
				if ("text".equals(msgType)) {
					QywxTexttemplate textTemplate = qywxTexttemplateDao.get(templateId);
					if (textTemplate != null) {
						logger.info(" dealUserFocus [1] --responseMessage--"+ responseMessage);
						responseMessage = WeixinUtil.wrapperTextMessage(textTemplate, fromUserName, toUserName);
					}
				} else if ("news".equals(msgType)) {
					QywxNewstemplate newsTemplate = qywxNewstemplateDao.get(templateId);
					if (newsTemplate != null) {
						List<QywxNewsitem> newsList = qywxNewsitemDao.getQywxNewsitemByTemplateId(newsTemplate.getId());
						if (newsList != null && newsList.size() > 0) {
							responseMessage = WeixinUtil.wrapperNewsMessage(newsList, fromUserName, toUserName,accountid, agentId);
							logger.info(" dealUserFocus [2] --responseMessage--"+ responseMessage);
						}
					}
				}
			}
		} else if (MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)){// 判断订阅类型是取消订阅，则修改用户状态为未关注。
			qywxGzuserinfo.setSubscribeStatus("4");// 有了修改状态未关注
			qywxGzuserinfo.setUpdateDate(new Date());// 更新日期
			qywxGzuserinfoDao.update(qywxGzuserinfo);// 修改数据库的数据
		}
		return responseMessage;
	}
	
	/*
	 *上传地理位置事件
	 * 
	 */
	private String dealLocation(String fromUserName,String accountId, String toUserName,String agentId,String latitude,String longitude,String precision){
	    //1.根据agenid企业应用的id,fromUserName成员UserID,toUserName 	企业号CorpID，没有做插入记录
		String responseMessage="";
		QywxLocation qywxLocation = qywxLocationDao.getQywxLocation(toUserName, fromUserName, agentId);
		if(qywxLocation==null){
			qywxLocation = new QywxLocation();
			qywxLocation.setCorpid(toUserName);//企业号CorpID
			qywxLocation.setUserid(fromUserName);//成员UserID
		    qywxLocation.setCreatetime(new Date());//创建时间
			qywxLocation.setLatitude(latitude);//地理位置纬度 
			qywxLocation.setLongitude(longitude);//地理位置经度 
			qywxLocation.setLocationPrecision(precision);//地理位置精度 
			qywxLocation.setAgentid(agentId);//企业应用的id
			qywxLocation.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
			qywxLocationDao.insert(qywxLocation);//插入数据库
		}else{
			qywxLocation.setLatitude(latitude);//地理位置纬度 
			qywxLocation.setLongitude(longitude);//地理位置经度 
			qywxLocation.setLocationPrecision(precision);//地理位置精度 
			qywxLocationDao.update(qywxLocation);//更新数据库
		}
		return responseMessage;//响应报文
		
	}
	

}
