package com.jeecg.qywx.base.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.base.entity.QywxLocation;

/**
 * 描述：</b>QywxLocationDao<br>
 * @author：p3.jeecg
 * @since：2016年05月25日 13时27分26秒 星期三 
 * @version:1.0
 */
@Repository
public interface QywxLocationDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_location WHERE ID = :id")
	QywxLocation get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxLocation
	 * @return
	 */
	int update(@Param("qywxLocation") QywxLocation qywxLocation);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxLocation") QywxLocation qywxLocation);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxLocation
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxLocation.class)
	public MiniDaoPage<QywxLocation> getAll(@Param("qywxLocation") QywxLocation qywxLocation,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_location WHERE ID = :qywxLocation.id")
	public void delete(@Param("qywxLocation") QywxLocation qywxLocation);
	
	/*
	 * 根据agenid企业应用的id,
	 * fromUserName成员UserID,
	 * toUserName 企业号CorpID
	 * 做查询
	 * 
	 */
	@Sql("SELECT * from qywx_location WHERE corpid = :corpid and userid =:userid and agentid =:agentid")
	public QywxLocation getQywxLocation(@Param("corpid") String corpid,@Param("userid") String userid,@Param("agentid") String agentid);
}

