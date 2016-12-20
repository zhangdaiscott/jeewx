SELECT count(*) FROM weixin_cms_article WHERE 1=1
<#if params.accountid ?exists && params.accountid ?length gt 0>
	and accountid = :params.accountid
</#if>
<#if params.columnid ?exists && params.columnid?length gt 0>
	and column_id = :params.columnid
</#if>
