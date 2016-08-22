package weixin.cms.entity;

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
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 微站点模板
 * @author onlineGenerator
 * @date 2014-07-15 22:20:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_cms_style", schema = "")
@SuppressWarnings("serial")
public class WeixinCmsStyleEntity implements java.io.Serializable {
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
	/**模板名称*/
	@Excel(exportName="模板名称")
	private java.lang.String templateName;
	/**模板路径*/
	@Excel(exportName="模板路径")
	private java.lang.String templateUrl;
	/**预览图*/
	@Excel(exportName="预览图")
	private java.lang.String reviewImgUrl;
	/**公众帐号原始ID*/
	private java.lang.String accountid;
	
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
	 *@return: java.lang.String  模板名称
	 */
	@Column(name ="TEMPLATE_NAME",nullable=true,length=100)
	public java.lang.String getTemplateName(){
		return this.templateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模板名称
	 */
	public void setTemplateName(java.lang.String templateName){
		this.templateName = templateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模板路径
	 */
	@Column(name ="TEMPLATE_URL",nullable=true,length=200)
	public java.lang.String getTemplateUrl(){
		return this.templateUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模板路径
	 */
	public void setTemplateUrl(java.lang.String templateUrl){
		this.templateUrl = templateUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预览图
	 */
	@Column(name ="REVIEW_IMG_URL",nullable=true,length=100)
	public java.lang.String getReviewImgUrl(){
		return this.reviewImgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预览图
	 */
	public void setReviewImgUrl(java.lang.String reviewImgUrl){
		this.reviewImgUrl = reviewImgUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众帐号原始ID
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=50)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众帐号原始ID
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
}
