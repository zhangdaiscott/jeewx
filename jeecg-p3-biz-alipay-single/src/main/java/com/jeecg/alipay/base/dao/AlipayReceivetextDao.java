package com.jeecg.alipay.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayReceivetext;

/**
 * 描述：</b>QywxReceivetextDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 10时13分23秒 星期五 
 * @version:1.0
 */
@Repository
public interface AlipayReceivetextDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_receivetext WHERE ID = :id")
	AlipayReceivetext get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayReceivetext
	 * @return
	 */
	int update(@Param("alipayReceivetext") AlipayReceivetext alipayReceivetext);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayReceivetext") AlipayReceivetext alipayReceivetext);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayReceivetext
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayReceivetext.class)
	public MiniDaoPage<AlipayReceivetext> getAll(@Param("alipayReceivetext") AlipayReceivetext alipayReceivetext,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_receivetext WHERE ID = :alipayReceivetext.id")
	public void delete(@Param("alipayReceivetext") AlipayReceivetext alipayReceivetext);
	
}

