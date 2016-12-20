		<#if alipayAutoresponseDefault.templatename ?exists && alipayAutoresponseDefault.templatename ?length gt 0>
		    /* 模板名称 */
			and qad.templatename like CONCAT('%', :alipayAutoresponseDefault.templatename ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.templateid ?exists && alipayAutoresponseDefault.templateid ?length gt 0>
		    /* 模板Id */
			and qad.templateid like CONCAT('%', :alipayAutoresponseDefault.templateid ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.msgtype ?exists && alipayAutoresponseDefault.msgtype ?length gt 0>
		    /* 消息类型 */
			and qad.msgtype like CONCAT('%', :alipayAutoresponseDefault.msgtype ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.accountid ?exists && alipayAutoresponseDefault.accountid ?length gt 0>
		    /* 微信账号Id */
			and qad.accountid like CONCAT('%', :alipayAutoresponseDefault.accountid ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.iswork ?exists && alipayAutoresponseDefault.iswork ?length gt 0>
		    /* 是否启用 */
			and qad.iswork like CONCAT('%', :alipayAutoresponseDefault.iswork ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.createName ?exists && alipayAutoresponseDefault.createName ?length gt 0>
		    /* 创建人名称 */
			and qad.create_name like CONCAT('%', :alipayAutoresponseDefault.createName ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.createBy ?exists && alipayAutoresponseDefault.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qad.create_by like CONCAT('%', :alipayAutoresponseDefault.createBy ,'%') 
		</#if>
	    <#if alipayAutoresponseDefault.createDate ?exists>
		    /* 创建日期 */
			and qad.create_date = :alipayAutoresponseDefault.createDate
		</#if>
		<#if alipayAutoresponseDefault.updateName ?exists && alipayAutoresponseDefault.updateName ?length gt 0>
		    /* 更新人名称 */
			and qad.update_name like CONCAT('%', :alipayAutoresponseDefault.updateName ,'%') 
		</#if>
		<#if alipayAutoresponseDefault.updateBy ?exists && alipayAutoresponseDefault.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qad.update_by like CONCAT('%', :alipayAutoresponseDefault.updateBy ,'%') 
		</#if>
	    <#if alipayAutoresponseDefault.updateDate ?exists>
		    /* 更新日期 */
			and qad.update_date = :alipayAutoresponseDefault.updateDate
		</#if>
