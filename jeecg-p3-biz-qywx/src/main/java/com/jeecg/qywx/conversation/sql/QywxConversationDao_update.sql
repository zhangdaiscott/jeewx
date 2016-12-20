UPDATE qywx_conversation
SET 
	   <#if qywxConversation.title ?exists>
		   TITLE = :qywxConversation.title,
		</#if>
	   <#if qywxConversation.usernamelist ?exists>
		   USERNAMELIST = :qywxConversation.usernamelist,
		</#if>
	   <#if qywxConversation.useridlist ?exists>
		   USERIDLIST = :qywxConversation.useridlist,
		</#if>
	   <#if qywxConversation.status ?exists>
		   STATUS = :qywxConversation.status,
		</#if>
	   <#if qywxConversation.managerid ?exists>
		   MANAGERID = :qywxConversation.managerid,
		</#if>
		 <#if qywxConversation.managername ?exists>
		   MANAGERNAME = :qywxConversation.managername,
		</#if>
		 <#if qywxConversation.chatid ?exists>
		   CHATID = :qywxConversation.chatid,
		</#if>
WHERE id = :qywxConversation.id