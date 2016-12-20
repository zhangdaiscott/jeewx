package com.jeecg.alipay.base.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.alipay.base.entity.AlipayGzuserinfo;

/**
 * 描述：</b>QywxGzuserinfoDao<br>
 * @author：zhoujf
 * @since：2016年03月25日 15时24分11秒 星期五 
 * @version:1.0
 */
@Repository
public interface AlipayGzuserinfoDao{

    /**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_gzuserinfo WHERE ID = :id")
	AlipayGzuserinfo get(@Param("id") String id);
	
	/**
	 * 修改数据
	 * @param alipayGzuserinfo
	 * @return
	 */
	int update(@Param("alipayGzuserinfo") AlipayGzuserinfo alipayGzuserinfo);
	
	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("alipayGzuserinfo") AlipayGzuserinfo alipayGzuserinfo);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param alipayGzuserinfo
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(AlipayGzuserinfo.class)
	public MiniDaoPage<AlipayGzuserinfo> getAll(@Param("alipayGzuserinfo") AlipayGzuserinfo alipayGzuserinfo,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from alipay_gzuserinfo WHERE ID = :alipayGzuserinfo.id")
	public void delete(@Param("alipayGzuserinfo") AlipayGzuserinfo alipayGzuserinfo);
	/**
	 * 根据userid返回对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM alipay_gzuserinfo WHERE USERID = :userid")
	AlipayGzuserinfo getByUserid(@Param("userid") String userid);
	
	@Sql("SELECT * FROM alipay_gzuserinfo WHERE DEPARTMENT = :department")
	public List<AlipayGzuserinfo> getByDepartment(@Param("department") String department);
	
	//查询全部用户
	@Sql("SELECT * FROM alipay_gzuserinfo")
	public List<AlipayGzuserinfo> getAllUser();
	
	//查询多个部门的所有用户的集合
	public List<AlipayGzuserinfo> getdepartments(@Param("departmentsid") String departmentsid);
	
	public List<AlipayGzuserinfo> getAllUser(@Param("alipayGzuserinfo") AlipayGzuserinfo alipayGzuserinfo);
}

