package com.jeecg.qywx.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 描述：</b>QywxLocation:地理位置表<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author p3.jeecg
 * @since：2016年05月26日 15时03分31秒 星期四 
 * @version:1.0
 */
public class QywxLocation implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *企业号corpId	 */	private String corpid;	/**	 * 	成员UserID 	 */	private String userid;	/**	 *创建时间	 */	private Date createtime;	/**	 *地理位置纬度	 */	private String latitude;	/**	 *地理位置径度	 */	private String longitude;	/**	 *地理位置精度	 */	private String locationPrecision;	/**	 *企业应用id	 */	private String agentid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCorpid() {	    return this.corpid;	}	public void setCorpid(String corpid) {	    this.corpid=corpid;	}	public String getUserid() {	    return this.userid;	}	public void setUserid(String userid) {	    this.userid=userid;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getLatitude() {	    return this.latitude;	}	public void setLatitude(String latitude) {	    this.latitude=latitude;	}	public String getLongitude() {	    return this.longitude;	}	public void setLongitude(String longitude) {	    this.longitude=longitude;	}	public String getLocationPrecision() {	    return this.locationPrecision;	}	public void setLocationPrecision(String locationPrecision) {	    this.locationPrecision=locationPrecision;	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
}

