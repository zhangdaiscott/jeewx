package weixin.guanjia.message.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * 文本消息存储实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="weixin_receivetext")
public class ReceiveText extends IdEntity {
	// 开发者微信号
    private String toUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private Timestamp createTime;
    // 消息类型（text/image/location/link）
    private String MsgType;
    // 消息id，64位整型
    private String MsgId;
    //消息内容
    private String Content;
    //是否回复
    private String response;
    //回复内容
    private String rescontent;
    //用户昵称
    private String nickName;
    //微信账号Id
    private String accountId;
    
    @Column(name="tousername")
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	@Column(name="fromusername")
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	@Column(name="msgtype")
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@Column(name="msgid")
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	@Column(name="content")
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	@Column(name="response")
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Column(name="rescontent")
	public String getRescontent() {
		return rescontent;
	}
	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}
	@Column(name="createtime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name="nickname")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name="accountid")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
