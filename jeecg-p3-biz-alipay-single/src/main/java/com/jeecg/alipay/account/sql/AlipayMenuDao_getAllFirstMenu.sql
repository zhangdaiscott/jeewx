SELECT * FROM alipay_menu AS qm 
	WHERE   (qm.father_id is null or qm.father_id ='')
ORDER BY qm.orders