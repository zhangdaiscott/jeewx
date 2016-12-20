package com.jeecg.qywx.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.base.entity.QywxReceivetext;

/**
 * 描述：</b>QywxReceivetextDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 10时13分23秒 星期五 
 * @version:1.0
 */
@Repository
public interface QywxReceivetextDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_receivetext WHERE ID = :id")
	QywxReceivetext get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxReceivetext
	 * @return
	 */
	int update(@Param("qywxReceivetext") QywxReceivetext qywxReceivetext);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxReceivetext") QywxReceivetext qywxReceivetext);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxReceivetext
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxReceivetext.class)
	public MiniDaoPage<QywxReceivetext> getAll(@Param("qywxReceivetext") QywxReceivetext qywxReceivetext,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_receivetext WHERE ID = :qywxReceivetext.id")
	public void delete(@Param("qywxReceivetext") QywxReceivetext qywxReceivetext);
	
}

