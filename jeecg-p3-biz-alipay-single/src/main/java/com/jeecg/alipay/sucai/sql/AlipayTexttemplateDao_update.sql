UPDATE alipay_texttemplate
SET 
	   <#if alipayTexttemplate.templateName ?exists && alipayTexttemplate.templateName ?length gt 0>
		   template_name = :alipayTexttemplate.templateName,
		</#if>
	   <#if alipayTexttemplate.templateContent ?exists && alipayTexttemplate.templateContent ?length gt 0>
		   template_content = :alipayTexttemplate.templateContent,
		</#if>
	   <#if alipayTexttemplate.accountid ?exists && alipayTexttemplate.accountid ?length gt 0>
		   accountid = :alipayTexttemplate.accountid,
		</#if>
	   <#if alipayTexttemplate.createName ?exists && alipayTexttemplate.createName ?length gt 0>
		   create_name = :alipayTexttemplate.createName,
		</#if>
	   <#if alipayTexttemplate.createBy ?exists && alipayTexttemplate.createBy ?length gt 0>
		   create_by = :alipayTexttemplate.createBy,
		</#if>
	    <#if alipayTexttemplate.createDate ?exists>
		   create_date = :alipayTexttemplate.createDate,
		</#if>
	   <#if alipayTexttemplate.updateName ?exists && alipayTexttemplate.updateName ?length gt 0>
		   update_name = :alipayTexttemplate.updateName,
		</#if>
	   <#if alipayTexttemplate.updateBy ?exists && alipayTexttemplate.updateBy ?length gt 0>
		   update_by = :alipayTexttemplate.updateBy,
		</#if>
	    <#if alipayTexttemplate.updateDate ?exists>
		   update_date = :alipayTexttemplate.updateDate,
		</#if>
WHERE id = :alipayTexttemplate.id