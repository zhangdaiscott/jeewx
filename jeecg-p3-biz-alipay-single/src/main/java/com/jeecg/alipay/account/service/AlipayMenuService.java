package com.jeecg.alipay.account.service;


import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.alipay.account.dao.AlipayMenuDao;
import com.jeecg.alipay.account.entity.AlipayMenu;


/**
 * 
 * 微信支付菜单
 *
 */
@Service
public class AlipayMenuService{
	@Autowired
	private AlipayMenuDao alipayMenuDao;
	
	public List<AlipayMenu> queryAll(){
		List<AlipayMenu> menuList=null;
		//父分类列表
		List<AlipayMenu> parentList=alipayMenuDao.getAllFirstMenu("");
		if(parentList!=null&&parentList.size()>0){
			List<AlipayMenu> allMenuList=new ArrayList<AlipayMenu>();
			List<AlipayMenu>  childMneuList=getAllChild(parentList,allMenuList);
			//当有数据存在时
			List<AlipayMenu> alipyMenuList=new ArrayList<AlipayMenu>();
			if(childMneuList!=null&&childMneuList.size()>0){
				for (int i = 0; i < childMneuList.size(); i++) {
					AlipayMenu menu1=childMneuList.get(i);
					if(StringUtils.isEmpty(menu1.getFatherId())){//先获取父节点
						alipyMenuList.add(menu1);
						List<AlipayMenu> childList=menu1.getMenuList();
						if(childList!=null&&childList.size()>0){
							childOrder(childList, alipyMenuList);
						}
					}
				}
			}
			menuList=alipyMenuList;
		}else{
			menuList=parentList;
		}
		return menuList;
	}
	
	//递归出所有数据
		public List<AlipayMenu> getAllChild(List<AlipayMenu> menuList,List<AlipayMenu> childMenuList){
			for (AlipayMenu menu : menuList) {
				List<AlipayMenu> childs=alipayMenuDao.getAllMenuByParentid(menu.getId());
				if(childs!=null&&childs.size()>0){//当有下一级时继续便利
					menu.setMenuList(childs);
					childMenuList.add(menu);
					getAllChild(childs, childMenuList);
				}else{
					childMenuList.add(menu);
				}
			}
			return childMenuList;
		}
		
		//给数据排序
		public void childOrder(List<AlipayMenu> childList,List<AlipayMenu> allMenuList){
			for(AlipayMenu menu:childList){
				if(menu.getMenuList()!=null&&menu.getMenuList().size()>0){
					allMenuList.add(menu);
					childOrder(menu.getMenuList(),allMenuList);
				}else{
					allMenuList.add(menu);
				}
			}
		}
}
