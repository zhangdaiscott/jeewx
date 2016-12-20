		<#if qywxGzentity.templateName ?exists && qywxGzentity.templateName ?length gt 0>
		    /* 模板名称 */
			and qg.template_name like CONCAT('%', :qywxGzentity.templateName ,'%') 
		</#if>
		<#if qywxGzentity.templateId ?exists && qywxGzentity.templateId ?length gt 0>
		    /* 模板id */
			and qg.template_id like CONCAT('%', :qywxGzentity.templateId ,'%') 
		</#if>
		<#if qywxGzentity.templateType ?exists && qywxGzentity.templateType ?length gt 0>
		    /* 类型 文本_text,图文_news */
			and qg.template_type like CONCAT('%', :qywxGzentity.templateType ,'%') 
		</#if>
		<#if qywxGzentity.isWork ?exists && qywxGzentity.isWork ?length gt 0>
		    /* 是否启用 未启用_0,启用_1 */
			and qg.is_work like CONCAT('%', :qywxGzentity.isWork ,'%') 
		</#if>
		<#if qywxGzentity.accountid ?exists && qywxGzentity.accountid ?length gt 0>
		    /* 微信账号id */
			and qg.accountid like CONCAT('%', :qywxGzentity.accountid ,'%') 
		</#if>
		<#if qywxGzentity.createName ?exists && qywxGzentity.createName ?length gt 0>
		    /* 创建人名称 */
			and qg.create_name like CONCAT('%', :qywxGzentity.createName ,'%') 
		</#if>
		<#if qywxGzentity.createBy ?exists && qywxGzentity.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qg.create_by like CONCAT('%', :qywxGzentity.createBy ,'%') 
		</#if>
	    <#if qywxGzentity.createDate ?exists>
		    /* 创建日期 */
			and qg.create_date = :qywxGzentity.createDate
		</#if>
		<#if qywxGzentity.updateName ?exists && qywxGzentity.updateName ?length gt 0>
		    /* 更新人名称 */
			and qg.update_name like CONCAT('%', :qywxGzentity.updateName ,'%') 
		</#if>
		<#if qywxGzentity.updateBy ?exists && qywxGzentity.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qg.update_by like CONCAT('%', :qywxGzentity.updateBy ,'%') 
		</#if>
	    <#if qywxGzentity.updateDate ?exists>
		    /* 更新日期 */
			and qg.update_date = :qywxGzentity.updateDate
		</#if>
