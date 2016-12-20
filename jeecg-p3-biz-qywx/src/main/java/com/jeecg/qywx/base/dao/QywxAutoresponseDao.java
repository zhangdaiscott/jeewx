package com.jeecg.qywx.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.base.entity.QywxAutoresponse;

/**
 * 描述：</b>QywxAutoresponseDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 11时33分23秒 星期五 
 * @version:1.0
 */
@Repository
public interface QywxAutoresponseDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_autoresponse WHERE ID = :id")
	QywxAutoresponse get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxAutoresponse
	 * @return
	 */
	int update(@Param("qywxAutoresponse") QywxAutoresponse qywxAutoresponse);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxAutoresponse") QywxAutoresponse qywxAutoresponse);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxAutoresponse
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxAutoresponse.class)
	public MiniDaoPage<QywxAutoresponse> getAll(@Param("qywxAutoresponse") QywxAutoresponse qywxAutoresponse,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_autoresponse WHERE ID = :qywxAutoresponse.id")
	public void delete(@Param("qywxAutoresponse") QywxAutoresponse qywxAutoresponse);
	
	@Sql("select *  from qywx_autoresponse WHERE accountid = :accountid")
	@ResultType(QywxAutoresponse.class)
	public List<QywxAutoresponse> getQywxAutoresponseByAccountid(@Param("accountid") String accountid);
	
}

