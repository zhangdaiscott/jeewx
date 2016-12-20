		<#if qywxNewstemplate.templateName ?exists && qywxNewstemplate.templateName ?length gt 0>
		    /* 模板名称 */
			and qn.template_name like CONCAT('%', :qywxNewstemplate.templateName ,'%') 
		</#if>
		<#if qywxNewstemplate.templateType ?exists && qywxNewstemplate.templateType ?length gt 0>
		    /* 模板类型 */
			and qn.template_type like CONCAT('%', :qywxNewstemplate.templateType ,'%') 
		</#if>
		<#if qywxNewstemplate.accountid ?exists && qywxNewstemplate.accountid ?length gt 0>
		    /* 微信企业号账号id */
			and qn.accountid like CONCAT('%', :qywxNewstemplate.accountid ,'%') 
		</#if>
