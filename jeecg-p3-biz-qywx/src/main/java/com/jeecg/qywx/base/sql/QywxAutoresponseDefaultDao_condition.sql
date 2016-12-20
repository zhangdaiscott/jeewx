		<#if qywxAutoresponseDefault.templatename ?exists && qywxAutoresponseDefault.templatename ?length gt 0>
		    /* 模板名称 */
			and qad.templatename like CONCAT('%', :qywxAutoresponseDefault.templatename ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.templateid ?exists && qywxAutoresponseDefault.templateid ?length gt 0>
		    /* 模板Id */
			and qad.templateid like CONCAT('%', :qywxAutoresponseDefault.templateid ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.msgtype ?exists && qywxAutoresponseDefault.msgtype ?length gt 0>
		    /* 消息类型 */
			and qad.msgtype like CONCAT('%', :qywxAutoresponseDefault.msgtype ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.accountid ?exists && qywxAutoresponseDefault.accountid ?length gt 0>
		    /* 微信账号Id */
			and qad.accountid like CONCAT('%', :qywxAutoresponseDefault.accountid ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.iswork ?exists && qywxAutoresponseDefault.iswork ?length gt 0>
		    /* 是否启用 */
			and qad.iswork like CONCAT('%', :qywxAutoresponseDefault.iswork ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.createName ?exists && qywxAutoresponseDefault.createName ?length gt 0>
		    /* 创建人名称 */
			and qad.create_name like CONCAT('%', :qywxAutoresponseDefault.createName ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.createBy ?exists && qywxAutoresponseDefault.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qad.create_by like CONCAT('%', :qywxAutoresponseDefault.createBy ,'%') 
		</#if>
	    <#if qywxAutoresponseDefault.createDate ?exists>
		    /* 创建日期 */
			and qad.create_date = :qywxAutoresponseDefault.createDate
		</#if>
		<#if qywxAutoresponseDefault.updateName ?exists && qywxAutoresponseDefault.updateName ?length gt 0>
		    /* 更新人名称 */
			and qad.update_name like CONCAT('%', :qywxAutoresponseDefault.updateName ,'%') 
		</#if>
		<#if qywxAutoresponseDefault.updateBy ?exists && qywxAutoresponseDefault.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qad.update_by like CONCAT('%', :qywxAutoresponseDefault.updateBy ,'%') 
		</#if>
	    <#if qywxAutoresponseDefault.updateDate ?exists>
		    /* 更新日期 */
			and qad.update_date = :qywxAutoresponseDefault.updateDate
		</#if>
