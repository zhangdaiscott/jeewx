UPDATE alipay_gzentity
SET 
	   <#if alipayGzentity.templateName ?exists && alipayGzentity.templateName ?length gt 0>
		   template_name = :alipayGzentity.templateName,
		</#if>
	   <#if alipayGzentity.templateId ?exists && alipayGzentity.templateId ?length gt 0>
		   template_id = :alipayGzentity.templateId,
		</#if>
	   <#if alipayGzentity.templateType ?exists && alipayGzentity.templateType ?length gt 0>
		   template_type = :alipayGzentity.templateType,
		</#if>
	   <#if alipayGzentity.isWork ?exists && alipayGzentity.isWork ?length gt 0>
		   is_work = :alipayGzentity.isWork,
		</#if>
	   <#if alipayGzentity.accountid ?exists && alipayGzentity.accountid ?length gt 0>
		   accountid = :alipayGzentity.accountid,
		</#if>
	   <#if alipayGzentity.createName ?exists && alipayGzentity.createName ?length gt 0>
		   create_name = :alipayGzentity.createName,
		</#if>
	   <#if alipayGzentity.createBy ?exists && alipayGzentity.createBy ?length gt 0>
		   create_by = :alipayGzentity.createBy,
		</#if>
	    <#if alipayGzentity.createDate ?exists>
		   create_date = :alipayGzentity.createDate,
		</#if>
	   <#if alipayGzentity.updateName ?exists && alipayGzentity.updateName ?length gt 0>
		   update_name = :alipayGzentity.updateName,
		</#if>
	   <#if alipayGzentity.updateBy ?exists && alipayGzentity.updateBy ?length gt 0>
		   update_by = :alipayGzentity.updateBy,
		</#if>
	    <#if alipayGzentity.updateDate ?exists>
		   update_date = :alipayGzentity.updateDate,
		</#if>
WHERE id = :alipayGzentity.id