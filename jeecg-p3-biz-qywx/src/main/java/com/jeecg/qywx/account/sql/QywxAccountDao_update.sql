UPDATE qywx_account
SET 
		   id = :qywxAccount.id
	   <#if qywxAccount.accontName ?exists && qywxAccount.accontName ?length gt 0>
		  ,accont_name = :qywxAccount.accontName
		</#if>
	   <#if qywxAccount.accountDesc ?exists>
		  ,account_desc = :qywxAccount.accountDesc
		</#if>
	   <#if qywxAccount.accessToken ?exists && qywxAccount.accessToken ?length gt 0>
		  ,access_token = :qywxAccount.accessToken
		</#if>
	   <#if qywxAccount.secret ?exists && qywxAccount.secret ?length gt 0>
		  ,secret = :qywxAccount.secret
		</#if>
		<#if qywxAccount.conversationSecret ?exists && qywxAccount.conversationSecret ?length gt 0>
		  ,conversation_secret = :qywxAccount.conversationSecret
		</#if>
	   <#if qywxAccount.corpid ?exists && qywxAccount.corpid ?length gt 0>
		  ,corpid = :qywxAccount.corpid
		</#if>
	   <#if qywxAccount.createName ?exists && qywxAccount.createName ?length gt 0>
		  ,create_name = :qywxAccount.createName
		</#if>
	   <#if qywxAccount.createBy ?exists && qywxAccount.createBy ?length gt 0>
		  ,create_by = :qywxAccount.createBy
		</#if>
	    <#if qywxAccount.createDate ?exists>
		   ,create_date = :qywxAccount.createDate
		</#if>
	   <#if qywxAccount.updateName ?exists && qywxAccount.updateName ?length gt 0>
		  ,update_name = :qywxAccount.updateName
		</#if>
	   <#if qywxAccount.updateBy ?exists && qywxAccount.updateBy ?length gt 0>
		  ,update_by = :qywxAccount.updateBy
		</#if>
	    <#if qywxAccount.updateDate ?exists>
		   ,update_date = :qywxAccount.updateDate
		</#if>
WHERE id = :qywxAccount.id