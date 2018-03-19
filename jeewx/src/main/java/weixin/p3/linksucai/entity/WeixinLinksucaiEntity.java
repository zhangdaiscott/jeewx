package weixin.p3.linksucai.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;

import javax.xml.soap.Text;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 链接素材
 * @author onlineGenerator
 * @date 2015-01-22 21:39:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_linksucai", schema = "")
@SuppressWarnings("serial")
public class WeixinLinksucaiEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**修改日期*/
	private java.util.Date updateDate;
	/**链接名称*/
	@Excel(exportName="链接名称")
	private java.lang.String name;
	/**外部链接*/
	@Excel(exportName="外部链接")
	private java.lang.String outerLink;
	/**功能描述*/
	@Excel(exportName="功能描述")
	private java.lang.String content;
	/**内部链接*/
	@Excel(exportName="内部链接")
	private java.lang.String innerLink;
	/**转换标志*/
	@Excel(exportName="转换标志")
	private java.lang.Integer transferSign;
	/**微信账户id*/
	@Excel(exportName="微信账户id")
	private java.lang.String accountid;
	/**账号邮编*/
	@Excel(exportName="账号邮编")
	private java.lang.String postcode;
	/**分享状态*/
	@Excel(exportName="分享状态")
	private java.lang.String shareStatus;
	/**账号邮编名称对应下发公众账号名称*/

	private String postcodeName;

	
	//add-begin-alax 20151109 for TASK #621 链接素材增加“是否加密”字段
	/**是否加密*/
	@Excel(exportName="是否加密")
	private java.lang.Integer isEncrypt;
	
	public void setIsEncrypt(java.lang.Integer isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	@Column(name ="IS_ENCRYPT",nullable=false,length=2)
	public java.lang.Integer getIsEncrypt() {
		return isEncrypt;
	}
	//add-end-alax 20151109 for TASK #621 链接素材增加“是否加密”字段
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  链接名称
	 */
	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  链接名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外部链接
	 */
	@Column(name ="OUTER_LINK",nullable=true,length=500)
	public java.lang.String getOuterLink(){
		return this.outerLink;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外部链接
	 */
	public void setOuterLink(java.lang.String outerLink){
		this.outerLink = outerLink;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  功能描述
	 */
	@Column(name ="CONTENT",nullable=true,length=500)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  功能描述
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内部链接
	 */
	@Column(name ="INNER_LINK",nullable=true,length=500)
	public java.lang.String getInnerLink(){
		return this.innerLink;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内部链接
	 */
	public void setInnerLink(java.lang.String innerLink){
		this.innerLink = innerLink;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  转换标志
	 */
	@Column(name ="TRANSFER_SIGN",nullable=true,length=32)
	public java.lang.Integer getTransferSign(){
		return this.transferSign;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  转换标志
	 */
	public void setTransferSign(java.lang.Integer transferSign){
		this.transferSign = transferSign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信账户id
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=100)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信账户id
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号邮编
	 */
	@Column(name ="POST_CODE",nullable=true,length=200)
	public java.lang.String getPostcode(){
		return this.postcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号邮编
	 */
	public void setPostcode(java.lang.String postcode){
		this.postcode = postcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分享状态
	 */
	@Column(name ="SHARE_STATUS",nullable=true,length=10)
	public java.lang.String getShareStatus(){
		return this.shareStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分享状态
	 */
	public void setShareStatus(java.lang.String shareStatus){
		this.shareStatus = shareStatus;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号邮编名称对应下发公众账号名称
	 */
	@Transient
	public String getPostcodeName() {
		return postcodeName;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号邮编名称对应下发公众账号名称
	 */
	public void setPostcodeName(String postcodeName) {
		this.postcodeName = postcodeName;
	}
	
}
