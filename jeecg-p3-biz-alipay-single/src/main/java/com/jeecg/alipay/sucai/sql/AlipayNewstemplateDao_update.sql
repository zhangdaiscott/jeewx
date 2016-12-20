UPDATE alipay_newstemplate
SET 
	   <#if alipayNewstemplate.templateName ?exists && alipayNewstemplate.templateName ?length gt 0>
		   template_name = :alipayNewstemplate.templateName,
		</#if>
	   <#if alipayNewstemplate.templateType ?exists && alipayNewstemplate.templateType ?length gt 0>
		   template_type = :alipayNewstemplate.templateType,
		</#if>
	   <#if alipayNewstemplate.accountid ?exists && alipayNewstemplate.accountid ?length gt 0>
		   accountid = :alipayNewstemplate.accountid,
		</#if>
	   <#if alipayNewstemplate.createName ?exists && alipayNewstemplate.createName ?length gt 0>
		   create_name = :alipayNewstemplate.createName,
		</#if>
	   <#if alipayNewstemplate.createBy ?exists && alipayNewstemplate.createBy ?length gt 0>
		   create_by = :alipayNewstemplate.createBy,
		</#if>
	    <#if alipayNewstemplate.createDate ?exists>
		   create_date = :alipayNewstemplate.createDate,
		</#if>
	   <#if alipayNewstemplate.updateName ?exists && alipayNewstemplate.updateName ?length gt 0>
		   update_name = :alipayNewstemplate.updateName,
		</#if>
	   <#if alipayNewstemplate.updateBy ?exists && alipayNewstemplate.updateBy ?length gt 0>
		   update_by = :alipayNewstemplate.updateBy,
		</#if>
	    <#if alipayNewstemplate.updateDate ?exists>
		   update_date = :alipayNewstemplate.updateDate,
		</#if>
WHERE id = :alipayNewstemplate.id