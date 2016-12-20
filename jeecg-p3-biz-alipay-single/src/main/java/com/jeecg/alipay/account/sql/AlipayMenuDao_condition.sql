		<#if alipayMenu.menuName ?exists && alipayMenu.menuName ?length gt 0>
		    /* 菜单标题 */
			and qm.menu_name like CONCAT('%', :alipayMenu.menuName ,'%') 
		</#if>
		<#if alipayMenu.menuType ?exists && alipayMenu.menuType ?length gt 0>
		    /* 菜单类型 */
			and qm.menu_type like CONCAT('%', :alipayMenu.menuType ,'%') 
		</#if>
		<#if alipayMenu.menuKey ?exists && alipayMenu.menuKey ?length gt 0>
		    /* 菜单KEY */
			and qm.menu_key like CONCAT('%', :alipayMenu.menuKey ,'%') 
		</#if>
		<#if alipayMenu.orders ?exists && alipayMenu.orders ?length gt 0>
		    /* 菜单位置 */
			and qm.orders like CONCAT('%', :alipayMenu.orders ,'%') 
		</#if>
		<#if alipayMenu.msgType ?exists && alipayMenu.msgType ?length gt 0>
		    /* 响应消息类型 */
			and qm.msg_type like CONCAT('%', :alipayMenu.msgType ,'%') 
		</#if>
		<#if alipayMenu.templateId ?exists && alipayMenu.templateId ?length gt 0>
		    /* 关联素材ID */
			and qm.template_id like CONCAT('%', :alipayMenu.templateId ,'%') 
		</#if>
		<#if alipayMenu.url ?exists && alipayMenu.url ?length gt 0>
		    /* 网页链接 */
			and qm.url like CONCAT('%', :alipayMenu.url ,'%') 
		</#if>
		<#if alipayMenu.fatherId ?exists && alipayMenu.fatherId ?length gt 0>
		    /* 父ID */
			and qm.father_id like CONCAT('%', :alipayMenu.fatherId ,'%') 
		</#if>
		<#if alipayMenu.createName ?exists && alipayMenu.createName ?length gt 0>
		    /* 创建人名称 */
			and qm.create_name like CONCAT('%', :alipayMenu.createName ,'%') 
		</#if>
		<#if alipayMenu.createBy ?exists && alipayMenu.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qm.create_by like CONCAT('%', :alipayMenu.createBy ,'%') 
		</#if>
	    <#if alipayMenu.createDate ?exists>
		    /* 创建日期 */
			and qm.create_date = :alipayMenu.createDate
		</#if>
		<#if alipayMenu.updateName ?exists && alipayMenu.updateName ?length gt 0>
		    /* 更新人名称 */
			and qm.update_name like CONCAT('%', :alipayMenu.updateName ,'%') 
		</#if>
		<#if alipayMenu.updateBy ?exists && alipayMenu.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qm.update_by like CONCAT('%', :alipayMenu.updateBy ,'%') 
		</#if>
	    <#if alipayMenu.updateDate ?exists>
		    /* 更新日期 */
			and qm.update_date = :alipayMenu.updateDate
		</#if>
		
