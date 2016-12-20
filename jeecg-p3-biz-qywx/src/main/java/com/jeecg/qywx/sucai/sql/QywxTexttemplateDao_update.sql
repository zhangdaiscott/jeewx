UPDATE qywx_texttemplate
SET 
	   <#if qywxTexttemplate.templateName ?exists && qywxTexttemplate.templateName ?length gt 0>
		   template_name = :qywxTexttemplate.templateName,
		</#if>
	   <#if qywxTexttemplate.templateContent ?exists && qywxTexttemplate.templateContent ?length gt 0>
		   template_content = :qywxTexttemplate.templateContent,
		</#if>
	   <#if qywxTexttemplate.accountid ?exists && qywxTexttemplate.accountid ?length gt 0>
		   accountid = :qywxTexttemplate.accountid,
		</#if>
	   <#if qywxTexttemplate.createName ?exists && qywxTexttemplate.createName ?length gt 0>
		   create_name = :qywxTexttemplate.createName,
		</#if>
	   <#if qywxTexttemplate.createBy ?exists && qywxTexttemplate.createBy ?length gt 0>
		   create_by = :qywxTexttemplate.createBy,
		</#if>
	    <#if qywxTexttemplate.createDate ?exists>
		   create_date = :qywxTexttemplate.createDate,
		</#if>
	   <#if qywxTexttemplate.updateName ?exists && qywxTexttemplate.updateName ?length gt 0>
		   update_name = :qywxTexttemplate.updateName,
		</#if>
	   <#if qywxTexttemplate.updateBy ?exists && qywxTexttemplate.updateBy ?length gt 0>
		   update_by = :qywxTexttemplate.updateBy,
		</#if>
	    <#if qywxTexttemplate.updateDate ?exists>
		   update_date = :qywxTexttemplate.updateDate,
		</#if>
WHERE id = :qywxTexttemplate.id