package org.jeecgframework.web.demo.service.test;

import java.util.List;

import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;

/**
 * Minidao例子
 * @author fancq
 *
 */
public interface JeecgMinidaoServiceI {
	public List<JeecgMinidaoEntity> listAll(JeecgMinidaoEntity jeecgMinidao, int page, int rows);
	
	public Integer getCount();
	
	public Integer getSumSalary();
}
