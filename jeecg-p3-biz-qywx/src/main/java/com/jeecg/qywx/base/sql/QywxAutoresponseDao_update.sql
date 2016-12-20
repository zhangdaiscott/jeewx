UPDATE qywx_autoresponse
SET 
	   <#if qywxAutoresponse.keyWord ?exists && qywxAutoresponse.keyWord ?length gt 0>
		   key_word = :qywxAutoresponse.keyWord,
		</#if>
	   <#if qywxAutoresponse.resContent ?exists && qywxAutoresponse.resContent ?length gt 0>
		   res_content = :qywxAutoresponse.resContent,
		</#if>
	   <#if qywxAutoresponse.msgType ?exists && qywxAutoresponse.msgType ?length gt 0>
		   msg_type = :qywxAutoresponse.msgType,
		</#if>
	   <#if qywxAutoresponse.templateName ?exists && qywxAutoresponse.templateName ?length gt 0>
		   template_name = :qywxAutoresponse.templateName,
		</#if>
	   <#if qywxAutoresponse.accountid ?exists && qywxAutoresponse.accountid ?length gt 0>
		   accountid = :qywxAutoresponse.accountid,
		</#if>
	   <#if qywxAutoresponse.createName ?exists && qywxAutoresponse.createName ?length gt 0>
		   create_name = :qywxAutoresponse.createName,
		</#if>
	   <#if qywxAutoresponse.createBy ?exists && qywxAutoresponse.createBy ?length gt 0>
		   create_by = :qywxAutoresponse.createBy,
		</#if>
	    <#if qywxAutoresponse.createDate ?exists>
		   create_date = :qywxAutoresponse.createDate,
		</#if>
	   <#if qywxAutoresponse.updateName ?exists && qywxAutoresponse.updateName ?length gt 0>
		   update_name = :qywxAutoresponse.updateName,
		</#if>
	   <#if qywxAutoresponse.updateBy ?exists && qywxAutoresponse.updateBy ?length gt 0>
		   update_by = :qywxAutoresponse.updateBy,
		</#if>
	    <#if qywxAutoresponse.updateDate ?exists>
		   update_date = :qywxAutoresponse.updateDate,
		</#if>
WHERE id = :qywxAutoresponse.id