package com.jeecg.alipay.account.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.account.entity.AlipayMenu;

/**
 * 描述：</b>QywxMenuDao<br>
 * @author：p3.jeecg
 * @since：2016年03月28日 13时37分49秒 星期一 
 * @version:1.0
 */
@Repository
public interface AlipayMenuDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_menu WHERE ID = :id")
	AlipayMenu get(@Param("id") String id);
	
	
	/**
	 * 通过菜单KEY，查询菜单
	 * @param alipayAccountId 
	 * @param 
	 * @return
	 */
	@Sql("SELECT * FROM alipay_menu WHERE MENU_KEY = :menuKey ")
	AlipayMenu getMenuByMenuKey(@Param("menuKey") String menuKey);
	
	/**
	 * 修改数据
	 * @param alipayMenu
	 * @return
	 */
	int update(@Param("alipayMenu") AlipayMenu alipayMenu);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayMenu") AlipayMenu alipayMenu);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayMenu
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayMenu.class)
	public MiniDaoPage<AlipayMenu> getAll(@Param("alipayMenu") AlipayMenu alipayMenu,@Param("page")  int page,@Param("rows") int rows);
	
	
	/**
	 * 通过应用ID，获取一级菜单
	 * @param accountid 
	 * @param agentid
	 * @return
	 */
	@ResultType(AlipayMenu.class)
	public List<AlipayMenu> getAllFirstMenu(@Param("accountid")String accountid);
	
	/**
	 * 通过父菜单ID，获取二级菜单
	 * @param agentid
	 * @return
	 */
	@ResultType(AlipayMenu.class)
	public List<AlipayMenu> getAllMenuByParentid(@Param("fatherId") String fatherId);
	
	@Sql("DELETE from alipay_menu WHERE ID = :alipayMenu.id")
	public void delete(@Param("alipayMenu") AlipayMenu alipayMenu);
	
	/**
	 * 查询父亲ID
	 * @param sccountid 
	 * @param id
	 * @return
	 */
	@Sql("SELECT m.id FROM alipay_menu m  where  m.orders = LEFT(:orders,1)")
	String getParentId(@Param("orders") String orders);
}

