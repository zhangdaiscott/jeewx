package weixin.guanjia.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 微信公众帐号信息
 * @author onlineGenerator
 * @date 2014-05-21 00:53:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_account", schema = "")
@SuppressWarnings("serial")
public class WeixinAccountEntity implements java.io.Serializable {
	
	/**主键*/
	private java.lang.String id;
	/**公众帐号名称*/
	private java.lang.String accountname;
	/**公众帐号TOKEN*/
	private java.lang.String accounttoken;
	/**公众微信号*/
	private java.lang.String accountnumber;
	/**公众原始ID*/
	private java.lang.String weixin_accountid;
	/**公众号类型*/
	private java.lang.String accounttype;
	/**电子邮箱*/
	private java.lang.String accountemail;
	/**公众帐号描述*/
	private java.lang.String accountdesc;
	/**公众帐号APPID*/
	private java.lang.String accountappid;
	/**公众帐号APPSECRET*/
	private java.lang.String accountappsecret;
	/**ACCESS_TOKEN*/
	private java.lang.String accountaccesstoken;
	/**TOKEN获取时间*/
	private java.util.Date addtoekntime;
	/**所属系统用户**/
	private java.lang.String userName;
	
	/**微信卡券JS API的临时票据*/
	private java.lang.String apiticket;
	/**微信卡券JS API的临时票据的获取时间*/
	private java.util.Date apiticketttime;
	/**jsapi调用接口临时凭证*/
	private java.lang.String jsapiticket;
	/**jsapi调用接口临时凭证的获取时间*/
	private java.util.Date jsapitickettime;
	
	public java.lang.String getApiticket() {
		return apiticket;
	}

	public void setApiticket(java.lang.String apiticket) {
		this.apiticket = apiticket;
	}

	public java.util.Date getApiticketttime() {
		return apiticketttime;
	}

	public void setApiticketttime(java.util.Date apiticketttime) {
		this.apiticketttime = apiticketttime;
	}
	@Column(name ="jsapiticket",nullable=true,length=1000)
	public java.lang.String getJsapiticket() {
		return jsapiticket;
	}

	public void setJsapiticket(java.lang.String jsapiticket) {
		this.jsapiticket = jsapiticket;
	}
	@Column(name ="jsapitickettime",nullable=true)
	public java.util.Date getJsapitickettime() {
		return jsapitickettime;
	}

	public void setJsapitickettime(java.util.Date jsapitickettime) {
		this.jsapitickettime = jsapitickettime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号名称
	 */
	@Column(name ="ACCOUNTNAME",nullable=true,length=200)
	public java.lang.String getAccountname(){
		return this.accountname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号名称
	 */
	public void setAccountname(java.lang.String accountname){
		this.accountname = accountname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号TOKEN
	 */
	@Column(name ="ACCOUNTTOKEN",nullable=true,length=200)
	public java.lang.String getAccounttoken(){
		return this.accounttoken;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号TOKEN
	 */
	public void setAccounttoken(java.lang.String accounttoken){
		this.accounttoken = accounttoken;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众微信号
	 */
	@Column(name ="ACCOUNTNUMBER",nullable=true,length=200)
	public java.lang.String getAccountnumber(){
		return this.accountnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众微信号
	 */
	public void setAccountnumber(java.lang.String accountnumber){
		this.accountnumber = accountnumber;
	}
	
	public java.lang.String getWeixin_accountid() {
		return weixin_accountid;
	}

	public void setWeixin_accountid(java.lang.String weixin_accountid) {
		this.weixin_accountid = weixin_accountid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众号类型
	 */
	@Column(name ="ACCOUNTTYPE",nullable=true,length=50)
	public java.lang.String getAccounttype(){
		return this.accounttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众号类型
	 */
	public void setAccounttype(java.lang.String accounttype){
		this.accounttype = accounttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电子邮箱
	 */
	@Column(name ="ACCOUNTEMAIL",nullable=true,length=200)
	public java.lang.String getAccountemail(){
		return this.accountemail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电子邮箱
	 */
	public void setAccountemail(java.lang.String accountemail){
		this.accountemail = accountemail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号描述
	 */
	@Column(name ="ACCOUNTDESC",nullable=true,length=500)
	public java.lang.String getAccountdesc(){
		return this.accountdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号描述
	 */
	public void setAccountdesc(java.lang.String accountdesc){
		this.accountdesc = accountdesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号APPID
	 */
	@Column(name ="ACCOUNTAPPID",nullable=true,length=200)
	public java.lang.String getAccountappid(){
		return this.accountappid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号APPID
	 */
	public void setAccountappid(java.lang.String accountappid){
		this.accountappid = accountappid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号APPSECRET
	 */
	@Column(name ="ACCOUNTAPPSECRET",nullable=true,length=500)
	public java.lang.String getAccountappsecret(){
		return this.accountappsecret;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号APPSECRET
	 */
	public void setAccountappsecret(java.lang.String accountappsecret){
		this.accountappsecret = accountappsecret;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ACCESS_TOKEN
	 */
	@Column(name ="ACCOUNTACCESSTOKEN",nullable=true,length=1000)
	public java.lang.String getAccountaccesstoken(){
		return this.accountaccesstoken;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ACCESS_TOKEN
	 */
	public void setAccountaccesstoken(java.lang.String accountaccesstoken){
		this.accountaccesstoken = accountaccesstoken;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  TOKEN获取时间
	 */
	@Column(name ="ADDTOEKNTIME",nullable=true,length=100)
	public java.util.Date getAddtoekntime(){
		return this.addtoekntime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  TOKEN获取时间
	 */
	public void setAddtoekntime(java.util.Date addtoekntime){
		this.addtoekntime = addtoekntime;
	}
	
	@Column(name ="USERNAME",nullable=true,length=50)
	public java.lang.String getUserName() {
		return userName;
	}
	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
}
