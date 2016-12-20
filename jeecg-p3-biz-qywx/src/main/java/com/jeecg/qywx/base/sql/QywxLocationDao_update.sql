UPDATE qywx_location
SET 
	   <#if qywxLocation.id ?exists>
		   id = :qywxLocation.id,
		</#if>
	   <#if qywxLocation.corpid ?exists>
		   corpid = :qywxLocation.corpid,
		</#if>
	   <#if qywxLocation.userid ?exists>
		   userid = :qywxLocation.userid,
		</#if>
	    <#if qywxLocation.createtime ?exists>
		   createtime = :qywxLocation.createtime,
		</#if>
	   <#if qywxLocation.latitude ?exists>
		   latitude = :qywxLocation.latitude,
		</#if>
	   <#if qywxLocation.longitude ?exists>
		   longitude = :qywxLocation.longitude,
		</#if>
	   <#if qywxLocation.locationPrecision ?exists>
		   location_precision = :qywxLocation.locationPrecision,
		</#if>
	   <#if qywxLocation.agentid ?exists>
		   agentid = :qywxLocation.agentid,
		</#if>
WHERE id = :qywxLocation.id