SELECT * FROM qywx_menu AS qm 
	where qm.agent_id =:agentid
	and   (qm.father_id is null or qm.father_id ='')
ORDER BY qm.agent_id,qm.orders