package weixin.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 栏目管理
 * @author zhangdaihao
 * @date 2014-06-10 20:07:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_cms_menu", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CmsMenuEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**栏目名称*/
	private java.lang.String name;
	/**栏目名称*/
	private java.lang.String type;
	/**图片名称*/
	private java.lang.String imageName;
	/**图片地址*/
	private java.lang.String imageHref;
	/**微信账户*/
	private java.lang.String accountid;
	/**创建人*/
	private java.lang.String createName;
	/**创建人id*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	
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
	 *@return: java.lang.String  栏目名称
	 */
	@Column(name ="NAME",nullable=true,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  栏目名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片名称
	 */
	@Column(name ="IMAGE_NAME",nullable=true,length=255)
	public java.lang.String getImageName(){
		return this.imageName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片名称
	 */
	public void setImageName(java.lang.String imageName){
		this.imageName = imageName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片地址
	 */
	@Column(name ="IMAGE_HREF",nullable=true,length=255)
	public java.lang.String getImageHref(){
		return this.imageHref;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片地址
	 */
	public void setImageHref(java.lang.String imageHref){
		this.imageHref = imageHref;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信账户
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=100)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信账户
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=255)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人id
	 */
	@Column(name ="CREATE_BY",nullable=true,length=255)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人id
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
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

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}
}
