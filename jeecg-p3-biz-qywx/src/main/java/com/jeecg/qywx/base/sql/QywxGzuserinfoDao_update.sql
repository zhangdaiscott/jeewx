UPDATE qywx_gzuserinfo
SET 
	   <#if qywxGzuserinfo.userid ?exists && qywxGzuserinfo.userid ?length gt 0>
		   userid = :qywxGzuserinfo.userid,
		</#if>
	   <#if qywxGzuserinfo.name ?exists && qywxGzuserinfo.name ?length gt 0>
		   name = :qywxGzuserinfo.name,
		</#if>
	   <#if qywxGzuserinfo.department ?exists && qywxGzuserinfo.department ?length gt 0>
		   department = :qywxGzuserinfo.department,
		</#if>
	   <#if qywxGzuserinfo.position ?exists && qywxGzuserinfo.position ?length gt 0>
		   position = :qywxGzuserinfo.position,
		</#if>
	   <#if qywxGzuserinfo.mobile ?exists && qywxGzuserinfo.mobile ?length gt 0>
		   mobile = :qywxGzuserinfo.mobile,
		</#if>
	   <#if qywxGzuserinfo.province ?exists && qywxGzuserinfo.province ?length gt 0>
		   province = :qywxGzuserinfo.province,
		</#if>
	   <#if qywxGzuserinfo.gender ?exists && qywxGzuserinfo.gender ?length gt 0>
		   gender = :qywxGzuserinfo.gender,
		</#if>
	   <#if qywxGzuserinfo.email ?exists && qywxGzuserinfo.email ?length gt 0>
		   email = :qywxGzuserinfo.email,
		</#if>
	   <#if qywxGzuserinfo.weixinid ?exists && qywxGzuserinfo.weixinid ?length gt 0>
		   weixinid = :qywxGzuserinfo.weixinid,
		</#if>
	   <#if qywxGzuserinfo.avatar ?exists && qywxGzuserinfo.avatar ?length gt 0>
		   avatar = :qywxGzuserinfo.avatar,
		</#if>
	   <#if qywxGzuserinfo.subscribeStatus ?exists && qywxGzuserinfo.subscribeStatus ?length gt 0>
		   subscribe_status = :qywxGzuserinfo.subscribeStatus,
		</#if>
	    <#if qywxGzuserinfo.subscribeTime ?exists>
		   subscribe_time = :qywxGzuserinfo.subscribeTime,
		</#if>
	   <#if qywxGzuserinfo.accountid ?exists && qywxGzuserinfo.accountid ?length gt 0>
		   accountid = :qywxGzuserinfo.accountid,
		</#if>
	   <#if qywxGzuserinfo.createName ?exists && qywxGzuserinfo.createName ?length gt 0>
		   create_name = :qywxGzuserinfo.createName,
		</#if>
	   <#if qywxGzuserinfo.createBy ?exists && qywxGzuserinfo.createBy ?length gt 0>
		   create_by = :qywxGzuserinfo.createBy,
		</#if>
	    <#if qywxGzuserinfo.createDate ?exists>
		   create_date = :qywxGzuserinfo.createDate,
		</#if>
	   <#if qywxGzuserinfo.updateName ?exists && qywxGzuserinfo.updateName ?length gt 0>
		   update_name = :qywxGzuserinfo.updateName,
		</#if>
	   <#if qywxGzuserinfo.updateBy ?exists && qywxGzuserinfo.updateBy ?length gt 0>
		   update_by = :qywxGzuserinfo.updateBy,
		</#if>
	    <#if qywxGzuserinfo.updateDate ?exists>
		   update_date = :qywxGzuserinfo.updateDate,
		</#if>
WHERE id = :qywxGzuserinfo.id