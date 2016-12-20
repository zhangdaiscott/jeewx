UPDATE alipay_gzuserinfo
SET 
	   <#if alipayGzuserinfo.userid ?exists && alipayGzuserinfo.userid ?length gt 0>
		   userid = :alipayGzuserinfo.userid,
		</#if>
	   <#if alipayGzuserinfo.name ?exists && alipayGzuserinfo.name ?length gt 0>
		   name = :alipayGzuserinfo.name,
		</#if>
	   <#if alipayGzuserinfo.department ?exists && alipayGzuserinfo.department ?length gt 0>
		   department = :alipayGzuserinfo.department,
		</#if>
	   <#if alipayGzuserinfo.position ?exists && alipayGzuserinfo.position ?length gt 0>
		   position = :alipayGzuserinfo.position,
		</#if>
	   <#if alipayGzuserinfo.mobile ?exists && alipayGzuserinfo.mobile ?length gt 0>
		   mobile = :alipayGzuserinfo.mobile,
		</#if>
	   <#if alipayGzuserinfo.province ?exists && alipayGzuserinfo.province ?length gt 0>
		   province = :alipayGzuserinfo.province,
		</#if>
	   <#if alipayGzuserinfo.gender ?exists && alipayGzuserinfo.gender ?length gt 0>
		   gender = :alipayGzuserinfo.gender,
		</#if>
	   <#if alipayGzuserinfo.email ?exists && alipayGzuserinfo.email ?length gt 0>
		   email = :alipayGzuserinfo.email,
		</#if>
	   <#if alipayGzuserinfo.weixinid ?exists && alipayGzuserinfo.weixinid ?length gt 0>
		   weixinid = :alipayGzuserinfo.weixinid,
		</#if>
	   <#if alipayGzuserinfo.avatar ?exists && alipayGzuserinfo.avatar ?length gt 0>
		   avatar = :alipayGzuserinfo.avatar,
		</#if>
	   <#if alipayGzuserinfo.subscribeStatus ?exists && alipayGzuserinfo.subscribeStatus ?length gt 0>
		   subscribe_status = :alipayGzuserinfo.subscribeStatus,
		</#if>
	    <#if alipayGzuserinfo.subscribeTime ?exists>
		   subscribe_time = :alipayGzuserinfo.subscribeTime,
		</#if>
	   <#if alipayGzuserinfo.accountid ?exists && alipayGzuserinfo.accountid ?length gt 0>
		   accountid = :alipayGzuserinfo.accountid,
		</#if>
	   <#if alipayGzuserinfo.createName ?exists && alipayGzuserinfo.createName ?length gt 0>
		   create_name = :alipayGzuserinfo.createName,
		</#if>
	   <#if alipayGzuserinfo.createBy ?exists && alipayGzuserinfo.createBy ?length gt 0>
		   create_by = :alipayGzuserinfo.createBy,
		</#if>
	    <#if alipayGzuserinfo.createDate ?exists>
		   create_date = :alipayGzuserinfo.createDate,
		</#if>
	   <#if alipayGzuserinfo.updateName ?exists && alipayGzuserinfo.updateName ?length gt 0>
		   update_name = :alipayGzuserinfo.updateName,
		</#if>
	   <#if alipayGzuserinfo.updateBy ?exists && alipayGzuserinfo.updateBy ?length gt 0>
		   update_by = :alipayGzuserinfo.updateBy,
		</#if>
	    <#if alipayGzuserinfo.updateDate ?exists>
		   update_date = :alipayGzuserinfo.updateDate,
		</#if>
WHERE id = :alipayGzuserinfo.id