		<#if qywxReceivetext.tousername ?exists && qywxReceivetext.tousername ?length gt 0>
		    /* 开发者微信号 */
			and qr.tousername like CONCAT('%', :qywxReceivetext.tousername ,'%') 
		</#if>
		<#if qywxReceivetext.fromusername ?exists && qywxReceivetext.fromusername ?length gt 0>
		    /* 发送方帐号（一个OpenID） */
			and qr.fromusername like CONCAT('%', :qywxReceivetext.fromusername ,'%') 
		</#if>
	    <#if qywxReceivetext.createtime ?exists>
		    /* 消息创建时间 （整型） */
			and qr.createtime = :qywxReceivetext.createtime
		</#if>
		<#if qywxReceivetext.msgtype ?exists && qywxReceivetext.msgtype ?length gt 0>
		    /* 消息类型（text/image/location/link） */
			and qr.msgtype like CONCAT('%', :qywxReceivetext.msgtype ,'%') 
		</#if>
		<#if qywxReceivetext.msgid ?exists && qywxReceivetext.msgid ?length gt 0>
		    /* 消息id，64位整型 */
			and qr.msgid like CONCAT('%', :qywxReceivetext.msgid ,'%') 
		</#if>
		<#if qywxReceivetext.content ?exists && qywxReceivetext.content ?length gt 0>
		    /* 消息内容 */
			and qr.content like CONCAT('%', :qywxReceivetext.content ,'%') 
		</#if>
		<#if qywxReceivetext.response ?exists && qywxReceivetext.response ?length gt 0>
		    /* 是否回复 */
			and qr.response like CONCAT('%', :qywxReceivetext.response ,'%') 
		</#if>
		<#if qywxReceivetext.rescontent ?exists && qywxReceivetext.rescontent ?length gt 0>
		    /* 回复内容 */
			and qr.rescontent like CONCAT('%', :qywxReceivetext.rescontent ,'%') 
		</#if>
		<#if qywxReceivetext.nickname ?exists && qywxReceivetext.nickname ?length gt 0>
		    /* 用户昵称 */
			and qr.nickname like CONCAT('%', :qywxReceivetext.nickname ,'%') 
		</#if>
		<#if qywxReceivetext.accountid ?exists && qywxReceivetext.accountid ?length gt 0>
		    /* 微信账号Id */
			and qr.accountid like CONCAT('%', :qywxReceivetext.accountid ,'%') 
		</#if>
		<#if qywxReceivetext.createName ?exists && qywxReceivetext.createName ?length gt 0>
		    /* 创建人名称 */
			and qr.create_name like CONCAT('%', :qywxReceivetext.createName ,'%') 
		</#if>
		<#if qywxReceivetext.createBy ?exists && qywxReceivetext.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qr.create_by like CONCAT('%', :qywxReceivetext.createBy ,'%') 
		</#if>
	    <#if qywxReceivetext.createDate ?exists>
		    /* 创建日期 */
			and qr.create_date = :qywxReceivetext.createDate
		</#if>
		<#if qywxReceivetext.updateName ?exists && qywxReceivetext.updateName ?length gt 0>
		    /* 更新人名称 */
			and qr.update_name like CONCAT('%', :qywxReceivetext.updateName ,'%') 
		</#if>
		<#if qywxReceivetext.updateBy ?exists && qywxReceivetext.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qr.update_by like CONCAT('%', :qywxReceivetext.updateBy ,'%') 
		</#if>
	    <#if qywxReceivetext.updateDate ?exists>
		    /* 更新日期 */
			and qr.update_date = :qywxReceivetext.updateDate
		</#if>
