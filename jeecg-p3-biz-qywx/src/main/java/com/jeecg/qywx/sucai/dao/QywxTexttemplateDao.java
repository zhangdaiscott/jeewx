package com.jeecg.qywx.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.sucai.entity.QywxTexttemplate;

/**
 * 描述：</b>QywxTexttemplateDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 15时52分18秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxTexttemplateDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_texttemplate WHERE ID = :id")
	QywxTexttemplate get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxTexttemplate
	 * @return
	 */
	int update(@Param("qywxTexttemplate") QywxTexttemplate qywxTexttemplate);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxTexttemplate") QywxTexttemplate qywxTexttemplate);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxTexttemplate
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxTexttemplate.class)
	public MiniDaoPage<QywxTexttemplate> getAll(@Param("qywxTexttemplate") QywxTexttemplate qywxTexttemplate,@Param("page")  int page,@Param("rows") int rows);
	

	/**
	 * 查询所有的文本模板
	 * @return
	 */
	@Sql("select * from qywx_texttemplate order by create_date desc")
	public List<QywxTexttemplate> getAllQywxTexttemplate();
	
	
	@Sql("DELETE from qywx_texttemplate WHERE ID = :qywxTexttemplate.id")
	public void delete(@Param("qywxTexttemplate") QywxTexttemplate qywxTexttemplate);
	
}

