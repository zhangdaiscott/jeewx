		<#if alipayGzentity.templateName ?exists && alipayGzentity.templateName ?length gt 0>
		    /* 模板名称 */
			and qg.template_name like CONCAT('%', :alipayGzentity.templateName ,'%') 
		</#if>
		<#if alipayGzentity.templateId ?exists && alipayGzentity.templateId ?length gt 0>
		    /* 模板id */
			and qg.template_id like CONCAT('%', :alipayGzentity.templateId ,'%') 
		</#if>
		<#if alipayGzentity.templateType ?exists && alipayGzentity.templateType ?length gt 0>
		    /* 类型 文本_text,图文_news */
			and qg.template_type like CONCAT('%', :alipayGzentity.templateType ,'%') 
		</#if>
		<#if alipayGzentity.isWork ?exists && alipayGzentity.isWork ?length gt 0>
		    /* 是否启用 未启用_0,启用_1 */
			and qg.is_work like CONCAT('%', :alipayGzentity.isWork ,'%') 
		</#if>
		<#if alipayGzentity.accountid ?exists && alipayGzentity.accountid ?length gt 0>
		    /* 微信账号id */
			and qg.accountid like CONCAT('%', :alipayGzentity.accountid ,'%') 
		</#if>
		<#if alipayGzentity.createName ?exists && alipayGzentity.createName ?length gt 0>
		    /* 创建人名称 */
			and qg.create_name like CONCAT('%', :alipayGzentity.createName ,'%') 
		</#if>
		<#if alipayGzentity.createBy ?exists && alipayGzentity.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qg.create_by like CONCAT('%', :alipayGzentity.createBy ,'%') 
		</#if>
	    <#if alipayGzentity.createDate ?exists>
		    /* 创建日期 */
			and qg.create_date = :alipayGzentity.createDate
		</#if>
		<#if alipayGzentity.updateName ?exists && alipayGzentity.updateName ?length gt 0>
		    /* 更新人名称 */
			and qg.update_name like CONCAT('%', :alipayGzentity.updateName ,'%') 
		</#if>
		<#if alipayGzentity.updateBy ?exists && alipayGzentity.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qg.update_by like CONCAT('%', :alipayGzentity.updateBy ,'%') 
		</#if>
	    <#if alipayGzentity.updateDate ?exists>
		    /* 更新日期 */
			and qg.update_date = :alipayGzentity.updateDate
		</#if>
