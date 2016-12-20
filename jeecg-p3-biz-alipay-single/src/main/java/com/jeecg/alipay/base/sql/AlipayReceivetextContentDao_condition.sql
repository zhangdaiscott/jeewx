		<#if alipayReceivetextContent.receivetextId ?exists && alipayReceivetextContent.receivetextId ?length gt 0>
		    /* 用户消息表 id */
			and arc.receivetext_id like CONCAT(:alipayReceivetextContent.receivetextId ,'%') 
		</#if>
		<#if alipayReceivetextContent.content ?exists && alipayReceivetextContent.content ?length gt 0>
		    /* 消息内容 */
			and arc.content like CONCAT('%', :alipayReceivetextContent.content ,'%') 
		</#if>
		<#if alipayReceivetextContent.createName ?exists && alipayReceivetextContent.createName ?length gt 0>
		    /* 创建人名称 */
			and arc.create_name like CONCAT('%', :alipayReceivetextContent.createName ,'%') 
		</#if>
		<#if alipayReceivetextContent.createBy ?exists && alipayReceivetextContent.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and arc.create_by like CONCAT('%', :alipayReceivetextContent.createBy ,'%') 
		</#if>
	    <#if alipayReceivetextContent.createDate ?exists>
		    /* 创建日期 */
			and arc.create_date = :alipayReceivetextContent.createDate
		</#if>
		<#if alipayReceivetextContent.updateName ?exists && alipayReceivetextContent.updateName ?length gt 0>
		    /* 更新人名称 */
			and arc.update_name like CONCAT('%', :alipayReceivetextContent.updateName ,'%') 
		</#if>
		<#if alipayReceivetextContent.updateBy ?exists && alipayReceivetextContent.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and arc.update_by like CONCAT('%', :alipayReceivetextContent.updateBy ,'%') 
		</#if>
	    <#if alipayReceivetextContent.updateDate ?exists>
		    /* 更新日期 */
			and arc.update_date = :alipayReceivetextContent.updateDate
		</#if>
