		<#if qywxGzuserinfo.userid ?exists && qywxGzuserinfo.userid ?length gt 0>
		    /* 账号 */
			and qg.userid like CONCAT('%', :qywxGzuserinfo.userid ,'%') 
		</#if>
		<#if qywxGzuserinfo.name ?exists && qywxGzuserinfo.name ?length gt 0>
		    /* 姓名 */
			and qg.name like CONCAT('%', :qywxGzuserinfo.name ,'%') 
		</#if>
		<#if qywxGzuserinfo.department ?exists && qywxGzuserinfo.department ?length gt 0>
		    /* 部门 */
			and qg.department like CONCAT('%', :qywxGzuserinfo.department ,'%') 
		</#if>
		<#if qywxGzuserinfo.position ?exists && qywxGzuserinfo.position ?length gt 0>
		    /* 职位 */
			and qg.position like CONCAT('%', :qywxGzuserinfo.position ,'%') 
		</#if>
		<#if qywxGzuserinfo.mobile ?exists && qywxGzuserinfo.mobile ?length gt 0>
		    /* 电话 */
			and qg.mobile like CONCAT('%', :qywxGzuserinfo.mobile ,'%') 
		</#if>
		<#if qywxGzuserinfo.province ?exists && qywxGzuserinfo.province ?length gt 0>
		    /* 省份 */
			and qg.province like CONCAT('%', :qywxGzuserinfo.province ,'%') 
		</#if>
		<#if qywxGzuserinfo.gender ?exists && qywxGzuserinfo.gender ?length gt 0>
		    /* 性别gender=1表示男，=2表示女 */
			and qg.gender like CONCAT('%', :qywxGzuserinfo.gender ,'%') 
		</#if>
		<#if qywxGzuserinfo.email ?exists && qywxGzuserinfo.email ?length gt 0>
		    /* 邮箱 */
			and qg.email like CONCAT('%', :qywxGzuserinfo.email ,'%') 
		</#if>
		<#if qywxGzuserinfo.weixinid ?exists && qywxGzuserinfo.weixinid ?length gt 0>
		    /* 微信号 */
			and qg.weixinid like CONCAT('%', :qywxGzuserinfo.weixinid ,'%') 
		</#if>
		<#if qywxGzuserinfo.avatar ?exists && qywxGzuserinfo.avatar ?length gt 0>
		    /* 头像url */
			and qg.avatar like CONCAT('%', :qywxGzuserinfo.avatar ,'%') 
		</#if>
		<#if qywxGzuserinfo.subscribeStatus ?exists && qywxGzuserinfo.subscribeStatus ?length gt 0>
		    /* 关注状态: 1=已关注，2=已冻结，4=未关注 */
			and qg.subscribe_status like CONCAT('%', :qywxGzuserinfo.subscribeStatus ,'%') 
		</#if>
	    <#if qywxGzuserinfo.subscribeTime ?exists>
		    /* 关注时间 */
			and qg.subscribe_time = :qywxGzuserinfo.subscribeTime
		</#if>
		<#if qywxGzuserinfo.accountid ?exists && qywxGzuserinfo.accountid ?length gt 0>
		    /* 微信账号ID */
			and qg.accountid like CONCAT('%', :qywxGzuserinfo.accountid ,'%') 
		</#if>
		<#if qywxGzuserinfo.createName ?exists && qywxGzuserinfo.createName ?length gt 0>
		    /* 创建人名称 */
			and qg.create_name like CONCAT('%', :qywxGzuserinfo.createName ,'%') 
		</#if>
		<#if qywxGzuserinfo.createBy ?exists && qywxGzuserinfo.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qg.create_by like CONCAT('%', :qywxGzuserinfo.createBy ,'%') 
		</#if>
	    <#if qywxGzuserinfo.createDate ?exists>
		    /* 创建日期 */
			and qg.create_date = :qywxGzuserinfo.createDate
		</#if>
		<#if qywxGzuserinfo.updateName ?exists && qywxGzuserinfo.updateName ?length gt 0>
		    /* 更新人名称 */
			and qg.update_name like CONCAT('%', :qywxGzuserinfo.updateName ,'%') 
		</#if>
		<#if qywxGzuserinfo.updateBy ?exists && qywxGzuserinfo.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qg.update_by like CONCAT('%', :qywxGzuserinfo.updateBy ,'%') 
		</#if>
	    <#if qywxGzuserinfo.updateDate ?exists>
		    /* 更新日期 */
			and qg.update_date = :qywxGzuserinfo.updateDate
		</#if>
