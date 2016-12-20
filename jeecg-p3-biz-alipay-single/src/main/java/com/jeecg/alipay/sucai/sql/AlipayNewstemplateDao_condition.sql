		<#if alipayNewstemplate.templateName ?exists && alipayNewstemplate.templateName ?length gt 0>
		    /* 模板名称 */
			and qn.template_name like CONCAT('%', :alipayNewstemplate.templateName ,'%') 
		</#if>
		<#if alipayNewstemplate.templateType ?exists && alipayNewstemplate.templateType ?length gt 0>
		    /* 模板类型 */
			and qn.template_type like CONCAT('%', :alipayNewstemplate.templateType ,'%') 
		</#if>
		<#if alipayNewstemplate.accountid ?exists && alipayNewstemplate.accountid ?length gt 0>
		    /* 微信企业号账号id */
			and qn.accountid like CONCAT('%', :alipayNewstemplate.accountid ,'%') 
		</#if>
