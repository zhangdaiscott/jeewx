package com.jeecg.qywx.account.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.account.entity.QywxAgent;

/**
 * 描述：</b>QywxAccountDao<br>
 * @author：p3.jeecg
 * @since：2016年03月24日 14时55分37秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxAccountDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_account WHERE ID = :id")
	QywxAccount get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxAccount
	 * @return
	 */
	int update(@Param("qywxAccount") QywxAccount qywxAccount);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxAccount") QywxAccount qywxAccount);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxAccount
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxAccount.class)
	public MiniDaoPage<QywxAccount> getAll(@Param("qywxAccount") QywxAccount qywxAccount,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_account WHERE ID = :qywxAccount.id")
	public void delete(@Param("qywxAccount") QywxAccount qywxAccount);
	
	/**
	 * 查询所有应用
	 * @return
	 */
	@Sql("select * from qywx_account ")
	public List<QywxAccount> getAllQywxAccount();
	
	/**
	 * 根据传入条件返回记录
	 * @return
	 */
	@ResultType(QywxAccount.class)
	public List<QywxAccount> getListByProperty(@Param("qywxAccount") QywxAccount qywxAccount);
	
	@Sql("SELECT * FROM qywx_account WHERE corpid = :corpid")
	public QywxAccount getQywxAccountByCorpid(@Param("corpid") String corpid);
	
}

