		<#if qywxTexttemplate.templateName ?exists && qywxTexttemplate.templateName ?length gt 0>
		    /* 模板名称 */
			and qt.template_name like CONCAT('%', :qywxTexttemplate.templateName ,'%') 
		</#if>
		<#if qywxTexttemplate.accountid ?exists && qywxTexttemplate.accountid ?length gt 0>
		    /* 微信企业账户id */
			and qt.accountid like CONCAT('%', :qywxTexttemplate.accountid ,'%') 
		</#if>
