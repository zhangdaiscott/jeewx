UPDATE qywx_newsitem
SET 
	   <#if qywxNewsitem.title ?exists && qywxNewsitem.title ?length gt 0>
		   title = :qywxNewsitem.title,
		</#if>
	   <#if qywxNewsitem.author ?exists && qywxNewsitem.author ?length gt 0>
		   author = :qywxNewsitem.author,
		</#if>
	   <#if qywxNewsitem.imagePath ?exists && qywxNewsitem.imagePath ?length gt 0>
		   image_path = :qywxNewsitem.imagePath,
		</#if>
	   <#if qywxNewsitem.content ?exists && qywxNewsitem.content ?length gt 0>
		   content = :qywxNewsitem.content,
		</#if>
	   <#if qywxNewsitem.templateid ?exists && qywxNewsitem.templateid ?length gt 0>
		   templateid = :qywxNewsitem.templateid,
		</#if>
	   <#if qywxNewsitem.description ?exists && qywxNewsitem.description ?length gt 0>
		   description = :qywxNewsitem.description,
		</#if>
	   <#if qywxNewsitem.orderNo ?exists && qywxNewsitem.orderNo ?length gt 0>
		   order_no = :qywxNewsitem.orderNo,
		</#if>
	   <#if qywxNewsitem.url ?exists>
		   url = :qywxNewsitem.url,
		</#if>
	   <#if qywxNewsitem.hdid ?exists && qywxNewsitem.hdid ?length gt 0>
		   hdid = :qywxNewsitem.hdid,
		</#if>
	   <#if qywxNewsitem.createName ?exists && qywxNewsitem.createName ?length gt 0>
		   create_name = :qywxNewsitem.createName,
		</#if>
	   <#if qywxNewsitem.createBy ?exists && qywxNewsitem.createBy ?length gt 0>
		   create_by = :qywxNewsitem.createBy,
		</#if>
	    <#if qywxNewsitem.createDate ?exists>
		   create_date = :qywxNewsitem.createDate,
		</#if>
	   <#if qywxNewsitem.updateName ?exists && qywxNewsitem.updateName ?length gt 0>
		   update_name = :qywxNewsitem.updateName,
		</#if>
	   <#if qywxNewsitem.updateBy ?exists && qywxNewsitem.updateBy ?length gt 0>
		   update_by = :qywxNewsitem.updateBy,
		</#if>
	    <#if qywxNewsitem.updateDate ?exists>
		   update_date = :qywxNewsitem.updateDate,
		</#if>
WHERE id = :qywxNewsitem.id