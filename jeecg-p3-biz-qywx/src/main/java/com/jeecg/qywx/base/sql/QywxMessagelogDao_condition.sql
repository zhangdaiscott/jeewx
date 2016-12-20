		<#if qywxMessagelog.wxAgentId ?exists && qywxMessagelog.wxAgentId ?length gt 0>
		    /* 应用id */
			and qm.wx_agent_id like CONCAT('%', :qywxMessagelog.wxAgentId ,'%') 
		</#if>
		<#if qywxMessagelog.topartysId ?exists && qywxMessagelog.topartysId ?length gt 0>
		    /* 部门id */
			and qm.topartys_id like CONCAT('%', :qywxMessagelog.topartysId ,'%') 
		</#if>
		<#if qywxMessagelog.messageType ?exists && qywxMessagelog.messageType ?length gt 0>
		    /* 消息类型 */
			and qm.message_type like CONCAT('%', :qywxMessagelog.messageType ,'%') 
		</#if>
		<#if qywxMessagelog.messageContent ?exists && qywxMessagelog.messageContent ?length gt 0>
		    /* 文本内容 */
			and qm.message_content like CONCAT('%', :qywxMessagelog.messageContent ,'%') 
		</#if>
		<#if qywxMessagelog.contentId ?exists && qywxMessagelog.contentId ?length gt 0>
		    /* 内容id */
			and qm.content_id like CONCAT('%', :qywxMessagelog.contentId ,'%') 
		</#if>
		<#if qywxMessagelog.receiveMessage ?exists && qywxMessagelog.receiveMessage ?length gt 0>
		    /* 消息状态 */
			and qm.receive_message like CONCAT('%', :qywxMessagelog.receiveMessage ,'%') 
		</#if>
	    <#if qywxMessagelog.createDate ?exists>
		    /* 创建时间 */
			and qm.create_date = :qywxMessagelog.createDate
		</#if>
