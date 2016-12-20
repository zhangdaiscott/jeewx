SELECT * FROM weixin_cms_ad WHERE 1=1 
<#if params.accountid ?exists && params.accountid ?length gt 0>
	and accountid = :params.accountid
</#if>