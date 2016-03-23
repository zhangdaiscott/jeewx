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
 * @Description: 微站点信息
 * @author onlineGenerator
 * @date 2014-07-15 21:04:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_cms_site", schema = "")
@SuppressWarnings("serial")
public class WeixinCmsSiteEntity implements java.io.Serializable {
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
	/**站点名称*/
	@Excel(exportName="站点名称")
	private java.lang.String siteName;
	/**公司电话*/
	@Excel(exportName="公司电话")
	private java.lang.String companyTel;
	/**站点logo*/
	@Excel(exportName="站点logo")
	private java.lang.String siteLogo;
	/**站点模板样式*/
	private java.lang.String siteTemplateStyle;
	/**公众账号原始ID*/
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
	 *@return: java.lang.String  站点名称
	 */
	@Column(name ="SITE_NAME",nullable=true,length=100)
	public java.lang.String getSiteName(){
		return this.siteName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  站点名称
	 */
	public void setSiteName(java.lang.String siteName){
		this.siteName = siteName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司电话
	 */
	@Column(name ="COMPANY_TEL",nullable=true,length=50)
	public java.lang.String getCompanyTel(){
		return this.companyTel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司电话
	 */
	public void setCompanyTel(java.lang.String companyTel){
		this.companyTel = companyTel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  站点logo
	 */
	@Column(name ="SITE_LOGO",nullable=true,length=200)
	public java.lang.String getSiteLogo(){
		return this.siteLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  站点logo
	 */
	public void setSiteLogo(java.lang.String siteLogo){
		this.siteLogo = siteLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  站点模板样式
	 */
	@Column(name ="SITE_TEMPLATE_STYLE",nullable=true,length=50)
	public java.lang.String getSiteTemplateStyle(){
		return this.siteTemplateStyle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  站点模板样式
	 */
	public void setSiteTemplateStyle(java.lang.String siteTemplateStyle){
		this.siteTemplateStyle = siteTemplateStyle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众账号原始ID
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众账号原始ID
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
}
