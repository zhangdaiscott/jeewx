package com.jeecg.qywx.account.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.account.entity.QywxAgent;

/**
 * 描述：</b>QywxAgentDao<br>
 * @author：p3.jeecg
 * @since：2016年03月24日 14时55分38秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxAgentDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_agent WHERE ID = :id")
	QywxAgent get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxAgent
	 * @return
	 */
	int update(@Param("qywxAgent") QywxAgent qywxAgent);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxAgent") QywxAgent qywxAgent);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxAgent
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxAgent.class)
	public MiniDaoPage<QywxAgent> getAll(@Param("qywxAgent") QywxAgent qywxAgent,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_agent WHERE ID = :qywxAgent.id")
	public void delete(@Param("qywxAgent") QywxAgent qywxAgent);
	
	/**
	 * 查询所有应用
	 * @return
	 */
	@Sql("select * from qywx_agent ")
	public List<QywxAgent> getAllQywxAgent(@Param("accountId") String accountId);
	
	/**
	 * 根据条件查询数据
	 * @return
	 */
	@ResultType(QywxAgent.class)
	public List<QywxAgent> getListByProperty(@Param("qywxAgent") QywxAgent qywxAgent);
	
	/**
	 * 根据accountid查询所有应用
	 * @return
	 */
	@Sql("select * from qywx_agent where account_id =:accountId and wx_agentid =:appid")
	public QywxAgent getQywxAgentByAccountIdAndAppid(@Param("accountId") String accountId,@Param("appid") String appid);
	
	/**
	 * 根据corpid和appid查询所有应用
	 * @return
	 */
	@Sql("select * from qywx_agent app where app.account_id =(select acc.id from qywx_account acc where acc.corpid =:corpid) and app.wx_agentid =:appid")
	public QywxAgent getQywxAgentByCorpidAndAppid(@Param("corpid") String corpid,@Param("appid") String appid);
	
	@Sql("select * from qywx_agent ")
	public List<QywxAgent> getAllQywxAgents();
	/**
	 * 根据agentId查询所有应用
	 * @return
	 */
	@Sql("select * from qywx_agent WHERE wx_agentid =:wxAgentid")
	public QywxAgent getAgent(@Param("wxAgentid") String wxAgentid);

}

