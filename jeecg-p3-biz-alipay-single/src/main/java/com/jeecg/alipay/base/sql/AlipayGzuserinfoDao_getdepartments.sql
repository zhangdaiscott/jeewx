SELECT * FROM alipay_gzuserinfo
<#if departmentsid ?exists && departmentsid ?length gt 0>
	where department in (${departmentsid})
</#if> 