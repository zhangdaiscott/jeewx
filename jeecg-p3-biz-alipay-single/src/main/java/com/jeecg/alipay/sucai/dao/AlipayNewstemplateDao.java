package com.jeecg.alipay.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.sucai.entity.AlipayNewstemplate;

/**
 * 描述：</b>QywxNewstemplateDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 18时59分45秒 星期四 
 * @version:1.0
 */
@Repository
public interface AlipayNewstemplateDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_newstemplate WHERE ID = :id")
	AlipayNewstemplate get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayNewstemplate
	 * @return
	 */
	int update(@Param("alipayNewstemplate") AlipayNewstemplate alipayNewstemplate);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayNewstemplate") AlipayNewstemplate alipayNewstemplate);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayNewstemplate
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayNewstemplate.class)
	public MiniDaoPage<AlipayNewstemplate> getAll(@Param("alipayNewstemplate") AlipayNewstemplate alipayNewstemplate,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_newstemplate WHERE ID = :alipayNewstemplate.id")
	public void delete(@Param("alipayNewstemplate") AlipayNewstemplate alipayNewstemplate);
	
	//update-begin--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
	/**
	 * 查询所有的图文模板
	 * @param accountid 
	 * @return
	 */
	@Sql("select * from alipay_newstemplate where accountid = :accountid order by create_date desc LIMIT 0,6")
	public List<AlipayNewstemplate> getAllAlipayNewstemplate(@Param("accountid")String accountid);
	//update-begin--author:zhangjiaqiang Date:20161108 for:根据账号获取数据
}

