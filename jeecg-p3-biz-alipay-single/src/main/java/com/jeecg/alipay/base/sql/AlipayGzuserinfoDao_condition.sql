		<#if alipayGzuserinfo.userid ?exists && alipayGzuserinfo.userid ?length gt 0>
		    /* 账号 */
			and qg.userid like CONCAT('%', :alipayGzuserinfo.userid ,'%') 
		</#if>
		<#if alipayGzuserinfo.name ?exists && alipayGzuserinfo.name ?length gt 0>
		    /* 姓名 */
			and qg.name like CONCAT('%', :alipayGzuserinfo.name ,'%') 
		</#if>
		<#if alipayGzuserinfo.department ?exists && alipayGzuserinfo.department ?length gt 0>
		    /* 部门 */
			and qg.department like CONCAT('%', :alipayGzuserinfo.department ,'%') 
		</#if>
		<#if alipayGzuserinfo.position ?exists && alipayGzuserinfo.position ?length gt 0>
		    /* 职位 */
			and qg.position like CONCAT('%', :alipayGzuserinfo.position ,'%') 
		</#if>
		<#if alipayGzuserinfo.mobile ?exists && alipayGzuserinfo.mobile ?length gt 0>
		    /* 电话 */
			and qg.mobile like CONCAT('%', :alipayGzuserinfo.mobile ,'%') 
		</#if>
		<#if alipayGzuserinfo.province ?exists && alipayGzuserinfo.province ?length gt 0>
		    /* 省份 */
			and qg.province like CONCAT('%', :alipayGzuserinfo.province ,'%') 
		</#if>
		<#if alipayGzuserinfo.gender ?exists && alipayGzuserinfo.gender ?length gt 0>
		    /* 性别gender=1表示男，=2表示女 */
			and qg.gender like CONCAT('%', :alipayGzuserinfo.gender ,'%') 
		</#if>
		<#if alipayGzuserinfo.email ?exists && alipayGzuserinfo.email ?length gt 0>
		    /* 邮箱 */
			and qg.email like CONCAT('%', :alipayGzuserinfo.email ,'%') 
		</#if>
		<#if alipayGzuserinfo.weixinid ?exists && alipayGzuserinfo.weixinid ?length gt 0>
		    /* 微信号 */
			and qg.weixinid like CONCAT('%', :alipayGzuserinfo.weixinid ,'%') 
		</#if>
		<#if alipayGzuserinfo.avatar ?exists && alipayGzuserinfo.avatar ?length gt 0>
		    /* 头像url */
			and qg.avatar like CONCAT('%', :alipayGzuserinfo.avatar ,'%') 
		</#if>
		<#if alipayGzuserinfo.subscribeStatus ?exists && alipayGzuserinfo.subscribeStatus ?length gt 0>
		    /* 关注状态: 1=已关注，2=已冻结，4=未关注 */
			and qg.subscribe_status like CONCAT('%', :alipayGzuserinfo.subscribeStatus ,'%') 
		</#if>
	    <#if alipayGzuserinfo.subscribeTime ?exists>
		    /* 关注时间 */
			and qg.subscribe_time = :alipayGzuserinfo.subscribeTime
		</#if>
		<#if alipayGzuserinfo.accountid ?exists && alipayGzuserinfo.accountid ?length gt 0>
		    /* 微信账号ID */
			and qg.accountid like CONCAT('%', :alipayGzuserinfo.accountid ,'%') 
		</#if>
		<#if alipayGzuserinfo.createName ?exists && alipayGzuserinfo.createName ?length gt 0>
		    /* 创建人名称 */
			and qg.create_name like CONCAT('%', :alipayGzuserinfo.createName ,'%') 
		</#if>
		<#if alipayGzuserinfo.createBy ?exists && alipayGzuserinfo.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qg.create_by like CONCAT('%', :alipayGzuserinfo.createBy ,'%') 
		</#if>
	    <#if alipayGzuserinfo.createDate ?exists>
		    /* 创建日期 */
			and qg.create_date = :alipayGzuserinfo.createDate
		</#if>
		<#if alipayGzuserinfo.updateName ?exists && alipayGzuserinfo.updateName ?length gt 0>
		    /* 更新人名称 */
			and qg.update_name like CONCAT('%', :alipayGzuserinfo.updateName ,'%') 
		</#if>
		<#if alipayGzuserinfo.updateBy ?exists && alipayGzuserinfo.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qg.update_by like CONCAT('%', :alipayGzuserinfo.updateBy ,'%') 
		</#if>
	    <#if alipayGzuserinfo.updateDate ?exists>
		    /* 更新日期 */
			and qg.update_date = :alipayGzuserinfo.updateDate
		</#if>
