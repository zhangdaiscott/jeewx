package weixin.guanjia.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 扩展接口管理
 * @author onlineGenerator
 * @date 2014-06-04 23:21:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_expandconfig", schema = "")
@SuppressWarnings("serial")
public class WeixinExpandconfigEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**关键字*/
	private java.lang.String keyword;
	/**类长名*/
	private java.lang.String classname;
	/**微信公众帐号*/
	private java.lang.String accountid;
	/**功能名称*/
	private java.lang.String name;
	/**功能描述*/
	private java.lang.String content;
	
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
	 *@return: java.lang.String  关键字
	 */
	@Column(name ="KEYWORD",nullable=false,length=100)
	public java.lang.String getKeyword(){
		return this.keyword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关键字
	 */
	public void setKeyword(java.lang.String keyword){
		this.keyword = keyword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类长名
	 */
	@Column(name ="CLASSNAME",nullable=false,length=100)
	public java.lang.String getClassname(){
		return this.classname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类长名
	 */
	public void setClassname(java.lang.String classname){
		this.classname = classname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信公众帐号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=200)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信公众帐号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  功能名称
	 */
	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  功能名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  功能描述
	 */
	@Column(name ="CONTENT",nullable=true,length=300)
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
}
