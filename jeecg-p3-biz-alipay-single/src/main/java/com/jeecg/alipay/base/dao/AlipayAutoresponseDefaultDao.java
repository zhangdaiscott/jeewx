package com.jeecg.alipay.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayAutoresponseDefault;

/**
 * 描述：</b>AlipayAutoresponseDefaultDao<br>
 * @author：p3.jeecg
 * @since：2016年04月06日 16时33分37秒 星期三 
 * @version:1.0
 */
@Repository
public interface AlipayAutoresponseDefaultDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_autoresponse_default WHERE ID = :id")
	AlipayAutoresponseDefault get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayAutoresponseDefault
	 * @return
	 */
	int update(@Param("alipayAutoresponseDefault") AlipayAutoresponseDefault alipayAutoresponseDefault);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayAutoresponseDefault") AlipayAutoresponseDefault alipayAutoresponseDefault);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayAutoresponseDefault
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayAutoresponseDefault.class)
	public MiniDaoPage<AlipayAutoresponseDefault> getAll(@Param("alipayAutoresponseDefault") AlipayAutoresponseDefault alipayAutoresponseDefault,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_autoresponse_default WHERE ID = :alipayAutoresponseDefault.id")
	public void delete(@Param("alipayAutoresponseDefault") AlipayAutoresponseDefault alipayAutoresponseDefault);
	
	@Sql("SELECT * FROM alipay_autoresponse_default WHERE accountid = :accountid and iswork =:iswork")
	@ResultType(AlipayAutoresponseDefault.class)
	public List<AlipayAutoresponseDefault> getAutoresponseDefault(@Param("accountid") String accountid,@Param("iswork") String iswork);
	
}

