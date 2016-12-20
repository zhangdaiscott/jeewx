package com.jeecg.qywx.account.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.qywx.account.entity.QywxMenu;

/**
 * 描述：</b>QywxMenuDao<br>
 * @author：p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一 
 * @version:1.0
 */
@Repository
public interface QywxMenuDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM qywx_menu WHERE ID = :id")
	QywxMenu get(@Param("id") String id);
	
	
	/**
	 * 通过菜单KEY，查询菜单
	 * @param 
	 * @return
	 */
	@Sql("SELECT * FROM QYWX_MENU WHERE MENU_KEY = :menuKey")
	QywxMenu getMenuByMenuKey(@Param("menuKey") String menuKey);
	
	/**
	 * 修改数据
	 * @param qywxMenu
	 * @return
	 */
	int update(@Param("qywxMenu") QywxMenu qywxMenu);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("qywxMenu") QywxMenu qywxMenu);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param qywxMenu
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxMenu.class)
	public MiniDaoPage<QywxMenu> getAll(@Param("qywxMenu") QywxMenu qywxMenu,@Param("page")  int page,@Param("rows") int rows);
	
	/**
	 * 通过应用ID获取应用
	 * @param qywxMenu
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(QywxMenu.class)
	public MiniDaoPage<QywxMenu> getAllByAgentId(@Param("qywxMenu") QywxMenu qywxMenu,@Param("page")  int page,@Param("rows") int rows);
	
	
	/**
	 * 通过应用ID，获取一级菜单
	 * @param agentid
	 * @return
	 */
	@ResultType(QywxMenu.class)
	public List<QywxMenu> getAllMenuByAgentid(@Param("agentid") String agentid);
	
	/**
	 * 通过父菜单ID，获取二级菜单
	 * @param agentid
	 * @return
	 */
	@ResultType(QywxMenu.class)
	public List<QywxMenu> getAllMenuByParentid(@Param("fatherId") String fatherId);
	
	@Sql("DELETE from qywx_menu WHERE ID = :qywxMenu.id")
	public void delete(@Param("qywxMenu") QywxMenu qywxMenu);
	
	/**
	 * 查询父亲ID
	 * @param id
	 * @return
	 */
	@Sql("SELECT m.id FROM qywx_menu m  where m.agent_id=:agentId AND m.orders = LEFT(:orders,1)")
	String getParentId(@Param("agentId") String agentId,@Param("orders") String orders);
}

