		<#if alipayReceivetext.tousername ?exists && alipayReceivetext.tousername ?length gt 0>
		    /* 开发者微信号 */
			and qr.tousername like CONCAT('%', :alipayReceivetext.tousername ,'%') 
		</#if>
		<#if alipayReceivetext.fromusername ?exists && alipayReceivetext.fromusername ?length gt 0>
		    /* 发送方帐号（一个OpenID） */
			and qr.fromusername like CONCAT('%', :alipayReceivetext.fromusername ,'%') 
		</#if>
	    <#if alipayReceivetext.createtime ?exists>
		    /* 消息创建时间 （整型） */
			and qr.createtime = :alipayReceivetext.createtime
		</#if>
		<#if alipayReceivetext.msgtype ?exists && alipayReceivetext.msgtype ?length gt 0>
		    /* 消息类型（text/image/location/link） */
			and qr.msgtype like CONCAT('%', :alipayReceivetext.msgtype ,'%') 
		</#if>
		<#if alipayReceivetext.msgid ?exists && alipayReceivetext.msgid ?length gt 0>
		    /* 消息id，64位整型 */
			and qr.msgid like CONCAT('%', :alipayReceivetext.msgid ,'%') 
		</#if>
		<#if alipayReceivetext.content ?exists && alipayReceivetext.content ?length gt 0>
		    /* 消息内容 */
			and qr.content like CONCAT('%', :alipayReceivetext.content ,'%') 
		</#if>
		<#if alipayReceivetext.response ?exists && alipayReceivetext.response ?length gt 0>
		    /* 是否回复 */
			and qr.response like CONCAT('%', :alipayReceivetext.response ,'%') 
		</#if>
		<#if alipayReceivetext.rescontent ?exists && alipayReceivetext.rescontent ?length gt 0>
		    /* 回复内容 */
			and qr.rescontent like CONCAT('%', :alipayReceivetext.rescontent ,'%') 
		</#if>
		<#if alipayReceivetext.nickname ?exists && alipayReceivetext.nickname ?length gt 0>
		    /* 用户昵称 */
			and qr.nickname like CONCAT('%', :alipayReceivetext.nickname ,'%') 
		</#if>
		<#if alipayReceivetext.accountid ?exists && alipayReceivetext.accountid ?length gt 0>
		    /* 微信账号Id */
			and qr.accountid like CONCAT('%', :alipayReceivetext.accountid ,'%') 
		</#if>
		<#if alipayReceivetext.createName ?exists && alipayReceivetext.createName ?length gt 0>
		    /* 创建人名称 */
			and qr.create_name like CONCAT('%', :alipayReceivetext.createName ,'%') 
		</#if>
		<#if alipayReceivetext.createBy ?exists && alipayReceivetext.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qr.create_by like CONCAT('%', :alipayReceivetext.createBy ,'%') 
		</#if>
	    <#if alipayReceivetext.createDate ?exists>
		    /* 创建日期 */
			and qr.create_date = :alipayReceivetext.createDate
		</#if>
		<#if alipayReceivetext.updateName ?exists && alipayReceivetext.updateName ?length gt 0>
		    /* 更新人名称 */
			and qr.update_name like CONCAT('%', :alipayReceivetext.updateName ,'%') 
		</#if>
		<#if alipayReceivetext.updateBy ?exists && alipayReceivetext.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qr.update_by like CONCAT('%', :alipayReceivetext.updateBy ,'%') 
		</#if>
	    <#if alipayReceivetext.updateDate ?exists>
		    /* 更新日期 */
			and qr.update_date = :alipayReceivetext.updateDate
		</#if>
