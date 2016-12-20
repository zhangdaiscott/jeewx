		<#if qywxAccount.accontName ?exists && qywxAccount.accontName ?length gt 0>
		    /* 名称 */
			and qa.accont_name like CONCAT('%', :qywxAccount.accontName ,'%') 
		</#if>
		<#if qywxAccount.accountDesc ?exists && qywxAccount.accountDesc ?length gt 0>
		    /* 描述 */
			and qa.account_desc like CONCAT('%', :qywxAccount.accountDesc ,'%') 
		</#if>
		<#if qywxAccount.accessToken ?exists && qywxAccount.accessToken ?length gt 0>
		    /* AccessToken */
			and qa.access_token like CONCAT('%', :qywxAccount.accessToken ,'%') 
		</#if>
		<#if qywxAccount.secret ?exists && qywxAccount.secret ?length gt 0>
		    /* 管理组凭证密钥 */
			and qa.secret like CONCAT('%', :qywxAccount.secret ,'%') 
		</#if>
		<#if qywxAccount.corpid ?exists && qywxAccount.corpid ?length gt 0>
		    /* 企业号标识 */
			and qa.corpid like CONCAT('%', :qywxAccount.corpid ,'%') 
		</#if>
		<#if qywxAccount.createName ?exists && qywxAccount.createName ?length gt 0>
		    /* 创建人名称 */
			and qa.create_name like CONCAT('%', :qywxAccount.createName ,'%') 
		</#if>
		<#if qywxAccount.createBy ?exists && qywxAccount.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qa.create_by like CONCAT('%', :qywxAccount.createBy ,'%') 
		</#if>
	    <#if qywxAccount.createDate ?exists>
		    /* 创建日期 */
			and qa.create_date = :qywxAccount.createDate
		</#if>
		<#if qywxAccount.updateName ?exists && qywxAccount.updateName ?length gt 0>
		    /* 更新人名称 */
			and qa.update_name like CONCAT('%', :qywxAccount.updateName ,'%') 
		</#if>
		<#if qywxAccount.updateBy ?exists && qywxAccount.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qa.update_by like CONCAT('%', :qywxAccount.updateBy ,'%') 
		</#if>
	    <#if qywxAccount.updateDate ?exists>
		    /* 更新日期 */
			and qa.update_date = :qywxAccount.updateDate
		</#if>
