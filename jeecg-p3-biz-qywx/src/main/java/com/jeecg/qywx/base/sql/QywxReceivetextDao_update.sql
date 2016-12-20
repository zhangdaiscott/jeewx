UPDATE qywx_receivetext
SET 
	   <#if qywxReceivetext.tousername ?exists && qywxReceivetext.tousername ?length gt 0>
		   tousername = :qywxReceivetext.tousername,
		</#if>
	   <#if qywxReceivetext.fromusername ?exists && qywxReceivetext.fromusername ?length gt 0>
		   fromusername = :qywxReceivetext.fromusername,
		</#if>
	    <#if qywxReceivetext.createtime ?exists>
		   createtime = :qywxReceivetext.createtime,
		</#if>
	   <#if qywxReceivetext.msgtype ?exists && qywxReceivetext.msgtype ?length gt 0>
		   msgtype = :qywxReceivetext.msgtype,
		</#if>
	   <#if qywxReceivetext.msgid ?exists && qywxReceivetext.msgid ?length gt 0>
		   msgid = :qywxReceivetext.msgid,
		</#if>
	   <#if qywxReceivetext.content ?exists && qywxReceivetext.content ?length gt 0>
		   content = :qywxReceivetext.content,
		</#if>
	   <#if qywxReceivetext.response ?exists && qywxReceivetext.response ?length gt 0>
		   response = :qywxReceivetext.response,
		</#if>
	   <#if qywxReceivetext.rescontent ?exists && qywxReceivetext.rescontent ?length gt 0>
		   rescontent = :qywxReceivetext.rescontent,
		</#if>
	   <#if qywxReceivetext.nickname ?exists && qywxReceivetext.nickname ?length gt 0>
		   nickname = :qywxReceivetext.nickname,
		</#if>
	   <#if qywxReceivetext.accountid ?exists && qywxReceivetext.accountid ?length gt 0>
		   accountid = :qywxReceivetext.accountid,
		</#if>
		<#if qywxReceivetext.agentId ?exists && qywxReceivetext.agentId ?length gt 0>
		   agent_id = :qywxReceivetext.agentId,
		</#if>
	   <#if qywxReceivetext.createName ?exists && qywxReceivetext.createName ?length gt 0>
		   create_name = :qywxReceivetext.createName,
		</#if>
	   <#if qywxReceivetext.createBy ?exists && qywxReceivetext.createBy ?length gt 0>
		   create_by = :qywxReceivetext.createBy,
		</#if>
	    <#if qywxReceivetext.createDate ?exists>
		   create_date = :qywxReceivetext.createDate,
		</#if>
	   <#if qywxReceivetext.updateName ?exists && qywxReceivetext.updateName ?length gt 0>
		   update_name = :qywxReceivetext.updateName,
		</#if>
	   <#if qywxReceivetext.updateBy ?exists && qywxReceivetext.updateBy ?length gt 0>
		   update_by = :qywxReceivetext.updateBy,
		</#if>
	    <#if qywxReceivetext.updateDate ?exists>
		   update_date = :qywxReceivetext.updateDate,
		</#if>
WHERE id = :qywxReceivetext.id