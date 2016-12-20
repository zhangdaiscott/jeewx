package com.jeecg.qywx.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.base.entity.QywxGroup;

/**
 * 描述：</b>QywxGroupDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 13时41分22秒 星期五 
 * @version:1.0
 */
@Repository
public interface QywxGroupDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_group WHERE ID = :id")
	QywxGroup get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxGroup
	 * @return
	 */
	int update(@Param("qywxGroup") QywxGroup qywxGroup);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxGroup") QywxGroup qywxGroup);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxGroup
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxGroup.class)
	public MiniDaoPage<QywxGroup> getAll(@Param("qywxGroup") QywxGroup qywxGroup,@Param("page")  int page,@Param("rows") int rows);
	
	/**
	 * 
	 * @param qywxGroup
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxGroup.class)
	public List<QywxGroup> getAll(@Param("qywxGroup") QywxGroup qywxGroup);
	
	@Sql("DELETE from qywx_group WHERE ID = :id")
	public void delete(@Param("id") String id);
	
	/**
	 * 
	 */
	@Sql("select * from qywx_group WHERE parentid = :parentid")
	@ResultType(QywxGroup.class)
	public List<QywxGroup> getQywxGroupByParentid(@Param("parentid") String parentid);
	
	@Sql("select * from qywx_group ")
	public List<QywxGroup> getAllQywxGroup(@Param("accountId") String accountId);
	
	@Sql("select * from qywx_group ")
	public List<QywxGroup> getAllQywxpid();
	
}

