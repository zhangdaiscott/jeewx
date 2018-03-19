package weixin.p3.linksucai.service.impl;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Criteria;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.p3.linksucai.entity.WeixinLinksucaiEntity;
import weixin.p3.linksucai.service.WeixinLinksucaiServiceI;
import weixin.p3.oauth2.def.WeiXinOpenConstants;
import weixin.p3.oauth2.util.CmsFreemarkerHelperNew;

@Service("weixinLinksucaiService")
@Transactional
public class WeixinLinksucaiServiceImpl extends CommonServiceImpl implements WeixinLinksucaiServiceI {

	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinLinksucaiEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinLinksucaiEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinLinksucaiEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinLinksucaiEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinLinksucaiEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinLinksucaiEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinLinksucaiEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{outer_link}",String.valueOf(t.getOuterLink()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{inner_link}",String.valueOf(t.getInnerLink()));
 		sql  = sql.replace("#{transfer_sign}",String.valueOf(t.getTransferSign()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{post_code}",String.valueOf(t.getPostcode()));
 		sql  = sql.replace("#{share_status}",String.valueOf(t.getShareStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
 	 * 按微信账号邮编及共享方式查询
 	 * @param <T>
 	 * @return
 	 */
 	public <T> List<T>  getLinksucaiList()
 	{
 		CriteriaQuery cq = new CriteriaQuery(WeixinLinksucaiEntity.class);
		cq.eq("accountid", ResourceUtil.getShangJiaAccountId());
		cq.eq("shareStatus", WeiXinOpenConstants.SUCAI_SHARE_STATUS);
		cq.add(cq.or(cq.and(cq, 1, 2),cq,0));
		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
				getSession());
		List<T> list = criteria.list();
		return list;
 	}
 	/**
 	 * 按微信账号邮编及共享方式查询
 	 * @param <T>
 	 * @return
 	 */
 	public <T> List<T>  getLinksucaiListForSelect(String likeName)
 	{
 		CriteriaQuery cq = new CriteriaQuery(WeixinLinksucaiEntity.class);
		cq.eq("accountid", ResourceUtil.getShangJiaAccountId());
		cq.eq("shareStatus", WeiXinOpenConstants.SUCAI_SHARE_STATUS);
		if(oConvertUtils.isNotEmpty(likeName)){
			cq.like("name", "%"+likeName+"%");
			cq.add(cq.and(cq.and(cq.or(cq, 1, 2),cq,0),cq,3));
		}else{
			cq.add(cq.or(cq.and(cq, 1, 2),cq,0));
		}
		cq.addOrder("createDate", SortDirection.desc);
		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
				getSession());
		List<T> list = criteria.list();
		return list;
 	}
// 	/**
// 	 * 获取组装的最新外部链接，追加系统参数
// 	 * @return
// 	 */
// 	public String installOuterLinkWithSysParams(WeixinLinksucaiEntity t,String openid, String accountid)
// 	{
// 		CmsFreemarkerHelper  cmsFreemarkerHelper = new CmsFreemarkerHelper();
// 		String url = t.getOuterLink();
// 		
// 		//捷微微信管家系统，可以获取到的微信相关参数
// 		Map<String,Object> mpLinkPrams = new HashMap<String,Object>();
// 		mpLinkPrams.put("openid", oConvertUtils.getString(openid));
// 		mpLinkPrams.put("accountid", accountid);
// 		if(oConvertUtils.isNotEmpty(accountid)){
// 			WeixinAccountEntity weixinAccount  = this.get(WeixinAccountEntity.class, accountid);
// 	 		if(weixinAccount!= null){
// 	 			//对应微信公众账号原始ID
// 	 			mpLinkPrams.put("wxid", weixinAccount.getWeixinOriginalAccountid());
// 	 			mpLinkPrams.put("wxname", weixinAccount.getAccountname());
// 	 			mpLinkPrams.put("wxcode", weixinAccount.getAccountnumber());
// 	 			mpLinkPrams.put("appid", weixinAccount.getAccountappid());
// 	 			mpLinkPrams.put("appsecret", weixinAccount.getAccountappsecret());
// 	 			mpLinkPrams.put("accesstoken", weixinAccount.getAccounttoken());
// 	 			
//				try {
//					Wxuser wxuser =  JwUserAPI.getWxuser(weixinAccount.getAccountaccesstoken(), oConvertUtils.getString(openid));
//					mpLinkPrams.put("subscribe", wxuser.getSubscribe());
//					mpLinkPrams.put("nickname", wxuser.getNickname());
//				} catch (WexinReqException e) {
//					e.printStackTrace();
//				}
// 	 		}
// 		}
// 		//用户手机号
// 		List<WeixinVipMemberEntity> ls = this.findByProperty(WeixinVipMemberEntity.class, "openid", oConvertUtils.getString(openid));
// 		if(!oConvertUtils.isNullOrEmpty(ls)){
// 			WeixinVipMemberEntity e = ls.get(0);
// 			mpLinkPrams.put("telphone", e.getMemberMobile());
// 		}else{
// 			mpLinkPrams.put("telphone", "");
// 		}
// 		return cmsFreemarkerHelper.parseTemplateContent(url, mpLinkPrams);
// 		
//// 		if(url.contains("?"))
//// 		{
//// 			url=url+"&openid="+openid;
//// 		}	
//// 		if(accountid!=null && !accountid.equals(""))
//// 		{
//// 			url=url+"&accountid="+accountid;
//// 		}
////		return url;
// 	}
// 	
 	
 	/**
 	 * 获取组装的最新外部链接，追加系统参数
 	 * @param url 外部URL地址
 	 * @wxcontent 微信用户发送消息  
 	 * @return
 	 */
 	public String installOuterLinkWithSysParams(String url,String openid, String accountid,String wxcontent){
 		String outlink = null;
 		CmsFreemarkerHelperNew  cmsFreemarkerHelper = new CmsFreemarkerHelperNew();
 		//捷微微信管家系统，可以获取到的微信相关参数
 		Map<String,Object> mpLinkPrams = new HashMap<String,Object>();
 		mpLinkPrams.put("openid", oConvertUtils.getString(openid));
 		mpLinkPrams.put("accountid", accountid);
 		System.out.println("----------------------------setp1-------------------");
 		try {
	 		//传递客户发送消息，给外围接口，供业务使用
	 		mpLinkPrams.put("wxcontent", wxcontent);
	 		if(oConvertUtils.isNotEmpty(accountid)){
	 			WeixinAccountEntity weixinAccount  = this.get(WeixinAccountEntity.class, accountid);
	 	 		if(weixinAccount!= null){
	 	 			//对应微信公众账号原始ID
	 	 			mpLinkPrams.put("wxid", weixinAccount.getWeixin_accountid());
	 	 			mpLinkPrams.put("wxname", weixinAccount.getAccountname());
	 	 			mpLinkPrams.put("wxcode", weixinAccount.getAccountnumber());
	 	 			mpLinkPrams.put("appid", weixinAccount.getAccountappid());
	 	 			mpLinkPrams.put("appsecret", weixinAccount.getAccountappsecret());
	 	 			mpLinkPrams.put("accesstoken", weixinAccount.getAccounttoken());
	 	 			System.out.println("----------------------------setp2-------------------");
					try {
						System.out.println("----------------------------setp3-------------------");
						Wxuser wxuser =  JwUserAPI.getWxuser(weixinAccount.getAccountaccesstoken(), oConvertUtils.getString(openid));
						System.out.println("----------------------------setp4-------------------");
						if(wxuser!=null){
							mpLinkPrams.put("subscribe", wxuser.getSubscribe());
							//解码传递，中文会有问题
							mpLinkPrams.put("nickname", URLEncoder.encode(wxuser.getNickname(),"UTF-8"));
						}
					} catch (Exception e) {
						//e.printStackTrace();
						mpLinkPrams.put("subscribe", "0");
					}
					System.out.println("----------------------------setp3-------------------");
	 	 		}
	 		}
	 		outlink = cmsFreemarkerHelper.parseTemplateContent(url, mpLinkPrams);
 		} catch (Exception e) {
 			e.printStackTrace();
		}
 		return outlink;
 	}
 	
 	
 	
	/**
	 * 获取链接素材对应的访问链接
	 * @param field 
	 * @return
	 */
	public String getInnerLink(String linkSucaiId) {
		String baseurl = ResourceUtil.getConfigByName("domain");
		String inner_link = baseurl+"/weixinLinksucaiController.do?link&id="+ linkSucaiId + "&accountid="+ResourceUtil.getShangJiaAccountId();
		return inner_link;
	}
}