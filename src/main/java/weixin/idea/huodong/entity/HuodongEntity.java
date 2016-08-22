package weixin.idea.huodong.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 微信营销活动
 * @author zhangdaihao
 * @date 2014-06-06 11:05:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_huodong", schema = "")
@SuppressWarnings("serial")
public class HuodongEntity implements java.io.Serializable {
	/**键主*/
	private java.lang.String id;
	/**活动名称*/
	private java.lang.String title;
	/**活动描述*/
	private java.lang.String description;
	/**一等奖奖品*/
	private java.lang.String priceone;
	/**一等奖数量*/
	private java.lang.Integer onetotal;
	/**二等奖奖品*/
	private java.lang.String pricetwo;
	/**二等奖数量*/
	private java.lang.Integer twototal;
	/**三等奖奖品*/
	private java.lang.String pricethree;
	/**三等奖数量*/
	private java.lang.Integer threetotal;
	/**开始时间*/
	private java.util.Date starttime;
	/**结束时间*/
	private java.util.Date endtime;
	/**活动类型[大转盘][刮刮乐]*/
	private java.lang.String type;
	/**中奖概率*/
	private String gl;
	/**抽奖次数*/
	private String count;
	/**微信公众账号ID*/
	private String accountid;
	


	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  键主
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=100)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  键主
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="TITLE",nullable=true,length=400)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  活动描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=65535)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  活动描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  一等奖奖品
	 */
	@Column(name ="PRICEONE",nullable=true,length=400)
	public java.lang.String getPriceone(){
		return this.priceone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一等奖奖品
	 */
	public void setPriceone(java.lang.String priceone){
		this.priceone = priceone;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  一等奖数量
	 */
	@Column(name ="ONETOTAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getOnetotal(){
		return this.onetotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  一等奖数量
	 */
	public void setOnetotal(java.lang.Integer onetotal){
		this.onetotal = onetotal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二等奖奖品
	 */
	@Column(name ="PRICETWO",nullable=true,length=400)
	public java.lang.String getPricetwo(){
		return this.pricetwo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二等奖奖品
	 */
	public void setPricetwo(java.lang.String pricetwo){
		this.pricetwo = pricetwo;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  二等奖数量
	 */
	@Column(name ="TWOTOTAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTwototal(){
		return this.twototal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  二等奖数量
	 */
	public void setTwototal(java.lang.Integer twototal){
		this.twototal = twototal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  三等奖奖品
	 */
	@Column(name ="PRICETHREE",nullable=true,length=400)
	public java.lang.String getPricethree(){
		return this.pricethree;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  三等奖奖品
	 */
	public void setPricethree(java.lang.String pricethree){
		this.pricethree = pricethree;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  三等奖数量
	 */
	@Column(name ="THREETOTAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getThreetotal(){
		return this.threetotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  三等奖数量
	 */
	public void setThreetotal(java.lang.Integer threetotal){
		this.threetotal = threetotal;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="STARTTIME",nullable=true)
	public java.util.Date getStarttime(){
		return this.starttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStarttime(java.util.Date starttime){
		this.starttime = starttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="ENDTIME",nullable=true)
	public java.util.Date getEndtime(){
		return this.endtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndtime(java.util.Date endtime){
		this.endtime = endtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动类型
	 */
	@Column(name ="TYPE",nullable=true,length=100)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	@Column(name ="gl",nullable=true,length=100)
	public String getGl() {
		return gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	@Column(name ="count",nullable=true,length=11)
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
