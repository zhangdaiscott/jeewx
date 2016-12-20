UPDATE qywx_group
SET 
	   <#if qywxGroup.name ?exists && qywxGroup.name ?length gt 0>
		   name = :qywxGroup.name,
		</#if>
	   <#if qywxGroup.parentid ?exists && qywxGroup.parentid ?length gt 0>
		   parentid = :qywxGroup.parentid,
		</#if>
	   <#if qywxGroup.accountid ?exists && qywxGroup.accountid ?length gt 0>
		   accountid = :qywxGroup.accountid,
		</#if>
	   <#if qywxGroup.createName ?exists && qywxGroup.createName ?length gt 0>
		   create_name = :qywxGroup.createName,
		</#if>
	   <#if qywxGroup.createBy ?exists && qywxGroup.createBy ?length gt 0>
		   create_by = :qywxGroup.createBy,
		</#if>
	    <#if qywxGroup.createDate ?exists>
		   create_date = :qywxGroup.createDate,
		</#if>
		<#if qywxGroup.orders ?exists>
		   orders = :qywxGroup.orders,
		</#if>
	   <#if qywxGroup.updateName ?exists && qywxGroup.updateName ?length gt 0>
		   update_name = :qywxGroup.updateName,
		</#if>
	   <#if qywxGroup.updateBy ?exists && qywxGroup.updateBy ?length gt 0>
		   update_by = :qywxGroup.updateBy,
		</#if>
	    <#if qywxGroup.updateDate ?exists>
		   update_date = :qywxGroup.updateDate,
		</#if>
WHERE id = :qywxGroup.id