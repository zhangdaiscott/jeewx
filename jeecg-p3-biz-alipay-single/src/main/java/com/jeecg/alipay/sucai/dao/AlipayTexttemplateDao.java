package com.jeecg.alipay.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.sucai.entity.AlipayTexttemplate;

/**
 * 描述：</b>AlipayTexttemplateDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 15时52分18秒 星期四 
 * @version:1.0
 */
@Repository
public interface AlipayTexttemplateDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_texttemplate WHERE ID = :id")
	AlipayTexttemplate get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayTexttemplate
	 * @return
	 */
	int update(@Param("alipayTexttemplate") AlipayTexttemplate alipayTexttemplate);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayTexttemplate") AlipayTexttemplate alipayTexttemplate);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayTexttemplate
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayTexttemplate.class)
	public MiniDaoPage<AlipayTexttemplate> getAll(@Param("alipayTexttemplate") AlipayTexttemplate alipayTexttemplate,@Param("page")  int page,@Param("rows") int rows);
	

	/**
	 * 查询所有的文本模板
	 * @param accountId 
	 * @return
	 */
	@Sql("select * from alipay_texttemplate where accountid = :accountId order by create_date desc")
	public List<AlipayTexttemplate> getAllAlipayTexttemplate(@Param("accountId")String accountId);
	
	
	@Sql("DELETE from alipay_texttemplate WHERE ID = :alipayTexttemplate.id")
	public void delete(@Param("alipayTexttemplate") AlipayTexttemplate alipayTexttemplate);
	
}

