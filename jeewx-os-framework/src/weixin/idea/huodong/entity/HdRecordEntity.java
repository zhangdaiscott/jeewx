package weixin.idea.huodong.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 参加活动记录
 * @author zhangdaihao
 * @date 2014-06-09 14:24:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_hdrecord", schema = "")
@SuppressWarnings("serial")
public class HdRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**活动Id*/
	private java.lang.String hdid;
	/**用户唯一标识*/
	private java.lang.String opendid;
	/**总数*/
	private java.lang.Integer total;
	/**时间*/
	private java.util.Date addtime;
	/**nickname*/
	private java.lang.String nickname;
	private String accountid;
	
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动Id
	 */
	@Column(name ="HDID",nullable=true,length=100)
	public java.lang.String getHdid(){
		return this.hdid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动Id
	 */
	public void setHdid(java.lang.String hdid){
		this.hdid = hdid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户唯一标识
	 */
	@Column(name ="OPENDID",nullable=true,length=100)
	public java.lang.String getOpendid(){
		return this.opendid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户唯一标识
	 */
	public void setOpendid(java.lang.String opendid){
		this.opendid = opendid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总数
	 */
	@Column(name ="TOTAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总数
	 */
	public void setTotal(java.lang.Integer total){
		this.total = total;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间
	 */
	@Column(name ="ADDTIME",nullable=true)
	public java.util.Date getAddtime(){
		return this.addtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间
	 */
	public void setAddtime(java.util.Date addtime){
		this.addtime = addtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  nickname
	 */
	@Column(name ="NICKNAME",nullable=true,length=200)
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  nickname
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
}
