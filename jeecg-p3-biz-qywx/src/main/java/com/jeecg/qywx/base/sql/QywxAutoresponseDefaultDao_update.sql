UPDATE qywx_autoresponse_default
SET 
	   <#if qywxAutoresponseDefault.templatename ?exists && qywxAutoresponseDefault.templatename ?length gt 0>
		   templatename = :qywxAutoresponseDefault.templatename,
		</#if>
	   <#if qywxAutoresponseDefault.templateid ?exists && qywxAutoresponseDefault.templateid ?length gt 0>
		   templateid = :qywxAutoresponseDefault.templateid,
		</#if>
	   <#if qywxAutoresponseDefault.msgtype ?exists && qywxAutoresponseDefault.msgtype ?length gt 0>
		   msgtype = :qywxAutoresponseDefault.msgtype,
		</#if>
	   <#if qywxAutoresponseDefault.accountid ?exists && qywxAutoresponseDefault.accountid ?length gt 0>
		   accountid = :qywxAutoresponseDefault.accountid,
		</#if>
	   <#if qywxAutoresponseDefault.iswork ?exists && qywxAutoresponseDefault.iswork ?length gt 0>
		   iswork = :qywxAutoresponseDefault.iswork,
		</#if>
	   <#if qywxAutoresponseDefault.createName ?exists && qywxAutoresponseDefault.createName ?length gt 0>
		   create_name = :qywxAutoresponseDefault.createName,
		</#if>
	   <#if qywxAutoresponseDefault.createBy ?exists && qywxAutoresponseDefault.createBy ?length gt 0>
		   create_by = :qywxAutoresponseDefault.createBy,
		</#if>
	    <#if qywxAutoresponseDefault.createDate ?exists>
		   create_date = :qywxAutoresponseDefault.createDate,
		</#if>
	   <#if qywxAutoresponseDefault.updateName ?exists && qywxAutoresponseDefault.updateName ?length gt 0>
		   update_name = :qywxAutoresponseDefault.updateName,
		</#if>
	   <#if qywxAutoresponseDefault.updateBy ?exists && qywxAutoresponseDefault.updateBy ?length gt 0>
		   update_by = :qywxAutoresponseDefault.updateBy,
		</#if>
	    <#if qywxAutoresponseDefault.updateDate ?exists>
		   update_date = :qywxAutoresponseDefault.updateDate,
		</#if>
WHERE id = :qywxAutoresponseDefault.id