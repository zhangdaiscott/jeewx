		<#if alipayAccount.accontName ?exists && alipayAccount.accontName ?length gt 0>
		    /* 名称 */
			and qa.accont_name like CONCAT('%', :alipayAccount.accontName ,'%') 
		</#if>
		<#if alipayAccount.accountDesc ?exists && alipayAccount.accountDesc ?length gt 0>
		    /* 描述 */
			and qa.account_desc like CONCAT('%', :alipayAccount.accountDesc ,'%') 
		</#if>
		<#if alipayAccount.createName ?exists && alipayAccount.createName ?length gt 0>
		    /* 创建人名称 */
			and qa.create_name like CONCAT('%', :alipayAccount.createName ,'%') 
		</#if>
		<#if alipayAccount.createBy ?exists && alipayAccount.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qa.create_by like CONCAT('%', :alipayAccount.createBy ,'%') 
		</#if>
	    <#if alipayAccount.createDate ?exists>
		    /* 创建日期 */
			and qa.create_date = :alipayAccount.createDate
		</#if>
		<#if alipayAccount.updateName ?exists && alipayAccount.updateName ?length gt 0>
		    /* 更新人名称 */
			and qa.update_name like CONCAT('%', :alipayAccount.updateName ,'%') 
		</#if>
		<#if alipayAccount.updateBy ?exists && alipayAccount.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qa.update_by like CONCAT('%', :alipayAccount.updateBy ,'%') 
		</#if>
	    <#if alipayAccount.updateDate ?exists>
		    /* 更新日期 */
			and qa.update_date = :alipayAccount.updateDate
		</#if>
