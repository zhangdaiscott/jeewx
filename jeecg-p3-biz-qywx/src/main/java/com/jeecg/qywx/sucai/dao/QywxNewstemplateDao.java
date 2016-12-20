package com.jeecg.qywx.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.sucai.entity.QywxNewstemplate;

/**
 * 描述：</b>QywxNewstemplateDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 18时59分45秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxNewstemplateDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_newstemplate WHERE ID = :id")
	QywxNewstemplate get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxNewstemplate
	 * @return
	 */
	int update(@Param("qywxNewstemplate") QywxNewstemplate qywxNewstemplate);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxNewstemplate") QywxNewstemplate qywxNewstemplate);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxNewstemplate
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxNewstemplate.class)
	public MiniDaoPage<QywxNewstemplate> getAll(@Param("qywxNewstemplate") QywxNewstemplate qywxNewstemplate,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_newstemplate WHERE ID = :qywxNewstemplate.id")
	public void delete(@Param("qywxNewstemplate") QywxNewstemplate qywxNewstemplate);
	
	/**
	 * 查询所有的图文模板
	 * @return
	 */
	@Sql("select * from qywx_newstemplate order by create_date desc LIMIT 0,6")
	public List<QywxNewstemplate> getAllQywxNewstemplate();
	
}

