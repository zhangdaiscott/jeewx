		<#if qywxGroup.name ?exists && qywxGroup.name ?length gt 0>
		    /* 部门名称 */
			and qg.name like CONCAT('%', :qywxGroup.name ,'%') 
		</#if>
		<#if qywxGroup.parentid ?exists && qywxGroup.parentid ?length gt 0>
		    /* 上级部门Id */
			and qg.parentid like CONCAT('%', :qywxGroup.parentid ,'%') 
		</#if>
		<#if qywxGroup.accountid ?exists && qywxGroup.accountid ?length gt 0>
		    /* 微信账号ID */
			and qg.accountid like CONCAT('%', :qywxGroup.accountid ,'%') 
		</#if>
		<#if qywxGroup.createName ?exists && qywxGroup.createName ?length gt 0>
		    /* 创建人名称 */
			and qg.create_name like CONCAT('%', :qywxGroup.createName ,'%') 
		</#if>
		<#if qywxGroup.createBy ?exists && qywxGroup.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qg.create_by like CONCAT('%', :qywxGroup.createBy ,'%') 
		</#if>
	    <#if qywxGroup.createDate ?exists>
		    /* 创建日期 */
			and qg.create_date = :qywxGroup.createDate
		</#if>
		<#if qywxGroup.updateName ?exists && qywxGroup.updateName ?length gt 0>
		    /* 更新人名称 */
			and qg.update_name like CONCAT('%', :qywxGroup.updateName ,'%') 
		</#if>
		<#if qywxGroup.updateBy ?exists && qywxGroup.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qg.update_by like CONCAT('%', :qywxGroup.updateBy ,'%') 
		</#if>
	    <#if qywxGroup.updateDate ?exists>
		    /* 更新日期 */
			and qg.update_date = :qywxGroup.updateDate
		</#if>
