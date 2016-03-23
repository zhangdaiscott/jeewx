package org.jeecgframework.web.rest.entity;

import java.util.Date;

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
 * @Description: 第三方平台Ticket
 * @author zhangdaihao
 * @date 2015-08-28 12:22:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_open_account", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class WeixinOpenAccountEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**appid*/
	private java.lang.String appid;
	/**第三方平台推送 : ticket*/
	private java.lang.String ticket;
	private Date getTicketTime;
	
	@Column(name ="get_ticket_time")
	public Date getGetTicketTime() {
		return getTicketTime;
	}

	public void setGetTicketTime(Date getTicketTime) {
		this.getTicketTime = getTicketTime;
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
	 *@return: java.lang.String  appid
	 */
	@Column(name ="APPID",nullable=true,length=200)
	public java.lang.String getAppid(){
		return this.appid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  appid
	 */
	public void setAppid(java.lang.String appid){
		this.appid = appid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第三方平台推送 : ticket
	 */
	@Column(name ="TICKET",nullable=true,length=200)
	public java.lang.String getTicket(){
		return this.ticket;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方平台推送 : ticket
	 */
	public void setTicket(java.lang.String ticket){
		this.ticket = ticket;
	}
}
