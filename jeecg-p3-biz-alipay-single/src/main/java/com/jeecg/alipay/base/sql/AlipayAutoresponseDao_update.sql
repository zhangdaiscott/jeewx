UPDATE alipay_autoresponse
SET 
	   <#if alipayAutoresponse.keyWord ?exists && alipayAutoresponse.keyWord ?length gt 0>
		   key_word = :alipayAutoresponse.keyWord,
		</#if>
	   <#if alipayAutoresponse.resContent ?exists && alipayAutoresponse.resContent ?length gt 0>
		   res_content = :alipayAutoresponse.resContent,
		</#if>
	   <#if alipayAutoresponse.msgType ?exists && alipayAutoresponse.msgType ?length gt 0>
		   msg_type = :alipayAutoresponse.msgType,
		</#if>
	   <#if alipayAutoresponse.templateName ?exists && alipayAutoresponse.templateName ?length gt 0>
		   template_name = :alipayAutoresponse.templateName,
		</#if>
	   <#if alipayAutoresponse.accountid ?exists && alipayAutoresponse.accountid ?length gt 0>
		   accountid = :alipayAutoresponse.accountid,
		</#if>
	   <#if alipayAutoresponse.createName ?exists && alipayAutoresponse.createName ?length gt 0>
		   create_name = :alipayAutoresponse.createName,
		</#if>
	   <#if alipayAutoresponse.createBy ?exists && alipayAutoresponse.createBy ?length gt 0>
		   create_by = :alipayAutoresponse.createBy,
		</#if>
	    <#if alipayAutoresponse.createDate ?exists>
		   create_date = :alipayAutoresponse.createDate,
		</#if>
	   <#if alipayAutoresponse.updateName ?exists && alipayAutoresponse.updateName ?length gt 0>
		   update_name = :alipayAutoresponse.updateName,
		</#if>
	   <#if alipayAutoresponse.updateBy ?exists && alipayAutoresponse.updateBy ?length gt 0>
		   update_by = :alipayAutoresponse.updateBy,
		</#if>
	    <#if alipayAutoresponse.updateDate ?exists>
		   update_date = :alipayAutoresponse.updateDate,
		</#if>
WHERE id = :alipayAutoresponse.id