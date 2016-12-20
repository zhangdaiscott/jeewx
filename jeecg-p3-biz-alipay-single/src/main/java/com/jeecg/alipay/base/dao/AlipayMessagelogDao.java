package com.jeecg.alipay.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayMessageLog;

/**
 * 描述：</b>QywxMessagelogDao<br>
 * @author：p3.jeecg
 * @since：2016年05月25日 16时38分06秒 星期三 
 * @version:1.0
 */
@Repository
public interface AlipayMessagelogDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_messagelog WHERE ID = :id")
	AlipayMessageLog get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayMessagelog
	 * @return
	 */
	int update(@Param("alipayMessagelog") AlipayMessageLog alipayMessagelog);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayMessagelog") AlipayMessageLog alipayMessagelog);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayMessagelog
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayMessageLog.class)
	public MiniDaoPage<AlipayMessageLog> getAll(@Param("alipayMessagelog") AlipayMessageLog alipayMessagelog,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_messagelog WHERE ID = :alipayMessagelog.id")
	public void delete(@Param("alipayMessagelog") AlipayMessageLog alipayMessagelog);
	
}

