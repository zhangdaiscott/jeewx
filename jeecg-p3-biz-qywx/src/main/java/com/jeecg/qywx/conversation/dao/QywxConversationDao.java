package com.jeecg.qywx.conversation.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.conversation.entity.QywxConversation;

/**
 * 描述：</b>QywxConversationDao<br>
 * @author：p3.jeecg
 * @since：2016年05月12日 21时09分13秒 星期四 
 * @version:1.0
 */
@Repository
public interface QywxConversationDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_conversation WHERE ID = :id")
	QywxConversation get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param qywxConversation
	 * @return
	 */
	int update(@Param("qywxConversation") QywxConversation qywxConversation);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxConversation") QywxConversation qywxConversation);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxConversation
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxConversation.class)
	public MiniDaoPage<QywxConversation> getAll(@Param("qywxConversation") QywxConversation qywxConversation,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from qywx_conversation WHERE ID = :qywxConversation.id")
	public void delete(@Param("qywxConversation") QywxConversation qywxConversation);
	
}

