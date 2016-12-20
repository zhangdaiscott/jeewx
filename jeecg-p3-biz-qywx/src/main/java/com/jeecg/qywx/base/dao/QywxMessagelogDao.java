package com.jeecg.qywx.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.base.entity.QywxMessagelog;

/**
 * 描述：</b>QywxMessagelogDao<br>
 * @author：p3.jeecg
 * @since：2016年05月25日 16时38分06秒 星期三 
 * @version:1.0
 */
@Repository
public interface QywxMessagelogDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_messagelog WHERE ID = :id")
	QywxMessagelog get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxMessagelog
	 * @return
	 */
	int update(@Param("qywxMessagelog") QywxMessagelog qywxMessagelog);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxMessagelog") QywxMessagelog qywxMessagelog);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxMessagelog
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxMessagelog.class)
	public MiniDaoPage<QywxMessagelog> getAll(@Param("qywxMessagelog") QywxMessagelog qywxMessagelog,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_messagelog WHERE ID = :qywxMessagelog.id")
	public void delete(@Param("qywxMessagelog") QywxMessagelog qywxMessagelog);
	
}

