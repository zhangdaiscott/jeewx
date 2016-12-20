		<#if qywxLocation.id ?exists && qywxLocation.id ?length gt 0>
		    /* id */
			and ql.id like CONCAT('%', :qywxLocation.id ,'%') 
		</#if>
		<#if qywxLocation.corpid ?exists && qywxLocation.corpid ?length gt 0>
		    /* 企业号corpId */
			and ql.corpid like CONCAT('%', :qywxLocation.corpid ,'%') 
		</#if>
		<#if qywxLocation.userid ?exists && qywxLocation.userid ?length gt 0>
		    /*  	成员UserID  */
			and ql.userid like CONCAT('%', :qywxLocation.userid ,'%') 
		</#if>
	    <#if qywxLocation.createtime ?exists>
		    /* 创建时间 */
			and ql.createtime = :qywxLocation.createtime
		</#if>
		<#if qywxLocation.latitude ?exists && qywxLocation.latitude ?length gt 0>
		    /* 地理位置纬度 */
			and ql.latitude like CONCAT('%', :qywxLocation.latitude ,'%') 
		</#if>
		<#if qywxLocation.longitude ?exists && qywxLocation.longitude ?length gt 0>
		    /* 地理位置径度 */
			and ql.longitude like CONCAT('%', :qywxLocation.longitude ,'%') 
		</#if>
		<#if qywxLocation.locationPrecision ?exists && qywxLocation.locationPrecision ?length gt 0>
		    /* 地理位置精度 */
			and ql.location_precision like CONCAT('%', :qywxLocation.locationPrecision ,'%') 
		</#if>
		<#if qywxLocation.agentid ?exists && qywxLocation.agentid ?length gt 0>
		    /* 企业应用id */
			and ql.agentid like CONCAT('%', :qywxLocation.agentid ,'%') 
		</#if>
