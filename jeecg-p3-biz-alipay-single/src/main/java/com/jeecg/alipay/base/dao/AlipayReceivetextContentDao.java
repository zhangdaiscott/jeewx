package com.jeecg.alipay.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayReceivetextContent;


/**
 * 描述：</b>AlipayReceivetextContentDao<br>
 * @author：p3.jeecg
 * @since：2016年07月15日 11时48分47秒 星期五 
 * @version:1.0
 */
@Repository
public interface AlipayReceivetextContentDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_receivetext_content WHERE ID = :id")
	AlipayReceivetextContent get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayReceivetextContent
	 * @return
	 */
	int update(@Param("alipayReceivetextContent") AlipayReceivetextContent alipayReceivetextContent);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayReceivetextContent") AlipayReceivetextContent alipayReceivetextContent);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayReceivetextContent
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayReceivetextContent.class)
	public MiniDaoPage<AlipayReceivetextContent> getAll(@Param("alipayReceivetextContent") AlipayReceivetextContent alipayReceivetextContent,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_receivetext_content WHERE ID = :alipayReceivetextContent.id")
	public void delete(@Param("alipayReceivetextContent") AlipayReceivetextContent alipayReceivetextContent);
	
}

