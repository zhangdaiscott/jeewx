UPDATE alipay_account
SET 
		   id = :alipayAccount.id
	   <#if alipayAccount.accontName ?exists && alipayAccount.accontName ?length gt 0>
		  ,accont_name = :alipayAccount.accontName
		</#if>
	   <#if alipayAccount.accountDesc ?exists>
		  ,account_desc = :alipayAccount.accountDesc
		</#if>
	   <#if alipayAccount.alipayPublicKey ?exists && alipayAccount.alipayPublicKey ?length gt 0>
		  ,alipay_public_key = :alipayAccount.alipayPublicKey
		</#if>
	   <#if alipayAccount.appid ?exists && alipayAccount.appid ?length gt 0>
		  ,appid = :alipayAccount.appid
		</#if>
		<#if alipayAccount.rsaPrivateKey ?exists && alipayAccount.rsaPrivateKey ?length gt 0>
		  ,rsa_private_key = :alipayAccount.rsaPrivateKey
		</#if>
		<#if alipayAccount.publicKey ?exists && alipayAccount.publicKey ?length gt 0>
		  ,public_key = :alipayAccount.publicKey
		</#if>
	   <#if alipayAccount.createName ?exists && alipayAccount.createName ?length gt 0>
		  ,create_name = :alipayAccount.createName
		</#if>
	   <#if alipayAccount.createBy ?exists && alipayAccount.createBy ?length gt 0>
		  ,create_by = :alipayAccount.createBy
		</#if>
	    <#if alipayAccount.createDate ?exists>
		   ,create_date = :alipayAccount.createDate
		</#if>
	   <#if alipayAccount.updateName ?exists && alipayAccount.updateName ?length gt 0>
		  ,update_name = :alipayAccount.updateName
		</#if>
	   <#if alipayAccount.updateBy ?exists && alipayAccount.updateBy ?length gt 0>
		  ,update_by = :alipayAccount.updateBy
		</#if>
	    <#if alipayAccount.updateDate ?exists>
		   ,update_date = :alipayAccount.updateDate
		</#if>
WHERE id = :alipayAccount.id