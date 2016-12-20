package com.jeecg.alipay.account.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.account.entity.AlipayAccount;


/**
 * 描述：</b>AlipayAccountDao<br>
 * @author：p3.jeecg
 * @since：2016年03月24日 14时55分37秒 星期四 
 * @version:1.0
 */
@Repository
public interface AlipayAccountDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_account WHERE ID = :id")
	AlipayAccount get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayAccount
	 * @return
	 */
	int update(@Param("alipayAccount") AlipayAccount alipayAccount);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayAccount") AlipayAccount alipayAccount);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayAccount
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayAccount.class)
	public MiniDaoPage<AlipayAccount> getAll(@Param("alipayAccount") AlipayAccount alipayAccount,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_account WHERE ID = :alipayAccount.id")
	public void delete(@Param("alipayAccount") AlipayAccount alipayAccount);
	
	/**
	 * 查询所有应用
	 * @return
	 */
	@Sql("select * from alipay_account ")
	public List<AlipayAccount> getAllAlipayAccount();
	
	/**
	 * 根据传入条件返回记录
	 * @return
	 */
	@ResultType(AlipayAccount.class)
	public List<AlipayAccount> getListByProperty(@Param("alipayAccount") AlipayAccount alipayAccount);
	
	@Sql("SELECT * FROM alipay_account WHERE corpid = :corpid")
	public AlipayAccount getAlipayAccountByCorpid(@Param("corpid") String corpid);
	
}

