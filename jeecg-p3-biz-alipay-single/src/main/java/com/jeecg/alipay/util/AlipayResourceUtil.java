package com.jeecg.alipay.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 */
public class AlipayResourceUtil {

	private static final String domain;
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("alipayconfig");
	static {
		// update-start--Author:chenchunpeng  Date:20160715 for：修改图文属性文件
		domain = getConfigByName("domain");
		// update-end--Author:chenchunpeng  Date:20160715 for：修改图文属性文件
	}
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}

	public static final String getDomain() {
		return domain;
	}

	public static void main(String[] args) {
		System.out.println(getDomain());
	}
}
