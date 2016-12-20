UPDATE alipay_autoresponse_default
SET 
	   <#if alipayAutoresponseDefault.templatename ?exists && alipayAutoresponseDefault.templatename ?length gt 0>
		   templatename = :alipayAutoresponseDefault.templatename,
		</#if>
	   <#if alipayAutoresponseDefault.templateid ?exists && alipayAutoresponseDefault.templateid ?length gt 0>
		   templateid = :alipayAutoresponseDefault.templateid,
		</#if>
	   <#if alipayAutoresponseDefault.msgtype ?exists && alipayAutoresponseDefault.msgtype ?length gt 0>
		   msgtype = :alipayAutoresponseDefault.msgtype,
		</#if>
	   <#if alipayAutoresponseDefault.accountid ?exists && alipayAutoresponseDefault.accountid ?length gt 0>
		   accountid = :alipayAutoresponseDefault.accountid,
		</#if>
	   <#if alipayAutoresponseDefault.iswork ?exists && alipayAutoresponseDefault.iswork ?length gt 0>
		   iswork = :alipayAutoresponseDefault.iswork,
		</#if>
	   <#if alipayAutoresponseDefault.createName ?exists && alipayAutoresponseDefault.createName ?length gt 0>
		   create_name = :alipayAutoresponseDefault.createName,
		</#if>
	   <#if alipayAutoresponseDefault.createBy ?exists && alipayAutoresponseDefault.createBy ?length gt 0>
		   create_by = :alipayAutoresponseDefault.createBy,
		</#if>
	    <#if alipayAutoresponseDefault.createDate ?exists>
		   create_date = :alipayAutoresponseDefault.createDate,
		</#if>
	   <#if alipayAutoresponseDefault.updateName ?exists && alipayAutoresponseDefault.updateName ?length gt 0>
		   update_name = :alipayAutoresponseDefault.updateName,
		</#if>
	   <#if alipayAutoresponseDefault.updateBy ?exists && alipayAutoresponseDefault.updateBy ?length gt 0>
		   update_by = :alipayAutoresponseDefault.updateBy,
		</#if>
	    <#if alipayAutoresponseDefault.updateDate ?exists>
		   update_date = :alipayAutoresponseDefault.updateDate,
		</#if>
WHERE id = :alipayAutoresponseDefault.id