		<#if qywxNewsitem.title ?exists && qywxNewsitem.title ?length gt 0>
		    /* 标题 */
			and qn.title like CONCAT('%', :qywxNewsitem.title ,'%') 
		</#if>
		<#if qywxNewsitem.author ?exists && qywxNewsitem.author ?length gt 0>
		    /* 作者 */
			and qn.author like CONCAT('%', :qywxNewsitem.author ,'%') 
		</#if>
		<#if qywxNewsitem.imagePath ?exists && qywxNewsitem.imagePath ?length gt 0>
		    /* 图片路径 */
			and qn.image_path like CONCAT('%', :qywxNewsitem.imagePath ,'%') 
		</#if>
		<#if qywxNewsitem.content ?exists && qywxNewsitem.content ?length gt 0>
		    /* 内容 */
			and qn.content like CONCAT('%', :qywxNewsitem.content ,'%') 
		</#if>
		<#if qywxNewsitem.templateid ?exists && qywxNewsitem.templateid ?length gt 0>
		    /* 图文模板id */
			and qn.templateid like CONCAT('%', :qywxNewsitem.templateid ,'%') 
		</#if>
		<#if qywxNewsitem.description ?exists && qywxNewsitem.description ?length gt 0>
		    /* 摘要 */
			and qn.description like CONCAT('%', :qywxNewsitem.description ,'%') 
		</#if>
		<#if qywxNewsitem.orderNo ?exists && qywxNewsitem.orderNo ?length gt 0>
		    /* 新闻顺序 */
			and qn.order_no like CONCAT('%', :qywxNewsitem.orderNo ,'%') 
		</#if>
		<#if qywxNewsitem.url ?exists && qywxNewsitem.url ?length gt 0>
		    /* 消息内容的url */
			and qn.url like CONCAT('%', :qywxNewsitem.url ,'%') 
		</#if>
		<#if qywxNewsitem.hdid ?exists && qywxNewsitem.hdid ?length gt 0>
		    /* 活动id */
			and qn.hdid like CONCAT('%', :qywxNewsitem.hdid ,'%') 
		</#if>
		<#if qywxNewsitem.createName ?exists && qywxNewsitem.createName ?length gt 0>
		    /* 创建人名称 */
			and qn.create_name like CONCAT('%', :qywxNewsitem.createName ,'%') 
		</#if>
		<#if qywxNewsitem.createBy ?exists && qywxNewsitem.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qn.create_by like CONCAT('%', :qywxNewsitem.createBy ,'%') 
		</#if>
	    <#if qywxNewsitem.createDate ?exists>
		    /* 创建日期 */
			and qn.create_date = :qywxNewsitem.createDate
		</#if>
		<#if qywxNewsitem.updateName ?exists && qywxNewsitem.updateName ?length gt 0>
		    /* 更新人名称 */
			and qn.update_name like CONCAT('%', :qywxNewsitem.updateName ,'%') 
		</#if>
		<#if qywxNewsitem.updateBy ?exists && qywxNewsitem.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qn.update_by like CONCAT('%', :qywxNewsitem.updateBy ,'%') 
		</#if>
	    <#if qywxNewsitem.updateDate ?exists>
		    /* 更新日期 */
			and qn.update_date = :qywxNewsitem.updateDate
		</#if>
