package com.jeecg.alipay.sucai.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.sucai.entity.AlipayNewsitem;

/**
 * 描述：</b>AlipayNewsitemDao<br>
 * @author：zhoujf
 * @since：2016年03月24日 18时59分46秒 星期四 
 * @version:1.0
 */
@Repository
public interface AlipayNewsitemDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_newsitem WHERE ID = :id")
	AlipayNewsitem get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayNewsitem
	 * @return
	 */
	int update(@Param("alipayNewsitem") AlipayNewsitem alipayNewsitem);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayNewsitem") AlipayNewsitem alipayNewsitem);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayNewsitem
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayNewsitem.class)
	public MiniDaoPage<AlipayNewsitem> getAll(@Param("alipayNewsitem") AlipayNewsitem alipayNewsitem,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_newsitem WHERE ID = :alipayNewsitem.id")
	public void delete(@Param("alipayNewsitem") AlipayNewsitem alipayNewsitem);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 */
	@Sql("select * from alipay_newsitem WHERE templateid = :templateId order by order_no asc")
	@ResultType(AlipayNewsitem.class)
	public List<AlipayNewsitem> getAlipayNewsitemByTemplateId(@Param("templateId") String templateId);
	
	@Sql("DELETE from alipay_newsitem WHERE templateid = :templateId ")
	public void deleteByTemplateId(@Param("templateId") String templateId);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 */
	@Sql("select * from alipay_newsitem where templateid =:templateId")
	@ResultType(AlipayNewsitem.class)
	public List<AlipayNewsitem> getALLNews(@Param("templateId") String templateId);
	
	
	@Sql("select * from alipay_newsitem order by create_date desc")
	public List<AlipayNewsitem> getAllNewstemplate();
	
	@Sql("select * from alipay_newsitem where templateid =:templateId")
	@ResultType(AlipayNewsitem.class)
	public AlipayNewsitem getALLNew(@Param("templateId") String templateId);

}

