UPDATE qywx_messagelog
SET 
	   <#if qywxMessagelog.wxAgentId ?exists>
		   wx_agent_id = :qywxMessagelog.wxAgentId,
		</#if>
	   <#if qywxMessagelog.topartysId ?exists>
		   topartys_id = :qywxMessagelog.topartysId,
		</#if>
	   <#if qywxMessagelog.messageType ?exists>
		   message_type = :qywxMessagelog.messageType,
		</#if>
	   <#if qywxMessagelog.messageContent ?exists>
		   message_content = :qywxMessagelog.messageContent,
		</#if>
	   <#if qywxMessagelog.contentId ?exists>
		   content_id = :qywxMessagelog.contentId,
		</#if>
	   <#if qywxMessagelog.receiveMessage ?exists>
		   receive_message = :qywxMessagelog.receiveMessage,
		</#if>
	    <#if qywxMessagelog.createDate ?exists>
		   create_date = :qywxMessagelog.createDate,
		</#if>
WHERE id = :qywxMessagelog.id