package weixin.guanjia.long2short.entity;

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
 * @Description: 长连接转短链接
 * @author onlineGenerator
 * @date 2018-03-12 16:01:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_long2short", schema = "")
@SuppressWarnings("serial")
public class WeixinLong2shortEntity implements java.io.Serializable {
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
	/**名称*/
	@Excel(exportName="名称")
	private java.lang.String wxName;
	/**长连接*/
	@Excel(exportName="长连接")
	private java.lang.String longUrl;
	/**短链接*/
	@Excel(exportName="短连接")
	private java.lang.String shortUrl;
	/**公众号ID*/
	private java.lang.String accountId;
	
	
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
	 *@return: java.lang.String  长连接
	 */
	@Column(name ="LONG_URL",nullable=true,length=2000)
	public java.lang.String getLongUrl(){
		return this.longUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  长连接
	 */
	public void setLongUrl(java.lang.String longUrl){
		this.longUrl = longUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  短链接
	 */
	@Column(name ="SHORT_URL",nullable=true,length=255)
	public java.lang.String getShortUrl(){
		return this.shortUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  短链接
	 */
	public void setShortUrl(java.lang.String shortUrl){
		this.shortUrl = shortUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众号ID
	 */
	@Column(name ="ACCOUNT_ID",nullable=true,length=50)
	public java.lang.String getAccountId(){
		return this.accountId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众号ID
	 */
	public void setAccountId(java.lang.String accountId){
		this.accountId = accountId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	@Column(name ="WX_NAME",nullable=true,length=32)
	public java.lang.String getWxName(){
		return this.wxName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setWxName(java.lang.String wxName){
		this.wxName = wxName;
	}
}
