		<#if qywxAutoresponse.keyWord ?exists && qywxAutoresponse.keyWord ?length gt 0>
		    /* 关键字 */
			and qa.key_word like CONCAT('%', :qywxAutoresponse.keyWord ,'%') 
		</#if>
		<#if qywxAutoresponse.resContent ?exists && qywxAutoresponse.resContent ?length gt 0>
		    /* 回复内容 */
			and qa.res_content like CONCAT('%', :qywxAutoresponse.resContent ,'%') 
		</#if>
		<#if qywxAutoresponse.msgType ?exists && qywxAutoresponse.msgType ?length gt 0>
		    /* 消息类型 */
			and qa.msg_type like CONCAT('%', :qywxAutoresponse.msgType ,'%') 
		</#if>
		<#if qywxAutoresponse.templateName ?exists && qywxAutoresponse.templateName ?length gt 0>
		    /* 模板名称 */
			and qa.template_name like CONCAT('%', :qywxAutoresponse.templateName ,'%') 
		</#if>
		<#if qywxAutoresponse.accountid ?exists && qywxAutoresponse.accountid ?length gt 0>
		    /* 微信账号id */
			and qa.accountid like CONCAT('%', :qywxAutoresponse.accountid ,'%') 
		</#if>
		<#if qywxAutoresponse.createName ?exists && qywxAutoresponse.createName ?length gt 0>
		    /* 创建人名称 */
			and qa.create_name like CONCAT('%', :qywxAutoresponse.createName ,'%') 
		</#if>
		<#if qywxAutoresponse.createBy ?exists && qywxAutoresponse.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qa.create_by like CONCAT('%', :qywxAutoresponse.createBy ,'%') 
		</#if>
	    <#if qywxAutoresponse.createDate ?exists>
		    /* 创建日期 */
			and qa.create_date = :qywxAutoresponse.createDate
		</#if>
		<#if qywxAutoresponse.updateName ?exists && qywxAutoresponse.updateName ?length gt 0>
		    /* 更新人名称 */
			and qa.update_name like CONCAT('%', :qywxAutoresponse.updateName ,'%') 
		</#if>
		<#if qywxAutoresponse.updateBy ?exists && qywxAutoresponse.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qa.update_by like CONCAT('%', :qywxAutoresponse.updateBy ,'%') 
		</#if>
	    <#if qywxAutoresponse.updateDate ?exists>
		    /* 更新日期 */
			and qa.update_date = :qywxAutoresponse.updateDate
		</#if>
