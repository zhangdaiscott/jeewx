		<#if alipayAutoresponse.keyWord ?exists && alipayAutoresponse.keyWord ?length gt 0>
		    /* 关键字 */
			and qa.key_word like CONCAT('%', :alipayAutoresponse.keyWord ,'%') 
		</#if>
		<#if alipayAutoresponse.resContent ?exists && alipayAutoresponse.resContent ?length gt 0>
		    /* 回复内容 */
			and qa.res_content like CONCAT('%', :alipayAutoresponse.resContent ,'%') 
		</#if>
		<#if alipayAutoresponse.msgType ?exists && alipayAutoresponse.msgType ?length gt 0>
		    /* 消息类型 */
			and qa.msg_type like CONCAT('%', :alipayAutoresponse.msgType ,'%') 
		</#if>
		<#if alipayAutoresponse.templateName ?exists && alipayAutoresponse.templateName ?length gt 0>
		    /* 模板名称 */
			and qa.template_name like CONCAT('%', :alipayAutoresponse.templateName ,'%') 
		</#if>
		<#if alipayAutoresponse.accountid ?exists && alipayAutoresponse.accountid ?length gt 0>
		    /* 微信账号id */
			and qa.accountid like CONCAT('%', :alipayAutoresponse.accountid ,'%') 
		</#if>
		<#if alipayAutoresponse.createName ?exists && alipayAutoresponse.createName ?length gt 0>
		    /* 创建人名称 */
			and qa.create_name like CONCAT('%', :alipayAutoresponse.createName ,'%') 
		</#if>
		<#if alipayAutoresponse.createBy ?exists && alipayAutoresponse.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qa.create_by like CONCAT('%', :alipayAutoresponse.createBy ,'%') 
		</#if>
	    <#if alipayAutoresponse.createDate ?exists>
		    /* 创建日期 */
			and qa.create_date = :alipayAutoresponse.createDate
		</#if>
		<#if alipayAutoresponse.updateName ?exists && alipayAutoresponse.updateName ?length gt 0>
		    /* 更新人名称 */
			and qa.update_name like CONCAT('%', :alipayAutoresponse.updateName ,'%') 
		</#if>
		<#if alipayAutoresponse.updateBy ?exists && alipayAutoresponse.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qa.update_by like CONCAT('%', :alipayAutoresponse.updateBy ,'%') 
		</#if>
	    <#if alipayAutoresponse.updateDate ?exists>
		    /* 更新日期 */
			and qa.update_date = :alipayAutoresponse.updateDate
		</#if>
