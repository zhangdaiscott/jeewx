package com.jeecg.alipay.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayAutoresponse;

/**
 * 描述：</b>AlipayAutoresponseDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 11时33分23秒 星期五 
 * @version:1.0
 */
@Repository
public interface AlipayAutoresponseDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_autoresponse WHERE ID = :id")
	AlipayAutoresponse get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayAutoresponse
	 * @return
	 */
	int update(@Param("alipayAutoresponse") AlipayAutoresponse alipayAutoresponse);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayAutoresponse") AlipayAutoresponse alipayAutoresponse);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayAutoresponse
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayAutoresponse.class)
	public MiniDaoPage<AlipayAutoresponse> getAll(@Param("alipayAutoresponse") AlipayAutoresponse alipayAutoresponse,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_autoresponse WHERE ID = :alipayAutoresponse.id")
	public void delete(@Param("alipayAutoresponse") AlipayAutoresponse alipayAutoresponse);
	
	@Sql("select *  from alipay_autoresponse WHERE accountid = :accountid")
	@ResultType(AlipayAutoresponse.class)
	public List<AlipayAutoresponse> getAlipayAutoresponseByAccountid(@Param("accountid") String accountid);
	
}

