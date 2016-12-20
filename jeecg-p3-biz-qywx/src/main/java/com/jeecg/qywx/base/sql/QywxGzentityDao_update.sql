UPDATE qywx_gzentity
SET 
	   <#if qywxGzentity.templateName ?exists && qywxGzentity.templateName ?length gt 0>
		   template_name = :qywxGzentity.templateName,
		</#if>
	   <#if qywxGzentity.templateId ?exists && qywxGzentity.templateId ?length gt 0>
		   template_id = :qywxGzentity.templateId,
		</#if>
	   <#if qywxGzentity.templateType ?exists && qywxGzentity.templateType ?length gt 0>
		   template_type = :qywxGzentity.templateType,
		</#if>
	   <#if qywxGzentity.isWork ?exists && qywxGzentity.isWork ?length gt 0>
		   is_work = :qywxGzentity.isWork,
		</#if>
	   <#if qywxGzentity.accountid ?exists && qywxGzentity.accountid ?length gt 0>
		   accountid = :qywxGzentity.accountid,
		</#if>
	   <#if qywxGzentity.createName ?exists && qywxGzentity.createName ?length gt 0>
		   create_name = :qywxGzentity.createName,
		</#if>
	   <#if qywxGzentity.createBy ?exists && qywxGzentity.createBy ?length gt 0>
		   create_by = :qywxGzentity.createBy,
		</#if>
	    <#if qywxGzentity.createDate ?exists>
		   create_date = :qywxGzentity.createDate,
		</#if>
	   <#if qywxGzentity.updateName ?exists && qywxGzentity.updateName ?length gt 0>
		   update_name = :qywxGzentity.updateName,
		</#if>
	   <#if qywxGzentity.updateBy ?exists && qywxGzentity.updateBy ?length gt 0>
		   update_by = :qywxGzentity.updateBy,
		</#if>
	    <#if qywxGzentity.updateDate ?exists>
		   update_date = :qywxGzentity.updateDate,
		</#if>
WHERE id = :qywxGzentity.id