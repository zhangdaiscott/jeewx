		<#if alipayNewsitem.title ?exists && alipayNewsitem.title ?length gt 0>
		    /* 标题 */
			and qn.title like CONCAT('%', :alipayNewsitem.title ,'%') 
		</#if>
		<#if alipayNewsitem.author ?exists && alipayNewsitem.author ?length gt 0>
		    /* 作者 */
			and qn.author like CONCAT('%', :alipayNewsitem.author ,'%') 
		</#if>
		<#if alipayNewsitem.imagePath ?exists && alipayNewsitem.imagePath ?length gt 0>
		    /* 图片路径 */
			and qn.image_path like CONCAT('%', :alipayNewsitem.imagePath ,'%') 
		</#if>
		<#if alipayNewsitem.content ?exists && alipayNewsitem.content ?length gt 0>
		    /* 内容 */
			and qn.content like CONCAT('%', :alipayNewsitem.content ,'%') 
		</#if>
		<#if alipayNewsitem.templateid ?exists && alipayNewsitem.templateid ?length gt 0>
		    /* 图文模板id */
			and qn.templateid like CONCAT('%', :alipayNewsitem.templateid ,'%') 
		</#if>
		<#if alipayNewsitem.description ?exists && alipayNewsitem.description ?length gt 0>
		    /* 摘要 */
			and qn.description like CONCAT('%', :alipayNewsitem.description ,'%') 
		</#if>
		<#if alipayNewsitem.orderNo ?exists && alipayNewsitem.orderNo ?length gt 0>
		    /* 新闻顺序 */
			and qn.order_no like CONCAT('%', :alipayNewsitem.orderNo ,'%') 
		</#if>
		<#if alipayNewsitem.url ?exists && alipayNewsitem.url ?length gt 0>
		    /* 消息内容的url */
			and qn.url like CONCAT('%', :alipayNewsitem.url ,'%') 
		</#if>
		<#if alipayNewsitem.hdid ?exists && alipayNewsitem.hdid ?length gt 0>
		    /* 活动id */
			and qn.hdid like CONCAT('%', :alipayNewsitem.hdid ,'%') 
		</#if>
		<#if alipayNewsitem.createName ?exists && alipayNewsitem.createName ?length gt 0>
		    /* 创建人名称 */
			and qn.create_name like CONCAT('%', :alipayNewsitem.createName ,'%') 
		</#if>
		<#if alipayNewsitem.createBy ?exists && alipayNewsitem.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qn.create_by like CONCAT('%', :alipayNewsitem.createBy ,'%') 
		</#if>
	    <#if alipayNewsitem.createDate ?exists>
		    /* 创建日期 */
			and qn.create_date = :alipayNewsitem.createDate
		</#if>
		<#if alipayNewsitem.updateName ?exists && alipayNewsitem.updateName ?length gt 0>
		    /* 更新人名称 */
			and qn.update_name like CONCAT('%', :alipayNewsitem.updateName ,'%') 
		</#if>
		<#if alipayNewsitem.updateBy ?exists && alipayNewsitem.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qn.update_by like CONCAT('%', :alipayNewsitem.updateBy ,'%') 
		</#if>
	    <#if alipayNewsitem.updateDate ?exists>
		    /* 更新日期 */
			and qn.update_date = :alipayNewsitem.updateDate
		</#if>
