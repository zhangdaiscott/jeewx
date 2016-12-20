UPDATE qywx_newstemplate
SET 
	   <#if qywxNewstemplate.templateName ?exists && qywxNewstemplate.templateName ?length gt 0>
		   template_name = :qywxNewstemplate.templateName,
		</#if>
	   <#if qywxNewstemplate.templateType ?exists && qywxNewstemplate.templateType ?length gt 0>
		   template_type = :qywxNewstemplate.templateType,
		</#if>
	   <#if qywxNewstemplate.accountid ?exists && qywxNewstemplate.accountid ?length gt 0>
		   accountid = :qywxNewstemplate.accountid,
		</#if>
	   <#if qywxNewstemplate.createName ?exists && qywxNewstemplate.createName ?length gt 0>
		   create_name = :qywxNewstemplate.createName,
		</#if>
	   <#if qywxNewstemplate.createBy ?exists && qywxNewstemplate.createBy ?length gt 0>
		   create_by = :qywxNewstemplate.createBy,
		</#if>
	    <#if qywxNewstemplate.createDate ?exists>
		   create_date = :qywxNewstemplate.createDate,
		</#if>
	   <#if qywxNewstemplate.updateName ?exists && qywxNewstemplate.updateName ?length gt 0>
		   update_name = :qywxNewstemplate.updateName,
		</#if>
	   <#if qywxNewstemplate.updateBy ?exists && qywxNewstemplate.updateBy ?length gt 0>
		   update_by = :qywxNewstemplate.updateBy,
		</#if>
	    <#if qywxNewstemplate.updateDate ?exists>
		   update_date = :qywxNewstemplate.updateDate,
		</#if>
WHERE id = :qywxNewstemplate.id