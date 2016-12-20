		<#if alipayTexttemplate.templateName ?exists && alipayTexttemplate.templateName ?length gt 0>
		    /* 模板名称 */
			and qt.template_name like CONCAT('%', :alipayTexttemplate.templateName ,'%') 
		</#if>
		<#if alipayTexttemplate.accountid ?exists && alipayTexttemplate.accountid ?length gt 0>
		    /* 微信企业账户id */
			and qt.accountid like CONCAT('%', :alipayTexttemplate.accountid ,'%') 
		</#if>
