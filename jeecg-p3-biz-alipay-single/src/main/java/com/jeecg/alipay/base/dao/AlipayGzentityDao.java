package com.jeecg.alipay.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayGzentity;

/**
 * 描述：</b>QywxGzentityDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 12时04分15秒 星期五 
 * @version:1.0
 */
@Repository
public interface AlipayGzentityDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_gzentity WHERE ID = :id")
	AlipayGzentity get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayGzentity
	 * @return
	 */
	int update(@Param("alipayGzentity") AlipayGzentity alipayGzentity);
	
	/**
	 * 修改其他数据的激活状态
	 * @param alipayGzentity
	 * @return
	 */
	int updateOtherisWork(@Param("id")String id);
	
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayGzentity") AlipayGzentity alipayGzentity);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayGzentity
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayGzentity.class)
	public MiniDaoPage<AlipayGzentity> getAll(@Param("alipayGzentity") AlipayGzentity alipayGzentity,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_gzentity WHERE ID = :alipayGzentity.id")
	public void delete(@Param("alipayGzentity") AlipayGzentity alipayGzentity);
	
	/**
	 * 通过状态为1的查询信息
	 * @return
	 */
	@Sql("SELECT * FROM alipay_gzentity WHERE is_work=1 LIMIT 1")
	public  AlipayGzentity getQywxGzentityIsWork();
	
}

