UPDATE qywx_menu m 
 <#if qywxMenu.orders ?exists && qywxMenu.orders ?length gt 0>
		  INNER JOIN qywx_menu fm ON m.agent_id = fm.agent_id AND fm.orders = LEFT (:qywxMenu.orders, 1)
 </#if>
SET 
	    <#if qywxMenu.id ?exists>
		   m.id = :qywxMenu.id
		</#if>
	   <#if qywxMenu.agentId ?exists && qywxMenu.agentId ?length gt 0>
		  ,m.agent_id = :qywxMenu.agentId
		</#if>
	   <#if qywxMenu.menuName ?exists && qywxMenu.menuName ?length gt 0>
		  ,m.menu_name = :qywxMenu.menuName
		</#if>
	   <#if qywxMenu.menuType ?exists && qywxMenu.menuType ?length gt 0>
		  ,m.menu_type = :qywxMenu.menuType
		</#if>
	   <#if qywxMenu.menuKey ?exists && qywxMenu.menuKey ?length gt 0>
		  ,m.menu_key = :qywxMenu.menuKey
		</#if>
	   <#if qywxMenu.orders ?exists && qywxMenu.orders ?length gt 0>
		  ,m.orders = :qywxMenu.orders
		  ,m.father_id =IF(CHAR_LENGTH(:qywxMenu.orders)>1,fm.id,null) 
		</#if>
	   <#if qywxMenu.msgType ?exists && qywxMenu.msgType ?length gt 0>
		  ,m.msg_type = :qywxMenu.msgType
		</#if>
	   <#if qywxMenu.templateId ?exists && qywxMenu.templateId ?length gt 0>
		  ,m.template_id = :qywxMenu.templateId
		</#if>
	   <#if qywxMenu.url ?exists && qywxMenu.url ?length gt 0>
		  ,m.url = :qywxMenu.url
		</#if>
	   <#if qywxMenu.createName ?exists && qywxMenu.createName ?length gt 0>
		  ,m.create_name = :qywxMenu.createName
		</#if>
	   <#if qywxMenu.createBy ?exists && qywxMenu.createBy ?length gt 0>
		  ,m.create_by = :qywxMenu.createBy
		</#if>
	    <#if qywxMenu.createDate ?exists>
		   ,m.create_date = :qywxMenu.createDate
		</#if>
	   <#if qywxMenu.updateName ?exists && qywxMenu.updateName ?length gt 0>
		  ,m.update_name = :qywxMenu.updateName
		</#if>
	   <#if qywxMenu.updateBy ?exists && qywxMenu.updateBy ?length gt 0>
		  ,m.update_by = :qywxMenu.updateBy
		</#if>
	    <#if qywxMenu.updateDate ?exists>
		   ,m.update_date = :qywxMenu.updateDate
		</#if>
WHERE m.id = :qywxMenu.id