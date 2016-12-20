UPDATE alipay_menu m 
SET 
	    <#if alipayMenu.id ?exists>
		   m.id = :alipayMenu.id
		</#if>
	   <#if alipayMenu.menuName ?exists && alipayMenu.menuName ?length gt 0>
		  ,m.menu_name = :alipayMenu.menuName
		</#if>
	   <#if alipayMenu.menuType ?exists && alipayMenu.menuType ?length gt 0>
		  ,m.menu_type = :alipayMenu.menuType
		</#if>
	   <#if alipayMenu.menuKey ?exists && alipayMenu.menuKey ?length gt 0>
		  ,m.menu_key = :alipayMenu.menuKey
		</#if>
	   <#if alipayMenu.orders ?exists && alipayMenu.orders ?length gt 0>
		  ,m.orders = :alipayMenu.orders
		</#if>
		/*update-start--Author:chenchunpeng  Date:20160715 for：修改菜单的父菜单id*/
		  ,m.father_id = :alipayMenu.fatherId
		/* update-end--Author:chenchunpeng  Date:20160715 for：修改菜单的父菜单id*/
	   <#if alipayMenu.msgType ?exists && alipayMenu.msgType ?length gt 0>
		  ,m.msg_type = :alipayMenu.msgType
		</#if>
	   <#if alipayMenu.templateId ?exists && alipayMenu.templateId ?length gt 0>
		  ,m.template_id = :alipayMenu.templateId
		</#if>
	   <#if alipayMenu.url ?exists && alipayMenu.url ?length gt 0>
		  ,m.url = :alipayMenu.url
		</#if>
	   <#if alipayMenu.createName ?exists && alipayMenu.createName ?length gt 0>
		  ,m.create_name = :alipayMenu.createName
		</#if>
	   <#if alipayMenu.createBy ?exists && alipayMenu.createBy ?length gt 0>
		  ,m.create_by = :alipayMenu.createBy
		</#if>
	    <#if alipayMenu.createDate ?exists>
		   ,m.create_date = :alipayMenu.createDate
		</#if>
	   <#if alipayMenu.updateName ?exists && alipayMenu.updateName ?length gt 0>
		  ,m.update_name = :alipayMenu.updateName
		</#if>
	   <#if alipayMenu.updateBy ?exists && alipayMenu.updateBy ?length gt 0>
		  ,m.update_by = :alipayMenu.updateBy
		</#if>
	    <#if alipayMenu.updateDate ?exists>
		   ,m.update_date = :alipayMenu.updateDate
		</#if>
		
WHERE m.id = :alipayMenu.id