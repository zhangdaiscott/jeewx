UPDATE alipay_messagelog
SET 
	   <#if alipayMessagelog.wxAgentId ?exists>
		   wx_agent_id = :alipayMessagelog.wxAgentId,
		</#if>
	   <#if alipayMessagelog.topartysId ?exists>
		   topartys_id = :alipayMessagelog.topartysId,
		</#if>
	   <#if alipayMessagelog.messageType ?exists>
		   message_type = :alipayMessagelog.messageType,
		</#if>
	   <#if alipayMessagelog.messageContent ?exists>
		   message_content = :alipayMessagelog.messageContent,
		</#if>
	   <#if alipayMessagelog.contentId ?exists>
		   content_id = :alipayMessagelog.contentId,
		</#if>
	   <#if alipayMessagelog.receiveMessage ?exists>
		   receive_message = :alipayMessagelog.receiveMessage,
		</#if>
	    <#if alipayMessagelog.createDate ?exists>
		   create_date = :alipayMessagelog.createDate,
		</#if>
WHERE id = :alipayMessagelog.id