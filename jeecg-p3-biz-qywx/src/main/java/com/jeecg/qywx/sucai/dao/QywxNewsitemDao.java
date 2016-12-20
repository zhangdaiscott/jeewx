package com.jeecg.qywx.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.sucai.entity.QywxNewsitem;
import com.jeecg.qywx.sucai.entity.QywxNewstemplate;

/**
 * 描述：</b>QywxNewsitemDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 18时59分46秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxNewsitemDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_newsitem WHERE ID = :id")
	QywxNewsitem get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxNewsitem
	 * @return
	 */
	int update(@Param("qywxNewsitem") QywxNewsitem qywxNewsitem);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxNewsitem") QywxNewsitem qywxNewsitem);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxNewsitem
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxNewsitem.class)
	public MiniDaoPage<QywxNewsitem> getAll(@Param("qywxNewsitem") QywxNewsitem qywxNewsitem,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_newsitem WHERE ID = :qywxNewsitem.id")
	public void delete(@Param("qywxNewsitem") QywxNewsitem qywxNewsitem);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 */
	@Sql("select * from qywx_newsitem WHERE templateid = :templateId order by order_no asc")
	@ResultType(QywxNewsitem.class)
	public List<QywxNewsitem> getQywxNewsitemByTemplateId(@Param("templateId") String templateId);
	
	@Sql("DELETE from qywx_newsitem WHERE templateid = :templateId ")
	public void deleteByTemplateId(@Param("templateId") String templateId);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 */
	@Sql("select * from qywx_newsitem where templateid =:templateId")
	@ResultType(QywxNewsitem.class)
	public List<QywxNewsitem> getALLNews(@Param("templateId") String templateId);
	
	
	@Sql("select * from qywx_newsitem order by create_date desc")
	public List<QywxNewsitem> getAllNewstemplate();
	
	@Sql("select * from qywx_newsitem where templateid =:templateId")
	@ResultType(QywxNewsitem.class)
	public QywxNewsitem getALLNew(@Param("templateId") String templateId);

}

