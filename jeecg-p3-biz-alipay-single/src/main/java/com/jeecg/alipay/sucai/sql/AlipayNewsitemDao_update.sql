UPDATE alipay_newsitem
SET 
	   <#if alipayNewsitem.title ?exists && alipayNewsitem.title ?length gt 0>
		   title = :alipayNewsitem.title,
		</#if>
	   <#if alipayNewsitem.author ?exists && alipayNewsitem.author ?length gt 0>
		   author = :alipayNewsitem.author,
		</#if>
	   <#if alipayNewsitem.imagePath ?exists && alipayNewsitem.imagePath ?length gt 0>
		   image_path = :alipayNewsitem.imagePath,
		</#if>
	   <#if alipayNewsitem.content ?exists && alipayNewsitem.content ?length gt 0>
		   content = :alipayNewsitem.content,
		</#if>
	   <#if alipayNewsitem.templateid ?exists && alipayNewsitem.templateid ?length gt 0>
		   templateid = :alipayNewsitem.templateid,
		</#if>
	   <#if alipayNewsitem.description ?exists && alipayNewsitem.description ?length gt 0>
		   description = :alipayNewsitem.description,
		</#if>
	   <#if alipayNewsitem.orderNo ?exists && alipayNewsitem.orderNo ?length gt 0>
		   order_no = :alipayNewsitem.orderNo,
		</#if>
	   <#if alipayNewsitem.url ?exists>
		   url = :alipayNewsitem.url,
		</#if>
	   <#if alipayNewsitem.hdid ?exists && alipayNewsitem.hdid ?length gt 0>
		   hdid = :alipayNewsitem.hdid,
		</#if>
	   <#if alipayNewsitem.createName ?exists && alipayNewsitem.createName ?length gt 0>
		   create_name = :alipayNewsitem.createName,
		</#if>
	   <#if alipayNewsitem.createBy ?exists && alipayNewsitem.createBy ?length gt 0>
		   create_by = :alipayNewsitem.createBy,
		</#if>
	    <#if alipayNewsitem.createDate ?exists>
		   create_date = :alipayNewsitem.createDate,
		</#if>
	   <#if alipayNewsitem.updateName ?exists && alipayNewsitem.updateName ?length gt 0>
		   update_name = :alipayNewsitem.updateName,
		</#if>
	   <#if alipayNewsitem.updateBy ?exists && alipayNewsitem.updateBy ?length gt 0>
		   update_by = :alipayNewsitem.updateBy,
		</#if>
	    <#if alipayNewsitem.updateDate ?exists>
		   update_date = :alipayNewsitem.updateDate,
		</#if>
WHERE id = :alipayNewsitem.id