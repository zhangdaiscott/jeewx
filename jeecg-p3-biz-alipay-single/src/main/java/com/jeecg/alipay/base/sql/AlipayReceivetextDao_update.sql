UPDATE alipay_receivetext
SET 
	   <#if alipayReceivetext.tousername ?exists && alipayReceivetext.tousername ?length gt 0>
		   tousername = :alipayReceivetext.tousername,
		</#if>
	   <#if alipayReceivetext.fromusername ?exists && alipayReceivetext.fromusername ?length gt 0>
		   fromusername = :alipayReceivetext.fromusername,
		</#if>
	    <#if alipayReceivetext.createtime ?exists>
		   createtime = :alipayReceivetext.createtime,
		</#if>
	   <#if alipayReceivetext.msgtype ?exists && alipayReceivetext.msgtype ?length gt 0>
		   msgtype = :alipayReceivetext.msgtype,
		</#if>
	   <#if alipayReceivetext.msgid ?exists && alipayReceivetext.msgid ?length gt 0>
		   msgid = :alipayReceivetext.msgid,
		</#if>
	   <#if alipayReceivetext.content ?exists && alipayReceivetext.content ?length gt 0>
		   content = :alipayReceivetext.content,
		</#if>
	   <#if alipayReceivetext.response ?exists && alipayReceivetext.response ?length gt 0>
		   response = :alipayReceivetext.response,
		</#if>
	   <#if alipayReceivetext.rescontent ?exists && alipayReceivetext.rescontent ?length gt 0>
		   rescontent = :alipayReceivetext.rescontent,
		</#if>
	   <#if alipayReceivetext.nickname ?exists && alipayReceivetext.nickname ?length gt 0>
		   nickname = :alipayReceivetext.nickname,
		</#if>
	   <#if alipayReceivetext.accountid ?exists && alipayReceivetext.accountid ?length gt 0>
		   accountid = :alipayReceivetext.accountid,
		</#if>
		<#if alipayReceivetext.agentId ?exists && alipayReceivetext.agentId ?length gt 0>
		   agent_id = :alipayReceivetext.agentId,
		</#if>
	   <#if alipayReceivetext.createName ?exists && alipayReceivetext.createName ?length gt 0>
		   create_name = :alipayReceivetext.createName,
		</#if>
	   <#if alipayReceivetext.createBy ?exists && alipayReceivetext.createBy ?length gt 0>
		   create_by = :alipayReceivetext.createBy,
		</#if>
	    <#if alipayReceivetext.createDate ?exists>
		   create_date = :alipayReceivetext.createDate,
		</#if>
	   <#if alipayReceivetext.updateName ?exists && alipayReceivetext.updateName ?length gt 0>
		   update_name = :alipayReceivetext.updateName,
		</#if>
	   <#if alipayReceivetext.updateBy ?exists && alipayReceivetext.updateBy ?length gt 0>
		   update_by = :alipayReceivetext.updateBy,
		</#if>
	    <#if alipayReceivetext.updateDate ?exists>
		   update_date = :alipayReceivetext.updateDate,
		</#if>
WHERE id = :alipayReceivetext.id