package com.jeecg.qywx.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.base.entity.QywxAutoresponseDefault;

/**
 * 描述：</b>QywxAutoresponseDefaultDao<br>
 * @author：p3.jeecg
 * @since：2016年04月06日 16时33分37秒 星期三 
 * @version:1.0
 */
@Repository
public interface QywxAutoresponseDefaultDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_autoresponse_default WHERE ID = :id")
	QywxAutoresponseDefault get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxAutoresponseDefault
	 * @return
	 */
	int update(@Param("qywxAutoresponseDefault") QywxAutoresponseDefault qywxAutoresponseDefault);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxAutoresponseDefault") QywxAutoresponseDefault qywxAutoresponseDefault);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxAutoresponseDefault
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxAutoresponseDefault.class)
	public MiniDaoPage<QywxAutoresponseDefault> getAll(@Param("qywxAutoresponseDefault") QywxAutoresponseDefault qywxAutoresponseDefault,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_autoresponse_default WHERE ID = :qywxAutoresponseDefault.id")
	public void delete(@Param("qywxAutoresponseDefault") QywxAutoresponseDefault qywxAutoresponseDefault);
	
	@Sql("SELECT * FROM qywx_autoresponse_default WHERE accountid = :accountid and iswork =:iswork")
	@ResultType(QywxAutoresponseDefault.class)
	public List<QywxAutoresponseDefault> getAutoresponseDefault(@Param("accountid") String accountid,@Param("iswork") String iswork);
	
}

