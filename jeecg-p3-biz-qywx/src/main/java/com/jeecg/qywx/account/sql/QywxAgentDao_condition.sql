		<#if qywxAgent.accountId ?exists && qywxAgent.accountId ?length gt 0>
		    /* 企业号ID */
			and qa.account_id like CONCAT('%', :qywxAgent.accountId ,'%') 
		</#if>
		<#if qywxAgent.wxAgentid ?exists && qywxAgent.wxAgentid ?length gt 0>
		    /* 应用ID(微信) */
			and qa.wx_agentid like CONCAT('%', :qywxAgent.wxAgentid ,'%') 
		</#if>
		<#if qywxAgent.agentName ?exists && qywxAgent.agentName ?length gt 0>
		    /* 应用名称 */
			and qa.agent_name like CONCAT('%', :qywxAgent.agentName ,'%') 
		</#if>
		<#if qywxAgent.token ?exists && qywxAgent.token ?length gt 0>
		    /* 回调token */
			and qa.token like CONCAT('%', :qywxAgent.token ,'%') 
		</#if>
		<#if qywxAgent.encodingAESKey ?exists && qywxAgent.encodingAESKey ?length gt 0>
		    /* 回调encodingAESKey */
			and qa.encodingAESKey like CONCAT('%', :qywxAgent.encodingAESKey ,'%') 
		</#if>
		<#if qywxAgent.squareLogoUrl ?exists && qywxAgent.squareLogoUrl ?length gt 0>
		    /* 方形头像 */
			and qa.square_logo_url like CONCAT('%', :qywxAgent.squareLogoUrl ,'%') 
		</#if>
		<#if qywxAgent.roundLogoUrl ?exists && qywxAgent.roundLogoUrl ?length gt 0>
		    /* 圆形头像 */
			and qa.round_logo_url like CONCAT('%', :qywxAgent.roundLogoUrl ,'%') 
		</#if>
		<#if qywxAgent.description ?exists && qywxAgent.description ?length gt 0>
		    /* 圆形头像 */
			and qa.description like CONCAT('%', :qywxAgent.description ,'%') 
		</#if>
		<#if qywxAgent.closeStatus ?exists && qywxAgent.closeStatus ?length gt 0>
		    /* 是否被禁用 */
			and qa.close_status like CONCAT('%', :qywxAgent.closeStatus ,'%') 
		</#if>
		<#if qywxAgent.redirectDomain ?exists && qywxAgent.redirectDomain ?length gt 0>
		    /* 可信域名 */
			and qa.redirect_domain like CONCAT('%', :qywxAgent.redirectDomain ,'%') 
		</#if>
		<#if qywxAgent.reportLocationFlag ?exists && qywxAgent.reportLocationFlag ?length gt 0>
		    /* 是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报 */
			and qa.report_location_flag like CONCAT('%', :qywxAgent.reportLocationFlag ,'%') 
		</#if>
		<#if qywxAgent.isreportuser ?exists && qywxAgent.isreportuser ?length gt 0>
		    /* 是否接收用户变更通知。0：不接收；1：接收 */
			and qa.isreportuser like CONCAT('%', :qywxAgent.isreportuser ,'%') 
		</#if>
		<#if qywxAgent.isreportenter ?exists && qywxAgent.isreportenter ?length gt 0>
		    /* 是否上报用户进入应用事件。0：不接收；1：接收 */
			and qa.isreportenter like CONCAT('%', :qywxAgent.isreportenter ,'%') 
		</#if>
		<#if qywxAgent.appType ?exists && qywxAgent.appType ?length gt 0>
		    /* 应用类型。1：消息型；2：主页型 */
			and qa.app_type like CONCAT('%', :qywxAgent.appType ,'%') 
		</#if>
		<#if qywxAgent.createName ?exists && qywxAgent.createName ?length gt 0>
		    /* 创建人名称 */
			and qa.create_name like CONCAT('%', :qywxAgent.createName ,'%') 
		</#if>
		<#if qywxAgent.createBy ?exists && qywxAgent.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qa.create_by like CONCAT('%', :qywxAgent.createBy ,'%') 
		</#if>
	    <#if qywxAgent.createDate ?exists>
		    /* 创建日期 */
			and qa.create_date = :qywxAgent.createDate
		</#if>
		<#if qywxAgent.updateName ?exists && qywxAgent.updateName ?length gt 0>
		    /* 更新人名称 */
			and qa.update_name like CONCAT('%', :qywxAgent.updateName ,'%') 
		</#if>
		<#if qywxAgent.updateBy ?exists && qywxAgent.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qa.update_by like CONCAT('%', :qywxAgent.updateBy ,'%') 
		</#if>
	    <#if qywxAgent.updateDate ?exists>
		    /* 更新日期 */
			and qa.update_date = :qywxAgent.updateDate
		</#if>
