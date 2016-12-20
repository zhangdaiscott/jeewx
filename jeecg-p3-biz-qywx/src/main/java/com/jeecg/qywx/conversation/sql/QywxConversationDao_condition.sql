		<#if qywxConversation.title ?exists && qywxConversation.title ?length gt 0>
		    /* TITLE */
			and qc.TITLE like CONCAT('%', :qywxConversation.title ,'%') 
		</#if>
		<#if qywxConversation.usernamelist ?exists && qywxConversation.usernamelist ?length gt 0>
		    /* USERNAMELIST */
			and qc.USERNAMELIST like CONCAT('%', :qywxConversation.usernamelist ,'%') 
		</#if>
		<#if qywxConversation.useridlist ?exists && qywxConversation.useridlist ?length gt 0>
		    /* USERIDLIST */
			and qc.USERIDLIST like CONCAT('%', :qywxConversation.useridlist ,'%') 
		</#if>
		<#if qywxConversation.status ?exists && qywxConversation.status ?length gt 0>
		    /* STATUS */
			and qc.STATUS like CONCAT('%', :qywxConversation.status ,'%') 
		</#if>
		<#if qywxConversation.managerid ?exists && qywxConversation.managerid ?length gt 0>
		    /* MANAGERID */
			and qc.MANAGERID like CONCAT('%', :qywxConversation.managerid ,'%') 
		</#if>
		<#if qywxConversation.managername ?exists && qywxConversation.managername ?length gt 0>
		    /* USERIDLIST */
			and qc.MANAGERNAME like CONCAT('%', :qywxConversation.managername ,'%') 
		</#if>
		