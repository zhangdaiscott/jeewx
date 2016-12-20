		<#if alipayMessagelog.wxAgentId ?exists && alipayMessagelog.wxAgentId ?length gt 0>
		    /* 应用id */
			and qm.wx_agent_id like CONCAT('%', :alipayMessagelog.wxAgentId ,'%') 
		</#if>
		<#if alipayMessagelog.topartysId ?exists && alipayMessagelog.topartysId ?length gt 0>
		    /* 部门id */
			and qm.topartys_id like CONCAT('%', :alipayMessagelog.topartysId ,'%') 
		</#if>
		<#if alipayMessagelog.messageType ?exists && alipayMessagelog.messageType ?length gt 0>
		    /* 消息类型 */
			and qm.message_type like CONCAT('%', :alipayMessagelog.messageType ,'%') 
		</#if>
		<#if alipayMessagelog.messageContent ?exists && alipayMessagelog.messageContent ?length gt 0>
		    /* 文本内容 */
			and qm.message_content like CONCAT('%', :alipayMessagelog.messageContent ,'%') 
		</#if>
		<#if alipayMessagelog.contentId ?exists && alipayMessagelog.contentId ?length gt 0>
		    /* 内容id */
			and qm.content_id like CONCAT('%', :alipayMessagelog.contentId ,'%') 
		</#if>
		<#if alipayMessagelog.receiveMessage ?exists && alipayMessagelog.receiveMessage ?length gt 0>
		    /* 消息状态 */
			and qm.receive_message like CONCAT('%', :alipayMessagelog.receiveMessage ,'%') 
		</#if>
	    <#if alipayMessagelog.createDate ?exists>
		    /* 创建时间 */
			and qm.create_date = :alipayMessagelog.createDate
		</#if>
		/*update-start--Author:chenchunpeng  Date:20160715 for：根据时间查询群发消息记录*/
		 <#if alipayMessagelog.startDate ?exists>
		    /* 查询条件：开始时间 */
			and qm.create_date >= :alipayMessagelog.startDate
		</#if>
		 <#if alipayMessagelog.endDate ?exists>
		    /* 查询条件:结束时间 */
			and qm.create_date <:alipayMessagelog.endDate
		</#if>
		/*update-start--Author:chenchunpeng  Date:20160715 for：根据时间查询群发消息记录*/
