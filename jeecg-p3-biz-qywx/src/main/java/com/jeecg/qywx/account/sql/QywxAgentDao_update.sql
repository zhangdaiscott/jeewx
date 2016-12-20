UPDATE qywx_agent
SET 
	    <#if qywxAgent.id ?exists>
		   id = :qywxAgent.id
		</#if>
	   <#if qywxAgent.accountId ?exists>
		  ,account_id = :qywxAgent.accountId
		</#if>
	   <#if qywxAgent.wxAgentid ?exists && qywxAgent.wxAgentid ?length gt 0>
		  ,wx_agentid = :qywxAgent.wxAgentid
		</#if>
	   <#if qywxAgent.agentName ?exists && qywxAgent.agentName ?length gt 0>
		  ,agent_name = :qywxAgent.agentName
		</#if>
		 <#if qywxAgent.token ?exists && qywxAgent.token ?length gt 0>
		  ,token = :qywxAgent.token
		</#if>
		 <#if qywxAgent.encodingAESKey ?exists && qywxAgent.encodingAESKey ?length gt 0>
		  ,encodingAESKey = :qywxAgent.encodingAESKey
		</#if>
	   <#if qywxAgent.squareLogoUrl ?exists && qywxAgent.squareLogoUrl ?length gt 0>
		  ,square_logo_url = :qywxAgent.squareLogoUrl
		</#if>
	   <#if qywxAgent.roundLogoUrl ?exists && qywxAgent.roundLogoUrl ?length gt 0>
		  ,round_logo_url = :qywxAgent.roundLogoUrl
		</#if>
	   <#if qywxAgent.description ?exists>
		  ,description = :qywxAgent.description
		</#if>
	   <#if qywxAgent.closeStatus ?exists>
		  ,close_status = :qywxAgent.closeStatus
		</#if>
	   <#if qywxAgent.redirectDomain ?exists>
		  ,redirect_domain = :qywxAgent.redirectDomain
		</#if>
	   <#if qywxAgent.reportLocationFlag ?exists>
		  ,report_location_flag = :qywxAgent.reportLocationFlag
		</#if>
	   <#if qywxAgent.isreportuser ?exists>
		  ,isreportuser = :qywxAgent.isreportuser
		</#if>
	   <#if qywxAgent.isreportenter ?exists>
		  ,isreportenter = :qywxAgent.isreportenter
		</#if>
	   <#if qywxAgent.appType ?exists>
		  ,app_type = :qywxAgent.appType
		</#if>
	   <#if qywxAgent.createName ?exists>
		  ,create_name = :qywxAgent.createName
		</#if>
	   <#if qywxAgent.createBy ?exists>
		  ,create_by = :qywxAgent.createBy
		</#if>
	    <#if qywxAgent.createDate ?exists>
		   ,create_date = :qywxAgent.createDate
		</#if>
	   <#if qywxAgent.updateName ?exists>
		  ,update_name = :qywxAgent.updateName
		</#if>
	   <#if qywxAgent.updateBy ?exists>
		  ,update_by = :qywxAgent.updateBy
		</#if>
	    <#if qywxAgent.updateDate ?exists>
		   ,update_date = :qywxAgent.updateDate
		</#if>
WHERE id = :qywxAgent.id