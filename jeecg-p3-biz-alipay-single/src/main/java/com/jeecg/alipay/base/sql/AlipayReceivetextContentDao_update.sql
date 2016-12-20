UPDATE alipay_receivetext_content
SET 
	   <#if alipayReceivetextContent.receivetextId ?exists>
		   receivetext_id = :alipayReceivetextContent.receivetextId,
		</#if>
	   <#if alipayReceivetextContent.content ?exists>
		   content = :alipayReceivetextContent.content,
		</#if>
	   <#if alipayReceivetextContent.createName ?exists>
		   create_name = :alipayReceivetextContent.createName,
		</#if>
	   <#if alipayReceivetextContent.createBy ?exists>
		   create_by = :alipayReceivetextContent.createBy,
		</#if>
	    <#if alipayReceivetextContent.createDate ?exists>
		   create_date = :alipayReceivetextContent.createDate,
		</#if>
	   <#if alipayReceivetextContent.updateName ?exists>
		   update_name = :alipayReceivetextContent.updateName,
		</#if>
	   <#if alipayReceivetextContent.updateBy ?exists>
		   update_by = :alipayReceivetextContent.updateBy,
		</#if>
	    <#if alipayReceivetextContent.updateDate ?exists>
		   update_date = :alipayReceivetextContent.updateDate,
		</#if>
WHERE id = :alipayReceivetextContent.id